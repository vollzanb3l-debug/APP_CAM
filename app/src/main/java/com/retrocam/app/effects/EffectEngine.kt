package com.retrocam.app.effects

import android.content.Context
import android.graphics.SurfaceTexture
import android.opengl.EGL14
import android.opengl.EGLConfig
import android.opengl.EGLContext
import android.opengl.EGLDisplay
import android.opengl.EGLExt
import android.opengl.EGLSurface
import android.opengl.GLES11Ext
import android.opengl.GLES30
import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import android.view.Surface
import androidx.camera.core.SurfaceRequest
import com.retrocam.app.camera.FrameTransform
import com.retrocam.app.recipe.FinalRecipe
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer
import java.util.concurrent.Executor

/**
 * Single OpenGL ES 3.0 live preview engine.
 * Knows only about FinalRecipe and Surfaces — never JSON, camera names, or Compose UI.
 */
class EffectEngine(context: Context) {

    private val assets = context.applicationContext.assets
    private val glThread = HandlerThread("EffectEngineGL").apply { start() }
    private val glHandler = Handler(glThread.looper)
    private val glExecutor = Executor { command -> glHandler.post(command) }

    @Volatile
    private var released = false

    @Volatile
    private var currentRecipe: FinalRecipe = FinalRecipe()

    @Volatile
    private var viewportWidth: Int = 1080

    @Volatile
    private var viewportHeight: Int = 1440

    private var eglDisplay: EGLDisplay = EGL14.EGL_NO_DISPLAY
    private var eglContext: EGLContext = EGL14.EGL_NO_CONTEXT
    private var eglConfig: EGLConfig? = null
    private var pbufferSurface: EGLSurface = EGL14.EGL_NO_SURFACE
    private var windowSurface: EGLSurface = EGL14.EGL_NO_SURFACE

    private var programId: Int = 0
    private var uTexMatrixLocation: Int = -1
    private var uInputTextureLocation: Int = -1
    private var effectUniforms: EffectUniforms? = null
    private var oesTextureId: Int = 0
    private var vaoId: Int = 0
    private var vboId: Int = 0

    private var surfaceTexture: SurfaceTexture? = null
    private val stMatrix = FloatArray(16)

    private var bufferWidth: Int = 0
    private var bufferHeight: Int = 0
    private var rotationDegrees: Int = 0
    private var isFrontCameraProvider: () -> Boolean = { false }

    init {
        glHandler.post { initGl() }
    }

    fun setRecipe(recipe: FinalRecipe) {
        currentRecipe = recipe
        glHandler.post {
            if (!released && windowSurface != EGL14.EGL_NO_SURFACE) {
                drawCurrentFrame()
            }
        }
    }

    fun getRecipe(): FinalRecipe = currentRecipe

    fun getPreviewViewportSize(): FrameTransform.Dimensions {
        val w = viewportWidth.coerceAtLeast(1)
        val h = viewportHeight.coerceAtLeast(1)
        return FrameTransform.Dimensions(w, h)
    }

    fun attachPreviewSurface(surface: Surface, width: Int, height: Int) {
        if (released) return
        viewportWidth = width.coerceAtLeast(1)
        viewportHeight = height.coerceAtLeast(1)
        glHandler.post {
            if (released || eglDisplay == EGL14.EGL_NO_DISPLAY) return@post
            destroyWindowSurface()
            val config = eglConfig ?: return@post
            val attrs = intArrayOf(EGL14.EGL_NONE)
            windowSurface = EGL14.eglCreateWindowSurface(eglDisplay, config, surface, attrs, 0)
            if (windowSurface == EGL14.EGL_NO_SURFACE) {
                Log.e(TAG, "eglCreateWindowSurface failed: 0x${EGL14.eglGetError().toString(16)}")
                return@post
            }
            makeCurrent(windowSurface)
            drawCurrentFrame()
        }
    }

    fun updatePreviewSize(width: Int, height: Int) {
        if (released) return
        viewportWidth = width.coerceAtLeast(1)
        viewportHeight = height.coerceAtLeast(1)
        glHandler.post {
            if (!released && windowSurface != EGL14.EGL_NO_SURFACE) {
                drawCurrentFrame()
            }
        }
    }

    fun detachPreviewSurface() {
        glHandler.post {
            destroyWindowSurface()
            if (pbufferSurface != EGL14.EGL_NO_SURFACE) {
                makeCurrent(pbufferSurface)
            }
        }
    }

    fun onSurfaceRequest(
        request: SurfaceRequest,
        isFrontCamera: () -> Boolean,
    ) {
        if (released) {
            request.willNotProvideSurface()
            return
        }

        glHandler.post {
            if (released || surfaceTexture == null) {
                request.willNotProvideSurface()
                return@post
            }

            isFrontCameraProvider = isFrontCamera
            bufferWidth = request.resolution.width
            bufferHeight = request.resolution.height

            val st = surfaceTexture ?: run {
                request.willNotProvideSurface()
                return@post
            }
            st.setDefaultBufferSize(bufferWidth, bufferHeight)
            val surface = Surface(st)

            request.setTransformationInfoListener(glExecutor) { info ->
                rotationDegrees = info.rotationDegrees
            }

            request.provideSurface(surface, glExecutor) {
                surface.release()
            }
        }
    }

    fun release() {
        if (released) return
        released = true
        glHandler.post {
            releaseGl()
            glThread.quitSafely()
        }
    }

    private fun initGl() {
        if (released) return
        try {
            eglDisplay = EGL14.eglGetDisplay(EGL14.EGL_DEFAULT_DISPLAY)
            check(eglDisplay != EGL14.EGL_NO_DISPLAY) { "eglGetDisplay failed" }

            val version = IntArray(2)
            check(EGL14.eglInitialize(eglDisplay, version, 0, version, 1)) {
                "eglInitialize failed"
            }

            val configAttribs = intArrayOf(
                EGL14.EGL_RENDERABLE_TYPE, EGLExt.EGL_OPENGL_ES3_BIT_KHR,
                EGL14.EGL_RED_SIZE, 8,
                EGL14.EGL_GREEN_SIZE, 8,
                EGL14.EGL_BLUE_SIZE, 8,
                EGL14.EGL_ALPHA_SIZE, 8,
                EGL14.EGL_SURFACE_TYPE, EGL14.EGL_WINDOW_BIT or EGL14.EGL_PBUFFER_BIT,
                EGL14.EGL_NONE,
            )
            val configs = arrayOfNulls<EGLConfig>(1)
            val numConfigs = IntArray(1)
            check(EGL14.eglChooseConfig(eglDisplay, configAttribs, 0, configs, 0, 1, numConfigs, 0)) {
                "eglChooseConfig failed"
            }
            val config = configs[0] ?: error("No matching EGLConfig for GLES 3.0")
            eglConfig = config

            val contextAttribs = intArrayOf(
                EGL14.EGL_CONTEXT_CLIENT_VERSION, 3,
                EGL14.EGL_NONE,
            )
            eglContext = EGL14.eglCreateContext(
                eglDisplay,
                config,
                EGL14.EGL_NO_CONTEXT,
                contextAttribs,
                0,
            )
            check(eglContext != EGL14.EGL_NO_CONTEXT) { "eglCreateContext failed" }

            val pbufferAttribs = intArrayOf(
                EGL14.EGL_WIDTH, 1,
                EGL14.EGL_HEIGHT, 1,
                EGL14.EGL_NONE,
            )
            pbufferSurface = EGL14.eglCreatePbufferSurface(eglDisplay, config, pbufferAttribs, 0)
            check(pbufferSurface != EGL14.EGL_NO_SURFACE) { "eglCreatePbufferSurface failed" }
            makeCurrent(pbufferSurface)

            val vertSrc = ShaderLoader.loadAssetText(assets, "shaders/retro_pipeline.vert")
            val fragBody = ShaderLoader.loadAssetText(assets, "shaders/retro_pipeline.frag")
            val fragSrc = ShaderLoader.buildFragmentSource(fragBody, useExternalOes = true)
            programId = ShaderLoader.compileProgram(vertSrc, fragSrc)
            uTexMatrixLocation = GLES30.glGetUniformLocation(programId, "uTexMatrix")
            uInputTextureLocation = GLES30.glGetUniformLocation(programId, "uInputTexture")
            effectUniforms = EffectUniforms.lookup(programId)

            setupQuadGeometry()

            val textures = IntArray(1)
            GLES30.glGenTextures(1, textures, 0)
            oesTextureId = textures[0]
            GLES30.glBindTexture(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, oesTextureId)
            GLES30.glTexParameteri(
                GLES11Ext.GL_TEXTURE_EXTERNAL_OES,
                GLES30.GL_TEXTURE_MIN_FILTER,
                GLES30.GL_LINEAR,
            )
            GLES30.glTexParameteri(
                GLES11Ext.GL_TEXTURE_EXTERNAL_OES,
                GLES30.GL_TEXTURE_MAG_FILTER,
                GLES30.GL_LINEAR,
            )
            GLES30.glTexParameteri(
                GLES11Ext.GL_TEXTURE_EXTERNAL_OES,
                GLES30.GL_TEXTURE_WRAP_S,
                GLES30.GL_CLAMP_TO_EDGE,
            )
            GLES30.glTexParameteri(
                GLES11Ext.GL_TEXTURE_EXTERNAL_OES,
                GLES30.GL_TEXTURE_WRAP_T,
                GLES30.GL_CLAMP_TO_EDGE,
            )

            surfaceTexture = SurfaceTexture(oesTextureId).apply {
                setOnFrameAvailableListener(
                    {
                        if (!released) {
                            onFrameAvailable()
                        }
                    },
                    glHandler,
                )
            }
        } catch (t: Throwable) {
            Log.e(TAG, "Failed to initialize EffectEngine GL", t)
        }
    }

    private fun setupQuadGeometry() {
        // Screen quad: (x, y, u, v) where GL (-1, +1) top-left maps to UV (0, 1).
        val vertices = floatArrayOf(
            -1f, -1f, 0f, 0f,
            1f, -1f, 1f, 0f,
            -1f, 1f, 0f, 1f,
            1f, 1f, 1f, 1f,
        )
        val buffer: FloatBuffer = ByteBuffer
            .allocateDirect(vertices.size * 4)
            .order(ByteOrder.nativeOrder())
            .asFloatBuffer()
            .apply {
                put(vertices)
                position(0)
            }

        val vaos = IntArray(1)
        val vbos = IntArray(1)
        GLES30.glGenVertexArrays(1, vaos, 0)
        GLES30.glGenBuffers(1, vbos, 0)
        vaoId = vaos[0]
        vboId = vbos[0]

        GLES30.glBindVertexArray(vaoId)
        GLES30.glBindBuffer(GLES30.GL_ARRAY_BUFFER, vboId)
        GLES30.glBufferData(
            GLES30.GL_ARRAY_BUFFER,
            vertices.size * 4,
            buffer,
            GLES30.GL_STATIC_DRAW,
        )

        val strideBytes = 4 * 4
        GLES30.glEnableVertexAttribArray(0)
        GLES30.glVertexAttribPointer(0, 2, GLES30.GL_FLOAT, false, strideBytes, 0)
        GLES30.glEnableVertexAttribArray(1)
        GLES30.glVertexAttribPointer(1, 2, GLES30.GL_FLOAT, false, strideBytes, 2 * 4)

        GLES30.glBindBuffer(GLES30.GL_ARRAY_BUFFER, 0)
        GLES30.glBindVertexArray(0)
    }

    private fun onFrameAvailable() {
        val st = surfaceTexture ?: return
        if (pbufferSurface != EGL14.EGL_NO_SURFACE && windowSurface == EGL14.EGL_NO_SURFACE) {
            makeCurrent(pbufferSurface)
        }
        try {
            st.updateTexImage()
            st.getTransformMatrix(stMatrix)
        } catch (t: Throwable) {
            Log.w(TAG, "updateTexImage failed", t)
            return
        }
        drawCurrentFrame()
    }

    private fun drawCurrentFrame() {
        if (windowSurface == EGL14.EGL_NO_SURFACE || programId == 0) return
        if (!makeCurrent(windowSurface)) return

        val viewW = viewportWidth.coerceAtLeast(1)
        val viewH = viewportHeight.coerceAtLeast(1)
        val bufW = bufferWidth.coerceAtLeast(1)
        val bufH = bufferHeight.coerceAtLeast(1)

        val texMatrix = FrameTransform.buildPreviewTexMatrix(
            surfaceTextureMatrix = stMatrix,
            bufferWidth = bufW,
            bufferHeight = bufH,
            rotationDegrees = rotationDegrees,
            mirrorHorizontal = isFrontCameraProvider(),
            viewportWidth = viewW,
            viewportHeight = viewH,
        )

        GLES30.glViewport(0, 0, viewW, viewH)
        GLES30.glClearColor(0f, 0f, 0f, 1f)
        GLES30.glClear(GLES30.GL_COLOR_BUFFER_BIT)

        GLES30.glUseProgram(programId)
        GLES30.glUniformMatrix4fv(uTexMatrixLocation, 1, false, texMatrix, 0)
        effectUniforms?.apply(currentRecipe)

        GLES30.glActiveTexture(GLES30.GL_TEXTURE0)
        GLES30.glBindTexture(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, oesTextureId)
        GLES30.glUniform1i(uInputTextureLocation, 0)

        GLES30.glBindVertexArray(vaoId)
        GLES30.glDrawArrays(GLES30.GL_TRIANGLE_STRIP, 0, 4)
        GLES30.glBindVertexArray(0)

        EGL14.eglSwapBuffers(eglDisplay, windowSurface)
    }

    private fun makeCurrent(surface: EGLSurface): Boolean {
        if (eglDisplay == EGL14.EGL_NO_DISPLAY || eglContext == EGL14.EGL_NO_CONTEXT) return false
        return EGL14.eglMakeCurrent(eglDisplay, surface, surface, eglContext)
    }

    private fun destroyWindowSurface() {
        if (windowSurface != EGL14.EGL_NO_SURFACE && eglDisplay != EGL14.EGL_NO_DISPLAY) {
            EGL14.eglDestroySurface(eglDisplay, windowSurface)
            windowSurface = EGL14.EGL_NO_SURFACE
        }
    }

    private fun releaseGl() {
        surfaceTexture?.release()
        surfaceTexture = null

        if (eglDisplay != EGL14.EGL_NO_DISPLAY) {
            if (pbufferSurface != EGL14.EGL_NO_SURFACE) {
                makeCurrent(pbufferSurface)
            }
            if (oesTextureId != 0) {
                GLES30.glDeleteTextures(1, intArrayOf(oesTextureId), 0)
                oesTextureId = 0
            }
            if (vboId != 0) {
                GLES30.glDeleteBuffers(1, intArrayOf(vboId), 0)
                vboId = 0
            }
            if (vaoId != 0) {
                GLES30.glDeleteVertexArrays(1, intArrayOf(vaoId), 0)
                vaoId = 0
            }
            if (programId != 0) {
                GLES30.glDeleteProgram(programId)
                programId = 0
            }

            EGL14.eglMakeCurrent(
                eglDisplay,
                EGL14.EGL_NO_SURFACE,
                EGL14.EGL_NO_SURFACE,
                EGL14.EGL_NO_CONTEXT,
            )
            destroyWindowSurface()
            if (pbufferSurface != EGL14.EGL_NO_SURFACE) {
                EGL14.eglDestroySurface(eglDisplay, pbufferSurface)
                pbufferSurface = EGL14.EGL_NO_SURFACE
            }
            if (eglContext != EGL14.EGL_NO_CONTEXT) {
                EGL14.eglDestroyContext(eglDisplay, eglContext)
                eglContext = EGL14.EGL_NO_CONTEXT
            }
            EGL14.eglTerminate(eglDisplay)
            eglDisplay = EGL14.EGL_NO_DISPLAY
        }
    }

    companion object {
        private const val TAG = "EffectEngine"
    }
}
