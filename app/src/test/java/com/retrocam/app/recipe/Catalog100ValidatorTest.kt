package com.retrocam.app.recipe

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.File

class Catalog100ValidatorTest {

    private fun readAssetsDir(folder: String): Map<String, String> {
        val candidates = listOf(
            File("src/main/assets/$folder"),
            File("app/src/main/assets/$folder"),
        )
        val dir = candidates.firstOrNull { it.isDirectory }
            ?: error("Could not find assets/$folder from ${File(".").absolutePath}")
        return dir.listFiles()
            ?.filter { it.isFile && it.extension == "json" }
            ?.sortedBy { it.name }
            ?.associate { "$folder/${it.name}" to it.readText() }
            ?: emptyMap()
    }

    @Test
    fun catalog_has100UniqueIdsValidArchetypesAndNoBlankRequiredFields() {
        val catalog = RecipeCatalogLoader.loadFromRaw(
            camerasRaw = readAssetsDir("cameras"),
            lensesRaw = readAssetsDir("lenses"),
            filmsRaw = readAssetsDir("films"),
            archetypesRaw = readAssetsDir("archetypes"),
        )

        assertTrue("Unexpected load errors: ${catalog.loadErrors}", catalog.loadErrors.isEmpty())
        assertEquals(100, catalog.cameras.size)
        assertEquals(100, catalog.cameras.map { it.id }.toSet().size)

        val catalogErrors = RecipeValidator.validateCatalog(catalog, expectedCameraCount = 100)
        assertTrue("Catalog validation errors: $catalogErrors", catalogErrors.isEmpty())

        val lens = catalog.lenses.first()
        val film = catalog.films.first()
        for (camera in catalog.cameras) {
            assertTrue("Camera ${camera.id} missing lookTags", camera.lookTags.size in 3..6)
            assertTrue("Camera ${camera.id} has blank tuning_notes", camera.tuningNotes.isNotBlank())
            val composed = RecipeCompositor.compose(camera, lens, film, catalog.archetypes)
            assertTrue(composed.aspectRatio.isNotBlank())
            assertTrue(composed.contrast in 0.5f..2.0f)
        }
    }

    @Test
    fun validateCatalog_detectsDuplicateIdsInvalidArchetypesAndBlankRequiredFields() {
        val validArch = ArchetypeSpec(
            schemaVersion = 2,
            id = "ccd_compact_2000s",
            displayName = "CCD Compact",
            era = "2000s",
            summary = "Draft",
            typicalTraits = listOf("punchy"),
            calibrationStatus = "draft",
            cameraDefaults = RecipeCompositor.FALLBACK_CAMERA_DEFAULTS,
        )
        val filmOnlyArch = ArchetypeSpec(
            schemaVersion = 2,
            id = "warm_golden_film",
            displayName = "Warm Film",
            era = "1990s",
            summary = "Film archetype",
            typicalTraits = listOf("warm"),
            calibrationStatus = "draft",
            cameraDefaults = null,
        )
        val baseCam = CameraSpec(
            schemaVersion = 4,
            id = "canon_powershot_sd1000",
            brand = "Canon",
            model = "PowerShot SD1000",
            displayName = "PowerShot SD1000",
            era = "2000s",
            category = "digital_compact",
            archetype = "ccd_compact_2000s",
            lookTags = listOf("high-contrast"),
            confidenceLook = "high",
            confidenceSpecs = "high",
            sources = listOf("https://global.canon/en/c-museum/product/dcc539.html"),
            calibrationStatus = "draft",
            iconType = "compact_digital",
        )

        val brokenCatalog = RecipeCatalog(
            cameras = listOf(
                baseCam,
                baseCam, // duplicate ID
                baseCam.copy(id = "cam_bad_arch", archetype = "nonexistent_archetype"),
                baseCam.copy(id = "cam_film_arch", archetype = "warm_golden_film"),
                baseCam.copy(id = "cam_blank_fields", brand = "", model = " ", lookTags = listOf("ok", "")),
            ),
            lenses = emptyList(),
            films = emptyList(),
            archetypes = listOf(validArch, filmOnlyArch),
            loadErrors = emptyList(),
        )

        val errors = RecipeValidator.validateCatalog(brokenCatalog, expectedCameraCount = 100)
        assertTrue(errors.any { it.contains("expected 100") })
        assertTrue(errors.any { it.contains("duplicate camera id: canon_powershot_sd1000") })
        assertTrue(errors.any { it.contains("unknown archetype: nonexistent_archetype") })
        assertTrue(errors.any { it.contains("without cameraDefaults: warm_golden_film") })
        assertTrue(errors.any { it.contains("camera.brand must not be blank") })
        assertTrue(errors.any { it.contains("camera.model must not be blank") })
        assertTrue(errors.any { it.contains("camera.lookTags must not contain blank entries") })
    }

    @Test
    fun bundled100Cameras_loadTimeStaysFastWithoutSignificantRegression() {
        val camerasRaw = readAssetsDir("cameras")
        val lensesRaw = readAssetsDir("lenses")
        val filmsRaw = readAssetsDir("films")
        val archetypesRaw = readAssetsDir("archetypes")

        // Warm up kotlinx.serialization classloading/JIT once before measuring
        val warmup = RecipeCatalogLoader.loadFromRaw(camerasRaw, lensesRaw, filmsRaw, archetypesRaw)
        assertEquals(100, warmup.cameras.size)

        val fullDurationsMs = LongArray(5) {
            val start = System.nanoTime()
            val c = RecipeCatalogLoader.loadFromRaw(camerasRaw, lensesRaw, filmsRaw, archetypesRaw)
            assertEquals(100, c.cameras.size)
            assertTrue(c.loadTimeMs >= 0L)
            (System.nanoTime() - start) / 1_000_000L
        }
        fullDurationsMs.sort()
        val medianFullMs = fullDurationsMs[fullDurationsMs.size / 2]

        // Baseline: loading without the 100-camera bundle (archetypes + lenses + films only)
        val baseDurationsMs = LongArray(5) {
            val start = System.nanoTime()
            RecipeCatalogLoader.loadFromRaw(emptyMap(), lensesRaw, filmsRaw, archetypesRaw)
            (System.nanoTime() - start) / 1_000_000L
        }
        baseDurationsMs.sort()
        val medianBaseMs = baseDurationsMs[baseDurationsMs.size / 2]

        val addedMs = (medianFullMs - medianBaseMs).coerceAtLeast(0L)
        assertTrue(
            "100-camera catalog load time too slow: full=${medianFullMs}ms, base=${medianBaseMs}ms, delta=${addedMs}ms",
            medianFullMs < 500L && addedMs < 250L,
        )
    }
}
