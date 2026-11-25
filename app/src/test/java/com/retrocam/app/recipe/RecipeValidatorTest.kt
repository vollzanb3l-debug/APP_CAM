package com.retrocam.app.recipe

import org.junit.Assert.assertTrue
import org.junit.Test

class RecipeValidatorTest {

    private fun validCamera() = CameraSpec(
        schemaVersion = 2,
        id = "compact_2003_ccd",
        displayName = "Kompak 2003",
        inspiredBy = "private note",
        era = "2000s",
        category = "digital_compact",
        archetype = "ccd_compact_2000s",
        confidence = "medium",
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

    @Test
    fun validCamera_passesValidation() {
        assertTrue(RecipeValidator.validateCamera(validCamera()).isEmpty())
    }

    @Test
    fun camera_rejectsBadSchemaConfidenceStatusSourcesAndHex() {
        val broken = validCamera().copy(
            schemaVersion = 1,
            archetype = "",
            confidence = "certain",
            calibrationStatus = "official",
            sources = emptyList(),
            aspectRatio = "21:9",
            dateStamp = DateStampSpec(true, false, "yy MM dd", "orange"),
        )
        val errors = RecipeValidator.validateCamera(broken)
        assertTrue(errors.any { it.contains("schemaVersion") })
        assertTrue(errors.any { it.contains("archetype") })
        assertTrue(errors.any { it.contains("confidence") })
        assertTrue(errors.any { it.contains("calibrationStatus") })
        assertTrue(errors.any { it.contains("sources") })
        assertTrue(errors.any { it.contains("aspectRatio") })
        assertTrue(errors.any { it.contains("dateStamp.color") })
    }
}
