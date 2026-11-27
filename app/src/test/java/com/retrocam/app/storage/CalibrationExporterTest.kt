package com.retrocam.app.storage

import com.retrocam.app.recipe.ArchetypeSpec
import com.retrocam.app.recipe.CameraBodyOpticsSpec
import com.retrocam.app.recipe.CameraParameterDefaults
import com.retrocam.app.recipe.CameraSpec
import com.retrocam.app.recipe.DateStampSpec
import com.retrocam.app.recipe.FinalRecipe
import com.retrocam.app.recipe.RecipeCatalogLoader
import com.retrocam.app.recipe.RecipeValidator
import com.retrocam.app.recipe.SensorNoiseSpec
import com.retrocam.app.recipe.ToneSpec
import com.retrocam.app.recipe.WhiteBalanceSpec
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class CalibrationExporterTest {

    private fun sampleArchetype() = ArchetypeSpec(
        schemaVersion = 2,
        id = "ccd_compact_2000s",
        displayName = "Kompak Digital CCD Awal 2000-an",
        era = "2000s",
        summary = "Draft archetype",
        typicalTraits = listOf("Kontras tegas"),
        calibrationStatus = "draft",
        lookTags = listOf("high-contrast", "clipped-highlights"),
        cameraDefaults = CameraParameterDefaults(
            aspectRatio = "4:3",
            whiteBalance = WhiteBalanceSpec(0.08f, 0.02f),
            tone = ToneSpec(1.10f, 0.22f, 0.02f),
            saturation = 1.08f,
            sensorNoise = SensorNoiseSpec(0.20f, 0.42f),
            sharpen = 0.35f,
            dateStamp = DateStampSpec(true, false, "yy MM dd", "#FF8C1A"),
            bodyOptics = CameraBodyOpticsSpec(),
        ),
    )

    private fun sampleCamera() = CameraSpec(
        schemaVersion = 4,
        id = "compact_2003_ccd",
        brand = "RetroCam",
        model = "Kompak CCD 2003",
        aliases = listOf("CCD 2003"),
        displayName = "Kompak CCD 2003",
        era = "2000s",
        category = "digital_compact",
        archetype = "ccd_compact_2000s",
        lookTags = listOf("high-contrast"),
        confidenceLook = "high",
        confidenceSpecs = "high",
        sources = listOf("test"),
        calibrationStatus = "draft",
        iconType = "compact_digital",
        tuningNotes = "Catatan kalibrasi manusia.",
    )

    @Test
    fun encodeCalibratedArchetypeJson_marksCalibratedAndReloadsCleanly() {
        val arch = sampleArchetype()
        val tuned = CalibrationJsonBuilder.archetypeToPreviewRecipe(arch).copy(
            temp = 0.14f,
            contrast = 1.22f,
            vignetteStrength = 0.16f,
            aspectRatio = "3:2",
        )

        val jsonText = CalibrationJsonBuilder.encodeCalibratedArchetypeJson(arch, tuned)
        assertTrue(jsonText.contains("\"calibrationStatus\": \"calibrated\""))

        val reloaded = RecipeCatalogLoader.loadFromRaw(
            camerasRaw = emptyMap(),
            lensesRaw = emptyMap(),
            filmsRaw = emptyMap(),
            archetypesRaw = mapOf("archetypes/ccd_compact_2000s.json" to jsonText),
        )
        assertTrue("Reload errors: ${reloaded.loadErrors}", reloaded.loadErrors.isEmpty())
        val parsed = reloaded.archetypes.single()
        assertEquals("calibrated", parsed.calibrationStatus)
        assertEquals(2, parsed.schemaVersion)
        assertEquals("3:2", parsed.cameraDefaults!!.aspectRatio)
        assertEquals(0.14f, parsed.cameraDefaults!!.whiteBalance.temp, 1e-4f)
        assertEquals(1.22f, parsed.cameraDefaults!!.tone.contrast, 1e-4f)
        assertEquals(0.16f, parsed.cameraDefaults!!.bodyOptics.vignetteStrength, 1e-4f)
    }

    @Test
    fun encodeCalibratedCameraJson_preservesMetadataAndClampsOutOfRangeValues() {
        val cam = sampleCamera()
        val arch = sampleArchetype()
        val extremeRecipe = FinalRecipe(
            temp = 3.0f,
            tint = -3.0f,
            contrast = 9.0f,
            saturation = 5.0f,
            highlightRolloff = 2.0f,
            shadowLift = 0.9f,
            aspectRatio = "21:9", // unsupported -> falls back to 4:3
            softness = 1.5f,
            vignetteStrength = -0.2f,
            combinedNoiseAmount = 1.2f,
            sensorNoiseChroma = 0.45f,
            sharpen = 0.5f,
            dateStampAvailable = true,
            dateStampEnabled = true,
            dateStampColorHex = "not-hex", // invalid -> falls back to archetype color
            lightLeakIntensity = 0.12f,
        )

        val jsonText = CalibrationJsonBuilder.encodeCalibratedCameraJson(cam, extremeRecipe, arch)
        val reloaded = RecipeCatalogLoader.loadFromRaw(
            camerasRaw = mapOf("cameras/compact_2003_ccd.json" to jsonText),
            lensesRaw = emptyMap(),
            filmsRaw = emptyMap(),
            archetypesRaw = emptyMap(),
        )
        assertTrue("Reload errors: ${reloaded.loadErrors}", reloaded.loadErrors.isEmpty())
        val parsed = reloaded.cameras.single()
        assertEquals("calibrated", parsed.calibrationStatus)
        assertEquals(4, parsed.schemaVersion)
        assertEquals("compact_2003_ccd", parsed.id)
        assertEquals("RetroCam", parsed.brand)
        assertEquals("Kompak CCD 2003", parsed.model)
        assertEquals(listOf("CCD 2003"), parsed.aliases)
        assertEquals("compact_digital", parsed.iconType)
        assertEquals("Catatan kalibrasi manusia.", parsed.tuningNotes)
        assertEquals("4:3", parsed.aspectRatio)
        assertEquals(1.0f, parsed.whiteBalance!!.temp, 1e-4f)
        assertEquals(2.0f, parsed.tone!!.contrast, 1e-4f)
        assertEquals(0.4f, parsed.tone!!.shadowLift, 1e-4f)
        assertEquals("#FF8C1A", parsed.dateStamp!!.color)
        assertTrue(RecipeValidator.validateCamera(parsed).isEmpty())
    }

    @Test
    fun buildExportFileName_sanitizesSpecialCharacters() {
        val fileName = CalibrationJsonBuilder.buildExportFileName(
            kind = "Archetype",
            id = "CCD Compact / 2000s!",
            timestamp = "20261127_205500",
        )
        assertEquals("archetype_ccd_compact_2000s_calibrated_20261127_205500.json", fileName)
    }
}
