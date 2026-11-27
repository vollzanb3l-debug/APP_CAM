package com.retrocam.app.recipe

import org.junit.Assert.assertTrue
import org.junit.Test

class RecipeValidatorTest {

    private fun validCamera() = CameraSpec(
        schemaVersion = 4,
        id = "compact_2003_ccd",
        brand = "RetroCam",
        model = "Kompak CCD 2003",
        aliases = listOf("CCD 2003"),
        displayName = "Kompak CCD 2003",
        inspiredBy = "private note",
        era = "2000s",
        category = "digital_compact",
        archetype = "ccd_compact_2000s",
        confidenceLook = "high",
        confidenceSpecs = "high",
        sources = listOf("Perkiraan awal arketipe CCD"),
        calibrationStatus = "draft",
        iconType = "compact_digital",
        icon = "icons/cameras/compact.png",
        aspectRatio = "4:3",
        whiteBalance = WhiteBalanceSpec(0.08f, 0.02f),
        tone = ToneSpec(1.10f, 0.30f, 0.02f),
        saturation = 1.05f,
        sensorNoise = SensorNoiseSpec(0.18f, 0.35f),
        sharpen = 0.35f,
        dateStamp = DateStampSpec(true, false, "yy MM dd", "#FF8C1A"),
        notes = "draft",
    )

    private fun validFilm() = FilmSpec(
        schemaVersion = 2,
        id = "film_surya_200",
        displayName = "Surya 200",
        archetype = "warm_golden_film",
        confidenceLook = "high",
        confidenceSpecs = "low",
        sources = listOf("Panduan kualitatif film_looks.md"),
        calibrationStatus = "draft",
        icon = "icons/films/surya_200.png",
        lut = null,
        grade = FilmGradeSpec(
            temp = 0.14f,
            tint = 0.02f,
            saturation = 1.06f,
            contrast = 1.04f,
            blackLift = 0.04f,
            highlightTint = "#FFD8A8",
            shadowTint = "#26332D",
            tintStrength = 0.12f,
        ),
        grain = GrainSpec(amount = 0.16f, size = 0.0016f, roughness = 0.55f),
        halation = HalationSpec(intensity = 0.08f, color = "#FF7244"),
        notes = "draft",
    )

    @Test
    fun validCameraAndFilm_passValidation() {
        assertTrue(RecipeValidator.validateCamera(validCamera()).isEmpty())
        assertTrue(RecipeValidator.validateFilm(validFilm()).isEmpty())
    }

    @Test
    fun camera_rejectsBadSchemaConfidenceStatusSourcesAndHex() {
        val broken = validCamera().copy(
            schemaVersion = 3,
            brand = "",
            model = "   ",
            aliases = listOf("Valid Alias", ""),
            iconType = "unknown_silhouette",
            archetype = "",
            confidenceLook = "certain",
            confidenceSpecs = "guessed",
            calibrationStatus = "official",
            sources = emptyList(),
            aspectRatio = "21:9",
            dateStamp = DateStampSpec(true, false, "yy MM dd", "orange"),
        )
        val errors = RecipeValidator.validateCamera(broken)
        assertTrue(errors.any { it.contains("schemaVersion") })
        assertTrue(errors.any { it.contains("brand") })
        assertTrue(errors.any { it.contains("model") })
        assertTrue(errors.any { it.contains("aliases") })
        assertTrue(errors.any { it.contains("iconType") })
        assertTrue(errors.any { it.contains("archetype") })
        assertTrue(errors.any { it.contains("confidenceLook") })
        assertTrue(errors.any { it.contains("confidenceSpecs") })
        assertTrue(errors.any { it.contains("calibrationStatus") })
        assertTrue(errors.any { it.contains("sources") })
        assertTrue(errors.any { it.contains("aspectRatio") })
        assertTrue(errors.any { it.contains("dateStamp.color") })
    }

    @Test
    fun film_rejectsBadSchemaArchetypeConfidenceStatusSources() {
        val broken = validFilm().copy(
            schemaVersion = 1,
            archetype = "",
            confidenceLook = "sure",
            confidenceSpecs = "unknown",
            calibrationStatus = "final",
            sources = emptyList(),
        )
        val errors = RecipeValidator.validateFilm(broken)
        assertTrue(errors.any { it.contains("schemaVersion") })
        assertTrue(errors.any { it.contains("archetype") })
        assertTrue(errors.any { it.contains("confidenceLook") })
        assertTrue(errors.any { it.contains("confidenceSpecs") })
        assertTrue(errors.any { it.contains("calibrationStatus") })
        assertTrue(errors.any { it.contains("sources") })
    }

    @Test
    fun pureInheritedCameraAndArchetypeV2_validateCleanly() {
        val pureInheritedCamera = CameraSpec(
            schemaVersion = 4,
            id = "canon_powershot_sd1000",
            brand = "Canon",
            model = "PowerShot SD1000",
            aliases = listOf("Digital IXUS 70"),
            displayName = "PowerShot SD1000",
            era = "2000s",
            category = "digital_compact",
            archetype = "ccd_compact_2000s",
            confidenceLook = "high",
            confidenceSpecs = "high",
            sources = listOf("https://global.canon/en/c-museum/product/dcc539.html"),
            calibrationStatus = "draft",
            iconType = "compact_digital",
        )
        assertTrue(RecipeValidator.validateCamera(pureInheritedCamera).isEmpty())

        val validArchetype = ArchetypeSpec(
            schemaVersion = 2,
            id = "ccd_compact_2000s",
            displayName = "Kompak CCD",
            era = "2000s",
            summary = "Draft",
            typicalTraits = listOf("high contrast"),
            calibrationStatus = "draft",
            cameraDefaults = RecipeCompositor.FALLBACK_CAMERA_DEFAULTS,
        )
        assertTrue(RecipeValidator.validateArchetype(validArchetype).isEmpty())

        val brokenArchetype = validArchetype.copy(
            schemaVersion = 1,
            calibrationStatus = "invalid",
            cameraDefaults = RecipeCompositor.FALLBACK_CAMERA_DEFAULTS.copy(
                aspectRatio = "21:9",
                bodyOptics = CameraBodyOpticsSpec(vignetteStrength = 2.0f),
            ),
        )
        val archErrors = RecipeValidator.validateArchetype(brokenArchetype)
        assertTrue(archErrors.any { it.contains("schemaVersion") })
        assertTrue(archErrors.any { it.contains("calibrationStatus") })
        assertTrue(archErrors.any { it.contains("aspectRatio") })
        assertTrue(archErrors.any { it.contains("vignetteStrength") })
    }
}
