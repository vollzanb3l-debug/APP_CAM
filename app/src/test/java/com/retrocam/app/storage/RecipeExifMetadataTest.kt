package com.retrocam.app.storage

import com.retrocam.app.recipe.BloomSpec
import com.retrocam.app.recipe.CameraSpec
import com.retrocam.app.recipe.DateStampSpec
import com.retrocam.app.recipe.DistortionSpec
import com.retrocam.app.recipe.FilmGradeSpec
import com.retrocam.app.recipe.FilmSpec
import com.retrocam.app.recipe.GrainSpec
import com.retrocam.app.recipe.HalationSpec
import com.retrocam.app.recipe.LensSpec
import com.retrocam.app.recipe.RecipeCatalog
import com.retrocam.app.recipe.SensorNoiseSpec
import com.retrocam.app.recipe.ToneSpec
import com.retrocam.app.recipe.VignetteSpec
import com.retrocam.app.recipe.WhiteBalanceSpec
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class RecipeExifMetadataTest {

    @Test
    fun encodeAndDecodeRoundTripPreservesAllFields() {
        val original = RecipeExifMetadata(
            schemaVersion = RecipeExifMetadata.CURRENT_SCHEMA_VERSION,
            cameraId = "compact_2003_ccd",
            lensId = "lens_35_f2",
            filmId = "film_surya_200",
            recipeVersion = "c3.l1.f2",
            cameraSchemaVersion = 3,
            lensSchemaVersion = 1,
            filmSchemaVersion = 2,
        )

        val encoded = RecipeExifMetadata.encode(original)
        assertTrue(encoded.contains("\"schemaVersion\":1"))
        assertTrue(encoded.contains("\"recipeVersion\":\"c3.l1.f2\""))

        val decoded = RecipeExifMetadata.decode(encoded)
        assertEquals(original, decoded)
    }

    @Test
    fun decodeHandlesExifCharsetPrefixAndNullPadding() {
        val original = RecipeExifMetadata(
            cameraId = "classic_s_35mm",
            lensId = "lens_50_f14",
            filmId = "film_monokrom_400",
            recipeVersion = "c3.l1.f2",
            cameraSchemaVersion = 3,
            lensSchemaVersion = 1,
            filmSchemaVersion = 2,
        )
        val rawWithExifPrefix = "ASCII\u0000\u0000\u0000${RecipeExifMetadata.encode(original)}\u0000\u0000"
        val decoded = RecipeExifMetadata.decode(rawWithExifPrefix)
        assertEquals(original, decoded)
    }

    @Test
    fun decodeReturnsNullSafelyForPhotosWithoutMetadataOrCorruptComments() {
        assertNull(RecipeExifMetadata.decode(null))
        assertNull(RecipeExifMetadata.decode(""))
        assertNull(RecipeExifMetadata.decode("   "))
        assertNull(RecipeExifMetadata.decode("ASCII\u0000\u0000\u0000Shot on another camera"))
        assertNull(RecipeExifMetadata.decode("{not-valid-json"))
        assertNull(RecipeExifMetadata.decode("{\"schemaVersion\":1,\"cameraId\":\"\",\"lensId\":\"a\",\"filmId\":\"b\",\"recipeVersion\":\"c1\"}"))
        assertNull(RecipeExifMetadata.decode("{\"schemaVersion\":0,\"cameraId\":\"a\",\"lensId\":\"b\",\"filmId\":\"c\",\"recipeVersion\":\"c1\"}"))
    }

    @Test
    fun fromSpecsBuildsMetadataWithRecipeSchemaVersions() {
        val catalog = sampleCatalog()
        val metadata = RecipeExifMetadata.fromSpecs(
            camera = catalog.cameras.first(),
            lens = catalog.lenses.first(),
            film = catalog.films.first(),
        )
        assertNotNull(metadata)
        assertEquals("compact_2003_ccd", metadata!!.cameraId)
        assertEquals("lens_35_f2", metadata.lensId)
        assertEquals("film_surya_200", metadata.filmId)
        assertEquals(RecipeExifMetadata.CURRENT_SCHEMA_VERSION, metadata.schemaVersion)
        assertEquals("c3.l1.f2", metadata.recipeVersion)
        assertEquals(3, metadata.cameraSchemaVersion)
        assertEquals(1, metadata.lensSchemaVersion)
        assertEquals(2, metadata.filmSchemaVersion)

        assertNull(RecipeExifMetadata.fromSpecs(null, catalog.lenses.first(), catalog.films.first()))
    }

    @Test
    fun formatDisplayLabelResolvesCatalogNamesAndFallbacksSafely() {
        val catalog = sampleCatalog()

        val known = RecipeExifMetadata(
            cameraId = "compact_2003_ccd",
            lensId = "lens_35_f2",
            filmId = "film_surya_200",
            recipeVersion = "c3.l1.f2",
        )
        assertEquals(
            "Kompak CCD 2003 - 35mm f2 - Surya 200",
            RecipeExifMetadata.formatDisplayLabel(known, catalog),
        )

        val unknownIds = RecipeExifMetadata(
            cameraId = "future_cam_99",
            lensId = "lens_35_f2",
            filmId = "old_film_x",
            recipeVersion = "c4.l1.f2",
        )
        assertEquals(
            "future_cam_99 - 35mm f2 - old_film_x",
            RecipeExifMetadata.formatDisplayLabel(unknownIds, catalog),
        )

        assertEquals(
            RecipeExifMetadata.FALLBACK_NO_METADATA_LABEL,
            RecipeExifMetadata.formatDisplayLabel(null, catalog),
        )
    }

    private fun sampleCatalog(): RecipeCatalog {
        val camera = CameraSpec(
            schemaVersion = 3,
            id = "compact_2003_ccd",
            displayName = "Kompak CCD 2003",
            inspiredBy = "test",
            era = "2000s",
            category = "digital_compact",
            archetype = "ccd_compact_2000s",
            confidenceLook = "high",
            confidenceSpecs = "high",
            sources = listOf("test"),
            calibrationStatus = "draft",
            icon = "compact_digital",
            aspectRatio = "4:3",
            whiteBalance = WhiteBalanceSpec(0f, 0f),
            tone = ToneSpec(1f, 0f, 0f),
            saturation = 1f,
            sensorNoise = SensorNoiseSpec(0f, 0f),
            sharpen = 0f,
            dateStamp = DateStampSpec(true, false, "yy_mm_dd", "#FF7A00"),
            notes = "test",
        )
        val lens = LensSpec(
            schemaVersion = 1,
            id = "lens_35_f2",
            displayName = "35mm f2",
            icon = "lens",
            focalLengthEquiv = 35,
            softness = 0f,
            bloom = BloomSpec(0f, 0.85f, 0f),
            vignette = VignetteSpec(0f, 0.7f),
            distortion = DistortionSpec(0f),
            chromaticAberration = 0f,
            flare = 0f,
            notes = "test",
        )
        val film = FilmSpec(
            schemaVersion = 2,
            id = "film_surya_200",
            displayName = "Surya 200",
            archetype = "warm_golden_film",
            confidenceLook = "high",
            confidenceSpecs = "high",
            sources = listOf("test"),
            calibrationStatus = "draft",
            icon = "film",
            lut = null,
            grade = FilmGradeSpec(0f, 0f, 1f, 1f, 0f, "#FFFFFF", "#000000", 0f),
            grain = GrainSpec(0f, 0.5f, 0.5f),
            halation = HalationSpec(0f, "#FF5500"),
            notes = "test",
        )
        return RecipeCatalog(
            cameras = listOf(camera),
            lenses = listOf(lens),
            films = listOf(film),
            archetypes = emptyList(),
            loadErrors = emptyList(),
        )
    }
}
