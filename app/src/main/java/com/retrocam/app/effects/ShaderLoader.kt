package com.retrocam.app.effects

import android.content.res.AssetManager
import android.opengl.GLES30
import java.io.BufferedReader

/**
 * Loads GLSL ES 3.0 shaders from assets and compiles shared shader programs
 * for both external OES live preview and 2D offscreen capture.
 */
object ShaderLoader {

    fun loadAssetText(assets: AssetManager, path: String): String {
        return assets.open(path).bufferedReader().use(BufferedReader::readText)
    }

    fun buildFragmentSource(rawBody: String, useExternalOes: Boolean): String {
        val header = buildString {
            append("#version 300 es\n")
            if (useExternalOes) {
                append("#extension GL_OES_EGL_image_external_essl3 : require\n")
                append("#define USE_EXTERNAL_OES 1\n")
            }
        }
        return header + rawBody
    }

    fun compileProgram(vertexSource: String, fragmentSource: String): Int {
        val vertexShader = compileShader(GLES30.GL_VERTEX_SHADER, vertexSource)
        val fragmentShader = try {
            compileShader(GLES30.GL_FRAGMENT_SHADER, fragmentSource)
        } catch (t: Throwable) {
            GLES30.glDeleteShader(vertexShader)
            throw t
        }

        val program = GLES30.glCreateProgram()
        if (program == 0) {
            GLES30.glDeleteShader(vertexShader)
            GLES30.glDeleteShader(fragmentShader)
            error("glCreateProgram returned 0")
        }

        GLES30.glAttachShader(program, vertexShader)
        GLES30.glAttachShader(program, fragmentShader)
        GLES30.glLinkProgram(program)

        val linkStatus = IntArray(1)
        GLES30.glGetProgramiv(program, GLES30.GL_LINK_STATUS, linkStatus, 0)
        GLES30.glDeleteShader(vertexShader)
        GLES30.glDeleteShader(fragmentShader)

        if (linkStatus[0] == 0) {
            val log = GLES30.glGetProgramInfoLog(program)
            GLES30.glDeleteProgram(program)
            error("Shader program link failed: $log")
        }

        return program
    }

    private fun compileShader(type: Int, source: String): Int {
        val shader = GLES30.glCreateShader(type)
        check(shader != 0) { "glCreateShader returned 0 for type=$type" }
        GLES30.glShaderSource(shader, source)
        GLES30.glCompileShader(shader)

        val compileStatus = IntArray(1)
        GLES30.glGetShaderiv(shader, GLES30.GL_COMPILE_STATUS, compileStatus, 0)
        if (compileStatus[0] == 0) {
            val log = GLES30.glGetShaderInfoLog(shader)
            GLES30.glDeleteShader(shader)
            error("Shader compile failed (type=$type): $log")
        }
        return shader
    }
}
