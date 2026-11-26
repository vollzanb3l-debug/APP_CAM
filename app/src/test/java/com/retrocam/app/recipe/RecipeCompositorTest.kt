package com.retrocam.app.recipe

import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.math.hypot

class RecipeCompositorTest {

    private val eps = 1e-5f

    private fun sampleCamera(
        temp: Float = 0.08f,
        tint: Float = 0.02f,
        contrast: Float = 1.10f,
        saturation: Float = 1.05f,
        noiseAmount: Float = 0.18f,
    ) = CameraSpec(
        schemaVersion = 3,
        id = "cam_test",
        displayName = "Cam Test",
        inspiredBy = "test",
        era = "2000s",
        category = "digital_compact",
        archetype = "ccd_compact_2000s",
        confidenceLook = "high",
        confidenceSpecs = "high",
        sources = listOf("draft test"),
        calibrationStatus = "draft",
        icon = "icons/cam.png",
        aspectRatio = "3:2",
        whiteBalance = WhiteBalanceSpec(temp = temp, tint = tint),
        tone = ToneSpec(contrast = contrast, highlightRolloff = 0.30f, shadowLift = 0.05f),
        saturation = saturation,
        sensorNoise = SensorNoiseSpec(amount = noiseAmount, chroma = 0.35f),
        sharpen = 0.35f,
        dateStamp = DateStampSpec(available = true, defaultOn = true, format = "yy MM dd", color = "#FF8C1A"),
        notes = "test",
    )

    private fun sampleLens() = LensSpec(
        schemaVersion = 1,
        id = "lens_test",
        displayName = "35mm f2",
        icon = "icons/lens.png",
        focalLengthEquiv = 35,
        softness = 0.14f,
        bloom = BloomSpec(intensity = 0.12f, threshold = 0.80f, radius = 0.006f),
        vignette = VignetteSpec(strength = 0.22f, radius = 0.85f),
        distortion = DistortionSpec(k1 = -0.04f),
        chromaticAberration = 0.07f,
        flare = 0.18f,
        notes = "test",
    )

    private fun sampleFilm(
        temp: Float = 0.05f,
        tint: Float = -0.01f,
        contrast: Float = 1.20f,
        saturation: Float = 1.25f,
        grainAmount: Float = 0.24f,
    ) = FilmSpec(
        schemaVersion = 2,
        id = "film_test",
        displayName = "Film Test",
        archetype = "warm_golden_film",
        confidenceLook = "high",
        confidenceSpecs = "low",
        sources = listOf("draft film test"),
        calibrationStatus = "draft",
        icon = "icons/film.png",
        lut = "luts/test.cube",
        grade = FilmGradeSpec(
            temp = temp,
            tint = tint,
            saturation = saturation,
            contrast = contrast,
            blackLift = 0.03f,
            highlightTint = "#FFE9C8",
            shadowTint = "#1B2A3A",
            tintStrength = 0.09f,
        ),
        grain = GrainSpec(amount = grainAmount, size = 0.0012f, roughness = 0.5f),
        halation = HalationSpec(intensity = 0.08f, color = "#FF6A3D"),
        notes = "test",
    )

    @Test
    fun compose_locksArithmeticMergeAndLayerOwnershipRules() {
        val cam = sampleCamera()
        val lens = sampleLens()
        val film = sampleFilm()

        val result = RecipeCompositor.compose(cam, lens, film)

        // Additive
        assertEquals(0.13f, result.temp, eps)
        assertEquals(0.01f, result.tint, eps)
        assertEquals(0.05f, result.shadowLift, eps)
        assertEquals(0.03f, result.blackLift, eps)
        assertEquals(0.12f, result.bloomIntensity, eps)
        assertEquals(0.22f, result.vignetteStrength, eps)
        assertEquals(0.07f, result.chromaticAberration, eps)

        // Multiplicative
        assertEquals(1.10f * 1.20f, result.contrast, eps)
        assertEquals(1.05f * 1.25f, result.saturation, eps)

        // Root sum of squares for noise + grain
        assertEquals(hypot(0.24f, 0.18f), result.combinedNoiseAmount, eps)

        // Camera-only
        assertEquals("3:2", result.aspectRatio)
        assertEquals(true, result.dateStampAvailable)
        assertEquals(true, result.dateStampDefaultOn)
        assertEquals("#FF8C1A", result.dateStampColorHex)

        // Lens-only
        assertEquals(0.14f, result.softness, eps)
        assertEquals(-0.04f, result.distortionK1, eps)
        assertEquals(0.18f, result.flare, eps)

        // Film-only
        assertEquals("luts/test.cube", result.lut)
        assertEquals(0.08f, result.halationIntensity, eps)
        assertEquals("#FFE9C8", result.highlightTintHex)
        assertEquals("#1B2A3A", result.shadowTintHex)
        assertEquals(0.09f, result.tintStrength, eps)
    }

    @Test
    fun compose_clampsExtremeInputsToValidRanges() {
        val cam = sampleCamera(temp = 0.9f, tint = -0.9f, contrast = 1.8f, saturation = 2.0f, noiseAmount = 0.9f)
        val lens = sampleLens()
        val film = sampleFilm(temp = 0.8f, tint = -0.8f, contrast = 1.8f, saturation = 2.0f, grainAmount = 0.9f)

        val result = RecipeCompositor.compose(cam, lens, film)

        assertEquals(1.0f, result.temp, eps)
        assertEquals(-1.0f, result.tint, eps)
        assertEquals(2.0f, result.contrast, eps)
        assertEquals(2.5f, result.saturation, eps)
        assertEquals(1.0f, result.combinedNoiseAmount, eps)
    }
}
