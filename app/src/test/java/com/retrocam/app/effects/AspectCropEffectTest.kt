package com.retrocam.app.effects

import com.retrocam.app.camera.FrameTransform
import com.retrocam.app.recipe.CameraSpec
import com.retrocam.app.recipe.DateStampSpec
import com.retrocam.app.recipe.RecipeValidator
import com.retrocam.app.recipe.SensorNoiseSpec
import com.retrocam.app.recipe.ToneSpec
import com.retrocam.app.recipe.WhiteBalanceSpec
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class AspectCropEffectTest {

    private val eps = 1e-4f

    @Test
    fun resolveDisplayAspectRatio_handlesHalfFrame34SquareAndOrientationAdaptiveRatios() {
        // Half-frame 3:4 always stays vertical 3:4 regardless of display orientation
        assertEquals(3f / 4f, FrameTransform.resolveDisplayAspectRatio("3:4", isLandscapeDisplay = false), eps)
        assertEquals(3f / 4f, FrameTransform.resolveDisplayAspectRatio("3:4", isLandscapeDisplay = true), eps)

        // Square 1:1 always stays 1.0
        assertEquals(1f, FrameTransform.resolveDisplayAspectRatio("1:1", isLandscapeDisplay = false), eps)
        assertEquals(1f, FrameTransform.resolveDisplayAspectRatio("1:1", isLandscapeDisplay = true), eps)

        // Classic 3:2 adapts cleanly to portrait (2:3) and landscape (3:2)
        assertEquals(2f / 3f, FrameTransform.resolveDisplayAspectRatio("3:2", isLandscapeDisplay = false), eps)
        assertEquals(3f / 2f, FrameTransform.resolveDisplayAspectRatio("3:2", isLandscapeDisplay = true), eps)

        // Standard 4:3 adapts cleanly to portrait (3:4) and landscape (4:3)
        assertEquals(3f / 4f, FrameTransform.resolveDisplayAspectRatio("4:3", isLandscapeDisplay = false), eps)
        assertEquals(4f / 3f, FrameTransform.resolveDisplayAspectRatio("4:3", isLandscapeDisplay = true), eps)

        // Widescreen 16:9 adapts cleanly
        assertEquals(9f / 16f, FrameTransform.resolveDisplayAspectRatio("16:9", isLandscapeDisplay = false), eps)
        assertEquals(16f / 9f, FrameTransform.resolveDisplayAspectRatio("16:9", isLandscapeDisplay = true), eps)
    }

    @Test
    fun aspectCrop_producesMatchingTargetAspectOnPreviewAndFullResPhoto() {
        val ratios = listOf("4:3", "3:4", "3:2", "1:1", "16:9")
        for (ratio in ratios) {
            val portraitTarget = FrameTransform.resolveDisplayAspectRatio(ratio, isLandscapeDisplay = false)
            val previewDims = FrameTransform.computeAspectTargetDimensions(960, 1280, ratio)
            val photoDims = FrameTransform.computeAspectTargetDimensions(3000, 4000, ratio)

            assertEquals(portraitTarget, previewDims.width.toFloat() / previewDims.height.toFloat(), 0.005f)
            assertEquals(portraitTarget, photoDims.width.toFloat() / photoDims.height.toFloat(), 0.005f)

            val croppedPhoto = FrameTransform.computeCroppedOutputDimensions(
                uprightWidth = 3000,
                uprightHeight = 4000,
                targetWidth = previewDims.width,
                targetHeight = previewDims.height,
            )
            assertEquals(
                previewDims.width.toFloat() / previewDims.height.toFloat(),
                croppedPhoto.width.toFloat() / croppedPhoto.height.toFloat(),
                0.005f,
            )
        }
    }

    @Test
    fun validator_acceptsHalfFrame34And23AspectRatios() {
        for (ratio in listOf("3:4", "2:3", "4:3", "3:2", "1:1", "16:9")) {
            val cam = CameraSpec(
                schemaVersion = 3,
                id = "cam_ratio_test",
                displayName = "Ratio Test",
                inspiredBy = "test",
                era = "1990s",
                category = "half_frame",
                archetype = "classic_35mm_slr",
                confidenceLook = "medium",
                confidenceSpecs = "high",
                sources = listOf("unit test"),
                calibrationStatus = "draft",
                icon = "icons/cam.png",
                aspectRatio = ratio,
                whiteBalance = WhiteBalanceSpec(0f, 0f),
                tone = ToneSpec(1f, 0f, 0f),
                saturation = 1f,
                sensorNoise = SensorNoiseSpec(0f, 0f),
                sharpen = 0f,
                dateStamp = DateStampSpec(true, false, "yy MM dd", "#FF8C1A"),
                notes = "test",
            )
            assertTrue("Expected $ratio to be valid", RecipeValidator.validateCamera(cam).isEmpty())
        }
    }
}
