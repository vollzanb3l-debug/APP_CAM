package com.retrocam.app.effects

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.opengl.EGL14
import android.opengl.EGLConfig
import android.opengl.EGLContext
import android.opengl.EGLDisplay
import android.opengl.EGLExt
import android.opengl.EGLSurface
import android.opengl.GLES30
import android.opengl.GLUtils
import com.retrocam.app.camera.FrameTransform
import com.retrocam.app.recipe.FinalRecipe
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer

/**
 * Offscreen OpenGL ES 3.0 full-resolution photo processor using the same shared
 * shader pipeline as EffectEngine. Automatically tiles output when dimensions
 * exceed safe FBO limits so full-resolution captures do not OOM.
 */
class CaptureProcessor(context: Context) {

    private val assets = context.applicationContext.assets

    fun process(
        sourceBitmap: Bitmap,
        rotationDegrees: Int,
        mirrorHorizontal: Boolean,
        viewportWidth: Int,
        viewportHeight: Int,
        recipe: FinalRecipe,
    ): Bitmap {
        // M1 uses passthrough shader; recipe will drive uniforms in M2-M4.
        @Suppress("UNUSED_VARIABLE")
        val unusedRecipe = recipe

        var argbSource: Bitmap? = null
        var safeSource: Bitmap = sourceBitmap
        var eglDisplay: EGLDisplay = EGL14.EGL_NO_DISPLAY
        var eglContext: EGLContext = EGL14.EGL_NO_CONTEXT
        var pbufferSurface: EGLSurface = EGL14.EGL_NO_SURFACE

        var programId = 0
        var sourceTexId = 0
        var fboId = 0
        var fboTexId = 0
        var vaoId = 0
        var vboId = 0

        try {
            if (sourceBitmap.config != Bitmap.Config.ARGB_8888) {
                val copied = checkNotNull(sourceBitmap.copy(Bitmap.Config.ARGB_8888, false)) {
                    "Failed to copy source bitmap to ARGB_8888"
                }
                argbSource = copied
                safeSource = copied
            }

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
                EGL14.EGL_SURFACE_TYPE, EGL14.EGL_PBUFFER_BIT,
                EGL14.EGL_NONE,
            )
            val configs = arrayOfNulls<EGLConfig>(1)
            val numConfigs = IntArray(1)
            check(EGL14.eglChooseConfig(eglDisplay, configAttribs, 0, configs, 0, 1, numConfigs, 0)) {
                "eglChooseConfig failed"
            }
            val config = configs[0] ?: error("No matching EGLConfig for offscreen GLES 3.0")

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
            check(EGL14.eglMakeCurrent(eglDisplay, pbufferSurface, pbufferSurface, eglContext)) {
                "eglMakeCurrent failed"
            }

            val maxTexArr = IntArray(1)
            GLES30.glGetIntegerv(GLES30.GL_MAX_TEXTURE_SIZE, maxTexArr, 0)
            val maxTextureSize = maxTexArr[0].coerceAtLeast(2048)

            // Guard against sensors larger than GL_MAX_TEXTURE_SIZE.
            var scaledDown: Bitmap? = null
            if (safeSource.width > maxTextureSize || safeSource.height > maxTextureSize) {
                val scale = minOf(
                    maxTextureSize.toFloat() / safeSource.width.toFloat(),
                    maxTextureSize.toFloat() / safeSource.height.toFloat(),
                )
                val targetW = (safeSource.width * scale).toInt().coerceAtLeast(1)
                val targetH = (safeSource.height * scale).toInt().coerceAtLeast(1)
                scaledDown = Bitmap.createScaledBitmap(safeSource, targetW, targetH, true)
                safeSource = scaledDown
            }

            val srcW = safeSource.width
            val srcH = safeSource.height
            val upright = FrameTransform.uprightDimensions(srcW, srcH, rotationDegrees)
            val outDims = FrameTransform.computeCroppedOutputDimensions(
                uprightWidth = upright.width,
                uprightHeight = upright.height,
                targetWidth = viewportWidth,
                targetHeight = viewportHeight,
            )
            val outW = outDims.width
            val outH = outDims.height

            val photoTexMatrix = FrameTransform.buildPhotoTexMatrix(
                sourceWidth = srcW,
                sourceHeight = srcH,
                rotationDegrees = rotationDegrees,
                mirrorHorizontal = mirrorHorizontal,
                viewportWidth = viewportWidth,
                viewportHeight = viewportHeight,
            )

            val vertSrc = ShaderLoader.loadAssetText(assets, "shaders/passthrough.vert")
            val fragBody = ShaderLoader.loadAssetText(assets, "shaders/passthrough.frag")
            val fragSrc = ShaderLoader.buildFragmentSource(fragBody, useExternalOes = false)
            programId = ShaderLoader.compileProgram(vertSrc, fragSrc)
            val uTexMatrixLocation = GLES30.glGetUniformLocation(programId, "uTexMatrix")
            val uInputTextureLocation = GLES30.glGetUniformLocation(programId, "uInputTexture")

            GLES30.glPixelStorei(GLES30.GL_UNPACK_ALIGNMENT, 1)
            GLES30.glPixelStorei(GLES30.GL_PACK_ALIGNMENT, 1)

            val srcTexArr = IntArray(1)
            GLES30.glGenTextures(1, srcTexArr, 0)
            sourceTexId = srcTexArr[0]
            GLES30.glBindTexture(GLES30.GL_TEXTURE_2D, sourceTexId)
            GLES30.glTexParameteri(GLES30.GL_TEXTURE_2D, GLES30.GL_TEXTURE_MIN_FILTER, GLES30.GL_LINEAR)
            GLES30.glTexParameteri(GLES30.GL_TEXTURE_2D, GLES30.GL_TEXTURE_MAG_FILTER, GLES30.GL_LINEAR)
            GLES30.glTexParameteri(GLES30.GL_TEXTURE_2D, GLES30.GL_TEXTURE_WRAP_S, GLES30.GL_CLAMP_TO_EDGE)
            GLES30.glTexParameteri(GLES30.GL_TEXTURE_2D, GLES30.GL_TEXTURE_WRAP_T, GLES30.GL_CLAMP_TO_EDGE)
            GLUtils.texImage2D(GLES30.GL_TEXTURE_2D, 0, safeSource, 0)

            scaledDown?.recycle()

            val quad = setupTopDownQuad()
            vaoId = quad.first
            vboId = quad.second

            val fboArr = IntArray(1)
            val fboTexArr = IntArray(1)
            GLES30.glGenFramebuffers(1, fboArr, 0)
            GLES30.glGenTextures(1, fboTexArr, 0)
            fboId = fboArr[0]
            fboTexId = fboTexArr[0]

            val maxTile = minOf(maxTextureSize, MAX_TILE_DIMENSION)
            if (outW <= maxTile && outH <= maxTile) {
                val pixelBuf = renderTileToBuffer(
                    fboId = fboId,
                    fboTexId = fboTexId,
                    sourceTexId = sourceTexId,
                    programId = programId,
                    vaoId = vaoId,
                    uTexMatrixLocation = uTexMatrixLocation,
                    uInputTextureLocation = uInputTextureLocation,
                    texMatrix = photoTexMatrix,
                    tileW = outW,
                    tileH = outH,
                )
                return Bitmap.createBitmap(outW, outH, Bitmap.Config.ARGB_8888).apply {
                    copyPixelsFromBuffer(pixelBuf)
                }
            }

            val outputBitmap = Bitmap.createBitmap(outW, outH, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(outputBitmap)

            var y = 0
            while (y < outH) {
                val tileH = minOf(maxTile, outH - y)
                var x = 0
                while (x < outW) {
                    val tileW = minOf(maxTile, outW - x)
                    val tileSubMatrix = buildTileSubMatrix(x, y, tileW, tileH, outW, outH)
                    val tileTexMatrix = FrameTransform.multiply(photoTexMatrix, tileSubMatrix)

                    val tileBuffer = renderTileToBuffer(
                        fboId = fboId,
                        fboTexId = fboTexId,
                        sourceTexId = sourceTexId,
                        programId = programId,
                        vaoId = vaoId,
                        uTexMatrixLocation = uTexMatrixLocation,
                        uInputTextureLocation = uInputTextureLocation,
                        texMatrix = tileTexMatrix,
                        tileW = tileW,
                        tileH = tileH,
                    )
                    val tileBitmap = Bitmap.createBitmap(tileW, tileH, Bitmap.Config.ARGB_8888)
                    tileBitmap.copyPixelsFromBuffer(tileBuffer)
                    canvas.drawBitmap(tileBitmap, x.toFloat(), y.toFloat(), null)
                    tileBitmap.recycle()

                    x += tileW
                }
                y += tileH
            }

            return outputBitmap
        } finally {
            if (eglDisplay != EGL14.EGL_NO_DISPLAY && eglContext != EGL14.EGL_NO_CONTEXT) {
                if (pbufferSurface != EGL14.EGL_NO_SURFACE) {
                    EGL14.eglMakeCurrent(eglDisplay, pbufferSurface, pbufferSurface, eglContext)
                }
                if (fboId != 0) GLES30.glDeleteFramebuffers(1, intArrayOf(fboId), 0)
                if (fboTexId != 0) GLES30.glDeleteTextures(1, intArrayOf(fboTexId), 0)
                if (sourceTexId != 0) GLES30.glDeleteTextures(1, intArrayOf(sourceTexId), 0)
                if (vboId != 0) GLES30.glDeleteBuffers(1, intArrayOf(vboId), 0)
                if (vaoId != 0) GLES30.glDeleteVertexArrays(1, intArrayOf(vaoId), 0)
                if (programId != 0) GLES30.glDeleteProgram(programId)
                EGL14.eglMakeCurrent(
                    eglDisplay,
                    EGL14.EGL_NO_SURFACE,
                    EGL14.EGL_NO_SURFACE,
                    EGL14.EGL_NO_CONTEXT,
                )
            }
            if (pbufferSurface != EGL14.EGL_NO_SURFACE && eglDisplay != EGL14.EGL_NO_DISPLAY) {
                EGL14.eglDestroySurface(eglDisplay, pbufferSurface)
            }
            if (eglContext != EGL14.EGL_NO_CONTEXT && eglDisplay != EGL14.EGL_NO_DISPLAY) {
                EGL14.eglDestroyContext(eglDisplay, eglContext)
            }
            if (eglDisplay != EGL14.EGL_NO_DISPLAY) {
                EGL14.eglTerminate(eglDisplay)
            }
            argbSource?.recycle()
        }
    }

    private fun renderTileToBuffer(
        fboId: Int,
        fboTexId: Int,
        sourceTexId: Int,
        programId: Int,
        vaoId: Int,
        uTexMatrixLocation: Int,
        uInputTextureLocation: Int,
        texMatrix: FloatArray,
        tileW: Int,
        tileH: Int,
    ): ByteBuffer {
        GLES30.glBindTexture(GLES30.GL_TEXTURE_2D, fboTexId)
        GLES30.glTexParameteri(GLES30.GL_TEXTURE_2D, GLES30.GL_TEXTURE_MIN_FILTER, GLES30.GL_LINEAR)
        GLES30.glTexParameteri(GLES30.GL_TEXTURE_2D, GLES30.GL_TEXTURE_MAG_FILTER, GLES30.GL_LINEAR)
        GLES30.glTexParameteri(GLES30.GL_TEXTURE_2D, GLES30.GL_TEXTURE_WRAP_S, GLES30.GL_CLAMP_TO_EDGE)
        GLES30.glTexParameteri(GLES30.GL_TEXTURE_2D, GLES30.GL_TEXTURE_WRAP_T, GLES30.GL_CLAMP_TO_EDGE)
        GLES30.glTexImage2D(
            GLES30.GL_TEXTURE_2D,
            0,
            GLES30.GL_RGBA8,
            tileW,
            tileH,
            0,
            GLES30.GL_RGBA,
            GLES30.GL_UNSIGNED_BYTE,
            null,
        )

        GLES30.glBindFramebuffer(GLES30.GL_FRAMEBUFFER, fboId)
        GLES30.glFramebufferTexture2D(
            GLES30.GL_FRAMEBUFFER,
            GLES30.GL_COLOR_ATTACHMENT0,
            GLES30.GL_TEXTURE_2D,
            fboTexId,
            0,
        )
        val status = GLES30.glCheckFramebufferStatus(GLES30.GL_FRAMEBUFFER)
        check(status == GLES30.GL_FRAMEBUFFER_COMPLETE) {
            "Offscreen FBO incomplete: 0x${status.toString(16)}"
        }

        GLES30.glViewport(0, 0, tileW, tileH)
        GLES30.glClearColor(0f, 0f, 0f, 1f)
        GLES30.glClear(GLES30.GL_COLOR_BUFFER_BIT)

        GLES30.glUseProgram(programId)
        GLES30.glUniformMatrix4fv(uTexMatrixLocation, 1, false, texMatrix, 0)
        GLES30.glActiveTexture(GLES30.GL_TEXTURE0)
        GLES30.glBindTexture(GLES30.GL_TEXTURE_2D, sourceTexId)
        GLES30.glUniform1i(uInputTextureLocation, 0)

        GLES30.glBindVertexArray(vaoId)
        GLES30.glDrawArrays(GLES30.GL_TRIANGLE_STRIP, 0, 4)
        GLES30.glBindVertexArray(0)
        GLES30.glFinish()

        val pixelBuffer = ByteBuffer.allocateDirect(tileW * tileH * 4).order(ByteOrder.nativeOrder())
        GLES30.glReadPixels(0, 0, tileW, tileH, GLES30.GL_RGBA, GLES30.GL_UNSIGNED_BYTE, pixelBuffer)
        pixelBuffer.rewind()

        GLES30.glBindFramebuffer(GLES30.GL_FRAMEBUFFER, 0)
        return pixelBuffer
    }

    private fun setupTopDownQuad(): Pair<Int, Int> {
        // FBO row y=0 is gl_Position.y=-1, which copyPixelsFromBuffer writes to Bitmap row y=0 (top).
        // Therefore (-1, -1) maps to top-down UV (0, 0) and (+1, +1) maps to (1, 1).
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
        val vao = vaos[0]
        val vbo = vbos[0]

        GLES30.glBindVertexArray(vao)
        GLES30.glBindBuffer(GLES30.GL_ARRAY_BUFFER, vbo)
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
        return vao to vbo
    }

    private fun buildTileSubMatrix(
        tileX: Int,
        tileY: Int,
        tileW: Int,
        tileH: Int,
        outW: Int,
        outH: Int,
    ): FloatArray {
        val sx = tileW.toFloat() / outW.toFloat()
        val sy = tileH.toFloat() / outH.toFloat()
        val tx = tileX.toFloat() / outW.toFloat()
        val ty = tileY.toFloat() / outH.toFloat()
        return floatArrayOf(
            sx, 0f, 0f, 0f,
            0f, sy, 0f, 0f,
            0f, 0f, 1f, 0f,
            tx, ty, 0f, 1f,
        )
    }

    companion object {
        private const val MAX_TILE_DIMENSION = 4096
    }
}
