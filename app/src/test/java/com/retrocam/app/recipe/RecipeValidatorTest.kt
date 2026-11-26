package com.retrocam.app.recipe

import org.junit.Assert.assertTrue
import org.junit.Test

class RecipeValidatorTest {

    private fun validCamera() = CameraSpec(
        schemaVersion = 3,
        id = "compact_2003_ccd",
        displayName = "Kompak CCD 2003",
        inspiredBy = "private note",
        era = "2000s",
        category = "digital_compact",
        archetype = "ccd_compact_2000s",
        confidenceLook = "high",
        confidenceSpecs = "high",
        sources = listOf("Perkiraan awal arketipe CCD"),
        calibrationStatus = "draft",
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
            schemaVersion = 2,
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
}
