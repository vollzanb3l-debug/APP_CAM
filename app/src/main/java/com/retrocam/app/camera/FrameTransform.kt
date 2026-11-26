package com.retrocam.app.camera

import kotlin.math.roundToInt

/**
 * Pure-Kotlin 4x4 column-major UV transform math shared between live GL preview
 * (EffectEngine) and offscreen photo capture (CaptureProcessor) so framing,
 * orientation, and front-camera mirroring match identically.
 */
object FrameTransform {

    data class Dimensions(val width: Int, val height: Int)

    data class CropScale(val scaleX: Float, val scaleY: Float)

    fun normalizeRotation(degrees: Int): Int {
        val mod = ((degrees % 360) + 360) % 360
        return when (mod) {
            0, 90, 180, 270 -> mod
            else -> ((mod + 45) / 90 * 90) % 360
        }
    }

    fun uprightDimensions(
        bufferWidth: Int,
        bufferHeight: Int,
        rotationDegrees: Int,
    ): Dimensions {
        val rot = normalizeRotation(rotationDegrees)
        return if (rot == 90 || rot == 270) {
            Dimensions(bufferHeight.coerceAtLeast(1), bufferWidth.coerceAtLeast(1))
        } else {
            Dimensions(bufferWidth.coerceAtLeast(1), bufferHeight.coerceAtLeast(1))
        }
    }

    fun computeCenterCropScale(
        contentWidth: Int,
        contentHeight: Int,
        targetWidth: Int,
        targetHeight: Int,
    ): CropScale {
        if (contentWidth <= 0 || contentHeight <= 0 || targetWidth <= 0 || targetHeight <= 0) {
            return CropScale(1f, 1f)
        }
        val contentAspect = contentWidth.toFloat() / contentHeight.toFloat()
        val targetAspect = targetWidth.toFloat() / targetHeight.toFloat()
        return if (contentAspect > targetAspect) {
            CropScale(scaleX = targetAspect / contentAspect, scaleY = 1f)
        } else if (contentAspect < targetAspect) {
            CropScale(scaleX = 1f, scaleY = contentAspect / targetAspect)
        } else {
            CropScale(1f, 1f)
        }
    }

    fun computeCroppedOutputDimensions(
        uprightWidth: Int,
        uprightHeight: Int,
        targetWidth: Int,
        targetHeight: Int,
    ): Dimensions {
        val crop = computeCenterCropScale(uprightWidth, uprightHeight, targetWidth, targetHeight)
        val outW = (uprightWidth * crop.scaleX).roundToInt().coerceIn(1, uprightWidth.coerceAtLeast(1))
        val outH = (uprightHeight * crop.scaleY).roundToInt().coerceIn(1, uprightHeight.coerceAtLeast(1))
        return Dimensions(outW, outH)
    }

    /**
     * Builds a column-major 4x4 matrix mapping upright top-down UV coordinates
     * (origin at top-left: (0,0)=top-left, (1,1)=bottom-right) into raw source
     * top-down buffer UV coordinates, applying optional horizontal mirror,
     * center-crop, and clockwise sensor-to-target rotation.
     */
    fun buildTopDownToBufferMatrix(
        rotationDegrees: Int,
        mirrorHorizontal: Boolean,
        cropScale: CropScale,
    ): FloatArray {
        val rot = normalizeRotation(rotationDegrees)
        val sx = if (mirrorHorizontal) -cropScale.scaleX else cropScale.scaleX
        val sy = cropScale.scaleY

        // Affine 2D:
        // outU = a * u + b * v + tx
        // outV = c * u + d * v + ty
        val a: Float
        val b: Float
        val tx: Float
        val c: Float
        val d: Float
        val ty: Float

        when (rot) {
            90 -> {
                a = 0f
                b = sy
                tx = 0.5f - 0.5f * sy
                c = -sx
                d = 0f
                ty = 0.5f + 0.5f * sx
            }
            180 -> {
                a = -sx
                b = 0f
                tx = 0.5f + 0.5f * sx
                c = 0f
                d = -sy
                ty = 0.5f + 0.5f * sy
            }
            270 -> {
                a = 0f
                b = -sy
                tx = 0.5f + 0.5f * sy
                c = sx
                d = 0f
                ty = 0.5f - 0.5f * sx
            }
            else -> {
                a = sx
                b = 0f
                tx = 0.5f - 0.5f * sx
                c = 0f
                d = sy
                ty = 0.5f - 0.5f * sy
            }
        }

        return floatArrayOf(
            a, c, 0f, 0f,
            b, d, 0f, 0f,
            0f, 0f, 1f, 0f,
            tx, ty, 0f, 1f,
        )
    }

    /**
     * Texture matrix for offscreen CaptureProcessor where GL_TEXTURE_2D uploads
     * the decoded Bitmap top-down and glReadPixels reads top-down rows.
     */
    fun buildPhotoTexMatrix(
        sourceWidth: Int,
        sourceHeight: Int,
        rotationDegrees: Int,
        mirrorHorizontal: Boolean,
        viewportWidth: Int,
        viewportHeight: Int,
    ): FloatArray {
        val upright = uprightDimensions(sourceWidth, sourceHeight, rotationDegrees)
        val crop = computeCenterCropScale(upright.width, upright.height, viewportWidth, viewportHeight)
        return buildTopDownToBufferMatrix(
            rotationDegrees = rotationDegrees,
            mirrorHorizontal = mirrorHorizontal,
            cropScale = crop,
        )
    }

    /**
     * Texture matrix mapping upright top-down normalized image UV coordinates
     * ((0,0)=top-left, (1,1)=bottom-right) directly into SurfaceTexture OES UV space.
     * Shared by spatial shader effects (vignette, grain, softness, etc.) so preview
     * and full-res photo execute identical normalized-coordinate math.
     */
    fun buildUprightToPreviewTexMatrix(
        surfaceTextureMatrix: FloatArray,
        bufferWidth: Int,
        bufferHeight: Int,
        rotationDegrees: Int,
        mirrorHorizontal: Boolean,
        viewportWidth: Int,
        viewportHeight: Int,
    ): FloatArray {
        val upright = uprightDimensions(bufferWidth, bufferHeight, rotationDegrees)
        val crop = computeCenterCropScale(upright.width, upright.height, viewportWidth, viewportHeight)
        val topDown = buildTopDownToBufferMatrix(
            rotationDegrees = rotationDegrees,
            mirrorHorizontal = mirrorHorizontal,
            cropScale = crop,
        )
        val flipY = verticalFlipMatrix()
        return multiply(surfaceTextureMatrix, multiply(flipY, topDown))
    }

    /**
     * Texture matrix for live EffectEngine preview where quad UVs are GL bottom-up
     * ((0,0)=bottom-left, (1,1)=top-right) and SurfaceTexture.getTransformMatrix
     * maps GL bottom-up buffer UVs to the underlying camera buffer.
     */
    fun buildPreviewTexMatrix(
        surfaceTextureMatrix: FloatArray,
        bufferWidth: Int,
        bufferHeight: Int,
        rotationDegrees: Int,
        mirrorHorizontal: Boolean,
        viewportWidth: Int,
        viewportHeight: Int,
    ): FloatArray {
        val uprightToOes = buildUprightToPreviewTexMatrix(
            surfaceTextureMatrix = surfaceTextureMatrix,
            bufferWidth = bufferWidth,
            bufferHeight = bufferHeight,
            rotationDegrees = rotationDegrees,
            mirrorHorizontal = mirrorHorizontal,
            viewportWidth = viewportWidth,
            viewportHeight = viewportHeight,
        )
        return multiply(uprightToOes, verticalFlipMatrix())
    }

    fun verticalFlipMatrix(): FloatArray = floatArrayOf(
        1f, 0f, 0f, 0f,
        0f, -1f, 0f, 0f,
        0f, 0f, 1f, 0f,
        0f, 1f, 0f, 1f,
    )

    fun identity(): FloatArray = floatArrayOf(
        1f, 0f, 0f, 0f,
        0f, 1f, 0f, 0f,
        0f, 0f, 1f, 0f,
        0f, 0f, 0f, 1f,
    )

    fun multiply(lhs: FloatArray, rhs: FloatArray): FloatArray {
        require(lhs.size == 16 && rhs.size == 16) { "Expected 4x4 matrices" }
        val out = FloatArray(16)
        for (col in 0..3) {
            for (row in 0..3) {
                var sum = 0f
                for (k in 0..3) {
                    sum += lhs[k * 4 + row] * rhs[col * 4 + k]
                }
                out[col * 4 + row] = sum
            }
        }
        return out
    }

    fun mapUv(matrix: FloatArray, u: Float, v: Float): Pair<Float, Float> {
        require(matrix.size == 16) { "Expected 4x4 matrix" }
        val outU = matrix[0] * u + matrix[4] * v + matrix[12]
        val outV = matrix[1] * u + matrix[5] * v + matrix[13]
        return outU to outV
    }
}
