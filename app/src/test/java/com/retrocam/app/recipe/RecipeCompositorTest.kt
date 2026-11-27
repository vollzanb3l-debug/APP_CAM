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
        schemaVersion = 4,
        id = "cam_test",
        brand = "RetroCam",
        model = "Cam Test",
        aliases = emptyList(),
        displayName = "Cam Test",
        inspiredBy = "test",
        era = "2000s",
        category = "digital_compact",
        archetype = "ccd_compact_2000s",
        confidenceLook = "high",
        confidenceSpecs = "high",
        sources = listOf("draft test"),
        calibrationStatus = "draft",
        iconType = "compact_digital",
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

    private fun sampleArchetype() = ArchetypeSpec(
        schemaVersion = 2,
        id = "disposable_point_shoot",
        displayName = "Disposable",
        era = "1990s-2000s",
        summary = "Draft archetype",
        typicalTraits = listOf("soft edges", "mild vignette"),
        calibrationStatus = "draft",
        cameraDefaults = CameraParameterDefaults(
            aspectRatio = "3:2",
            whiteBalance = WhiteBalanceSpec(temp = 0.04f, tint = 0.05f),
            tone = ToneSpec(contrast = 0.94f, highlightRolloff = 0.36f, shadowLift = 0.07f),
            saturation = 0.98f,
            sensorNoise = SensorNoiseSpec(amount = 0.14f, chroma = 0.18f),
            sharpen = 0.05f,
            dateStamp = DateStampSpec(available = true, defaultOn = false, format = "yy MM dd", color = "#FF8C1A"),
            bodyOptics = CameraBodyOpticsSpec(
                softness = 0.08f,
                vignetteStrength = 0.14f,
                bloomIntensity = 0.04f,
                chromaticAberration = 0.04f,
                lightLeakIntensity = 0.06f,
            ),
        ),
    )

    private fun inheritedCamera(
        aspectRatio: String? = null,
        tone: ToneSpec? = null,
        tuningNotes: String = "",
    ) = CameraSpec(
        schemaVersion = 4,
        id = "fujifilm_quicksnap_flash_400",
        brand = "Fujifilm",
        model = "QuickSnap Flash 400",
        aliases = emptyList(),
        displayName = "QuickSnap Flash 400",
        era = "1980s-present",
        category = "disposable_35mm",
        archetype = "disposable_point_shoot",
        confidenceLook = "high",
        confidenceSpecs = "high",
        sources = listOf("test"),
        calibrationStatus = "draft",
        iconType = "disposable",
        aspectRatio = aspectRatio,
        tone = tone,
        tuningNotes = tuningNotes,
    )

    @Test
    fun compose_inheritsAllArchetypeDefaultsAndDeterministicForFixedInputs() {
        val archetype = sampleArchetype()
        val cam = inheritedCamera()
        val lens = sampleLens()
        val film = sampleFilm()

        val first = RecipeCompositor.compose(cam, lens, film, listOf(archetype))
        val second = RecipeCompositor.compose(cam, lens, film, archetype)

        assertEquals(first, second)
        assertEquals("3:2", first.aspectRatio)
        assertEquals(0.04f + 0.05f, first.temp, eps)
        assertEquals(0.05f - 0.01f, first.tint, eps)
        assertEquals(0.94f * 1.20f, first.contrast, eps)
        assertEquals(0.98f * 1.25f, first.saturation, eps)
        assertEquals(0.36f, first.highlightRolloff, eps)
        assertEquals(0.07f, first.shadowLift, eps)
        assertEquals(hypot(0.24f, 0.14f), first.combinedNoiseAmount, eps)
        assertEquals(0.18f, first.sensorNoiseChroma, eps)
        assertEquals(0.05f, first.sharpen, eps)
        assertEquals(0.08f + 0.14f, first.softness, eps)
        assertEquals(0.14f + 0.22f, first.vignetteStrength, eps)
        assertEquals(0.04f + 0.12f, first.bloomIntensity, eps)
        assertEquals(0.04f + 0.07f, first.chromaticAberration, eps)
        assertEquals(0.06f, first.lightLeakIntensity, eps)
    }

    @Test
    fun compose_appliesOptionalPerCameraOverridesOnTopOfArchetype() {
        val archetype = sampleArchetype()
        val cam = inheritedCamera(
            aspectRatio = "3:4",
            tone = ToneSpec(contrast = 1.15f, highlightRolloff = 0.20f, shadowLift = 0.02f),
        )
        val lens = sampleLens()
        val film = sampleFilm()

        val result = RecipeCompositor.compose(cam, lens, film, archetype)

        // Overridden fields come from camera
        assertEquals("3:4", result.aspectRatio)
        assertEquals(1.15f * 1.20f, result.contrast, eps)
        assertEquals(0.20f, result.highlightRolloff, eps)
        assertEquals(0.02f, result.shadowLift, eps)

        // Unspecified fields remain inherited from archetype
        assertEquals(0.04f + 0.05f, result.temp, eps)
        assertEquals(0.98f * 1.25f, result.saturation, eps)
        assertEquals(0.14f + 0.22f, result.vignetteStrength, eps)
    }

    @Test
    fun compose_ignoresTuningNotesCompletelyAndRejectsMismatchedArchetype() {
        val archetype = sampleArchetype()
        val lens = sampleLens()
        val film = sampleFilm()

        val plain = inheritedCamera(tuningNotes = "")
        val withNoisyNotes = inheritedCamera(
            tuningNotes = "contrast=2.0 saturation=0.0 heavy-vignette light-leak=1.0 temp=-1.0",
        )

        assertEquals(
            RecipeCompositor.compose(plain, lens, film, archetype),
            RecipeCompositor.compose(withNoisyNotes, lens, film, archetype),
        )

        val wrongArchetype = archetype.copy(id = "different_archetype")
        val fallbackResult = RecipeCompositor.compose(plain, lens, film, wrongArchetype)
        assertEquals(RecipeCompositor.FALLBACK_CAMERA_DEFAULTS.aspectRatio, fallbackResult.aspectRatio)
        assertEquals(1.0f * 1.20f, fallbackResult.contrast, eps)
    }
}
