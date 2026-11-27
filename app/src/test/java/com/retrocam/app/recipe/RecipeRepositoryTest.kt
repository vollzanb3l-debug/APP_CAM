package com.retrocam.app.recipe

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.File

class RecipeRepositoryTest {

    @Test
    fun bundled100CamerasAndAssets_loadCleanlyWithoutErrorsAndInheritArchetypes() {
        val camerasRaw = readAssetsDir("cameras")
        val lensesRaw = readAssetsDir("lenses")
        val filmsRaw = readAssetsDir("films")
        val archetypesRaw = readAssetsDir("archetypes")

        val catalog = RecipeCatalogLoader.loadFromRaw(
            camerasRaw = camerasRaw,
            lensesRaw = lensesRaw,
            filmsRaw = filmsRaw,
            archetypesRaw = archetypesRaw,
        )

        assertTrue("Unexpected load errors: ${catalog.loadErrors}", catalog.loadErrors.isEmpty())
        assertEquals(100, catalog.cameras.size)
        assertEquals(3, catalog.lenses.size)
        assertEquals(3, catalog.films.size)
        assertEquals(18, catalog.archetypes.size)

        val archetypeIds = catalog.archetypes.map { it.id }.toSet()
        val uniqueCameraIds = catalog.cameras.map { it.id }.toSet()
        assertEquals(100, uniqueCameraIds.size)

        for (camera in catalog.cameras) {
            assertEquals(CameraSchemaMigrator.CURRENT_SCHEMA_VERSION, camera.schemaVersion)
            assertTrue(camera.brand.isNotBlank())
            assertTrue(camera.model.isNotBlank())
            assertTrue(camera.iconType in RecipeValidator.allowedIconTypes)
            assertTrue(
                "Camera ${camera.id} references missing archetype ${camera.archetype}",
                camera.archetype in archetypeIds,
            )
            assertEquals("draft", camera.calibrationStatus)
        }
        for (film in catalog.films) {
            assertTrue(
                "Film ${film.id} references missing archetype ${film.archetype}",
                film.archetype in archetypeIds,
            )
            assertEquals("draft", film.calibrationStatus)
        }

        // Verify archetype inheritance resolves cleanly across distinct camera formats
        val lens = catalog.lenses.first()
        val film = catalog.films.first()
        val sd1000 = catalog.cameras.first { it.id == "canon_powershot_sd1000" }
        val penEe3 = catalog.cameras.first { it.id == "olympus_pen_ee_3" }
        val holga = catalog.cameras.first { it.id == "holga_120n" }
        val ae1 = catalog.cameras.first { it.id == "canon_ae_1" }

        assertEquals("4:3", RecipeCompositor.compose(sd1000, lens, film, catalog.archetypes).aspectRatio)
        assertEquals("3:4", RecipeCompositor.compose(penEe3, lens, film, catalog.archetypes).aspectRatio)
        assertEquals("1:1", RecipeCompositor.compose(holga, lens, film, catalog.archetypes).aspectRatio)
        assertEquals("3:2", RecipeCompositor.compose(ae1, lens, film, catalog.archetypes).aspectRatio)
    }

    @Test
    fun brokenJsonAndInvalidValues_areSkippedWithoutCrashing() {
        val validSingleCamera = """
            {
              "schemaVersion": 4,
              "id": "valid_single_cam",
              "brand": "Canon",
              "model": "PowerShot SD1000",
              "aliases": [],
              "displayName": "PowerShot SD1000",
              "era": "2000s",
              "category": "digital_compact",
              "archetype": "ccd_compact_2000s",
              "confidenceLook": "high",
              "confidenceSpecs": "high",
              "sources": ["https://global.canon/en/c-museum/product/dcc539.html"],
              "calibrationStatus": "draft",
              "iconType": "compact_digital"
            }
        """.trimIndent()
        val validLensRaw = readAssetsDir("lenses").entries.first()
        val validFilmRaw = readAssetsDir("films").entries.first()

        val camerasRaw = mapOf(
            "cameras/valid_single_cam.json" to validSingleCamera,
            "cameras/corrupt.json" to "{ not valid json ",
            "cameras/bad_schema.json" to validSingleCamera.replaceFirst(
                Regex("\"schemaVersion\"\\s*:\\s*\\d+"),
                "\"schemaVersion\": 99",
            ),
        )
        val lensesRaw = mapOf(
            validLensRaw.key to validLensRaw.value,
            "lenses/bad_range.json" to validLensRaw.value.replaceFirst(
                Regex("\"softness\"\\s*:\\s*[0-9.]+"),
                "\"softness\": 9.0",
            ),
        )
        val filmsRaw = mapOf(
            validFilmRaw.key to validFilmRaw.value,
            "films/bad_hex.json" to validFilmRaw.value.replaceFirst(
                Regex("#[0-9A-Fa-f]{6}"),
                "not-a-color",
            ),
        )

        val catalog = RecipeCatalogLoader.loadFromRaw(
            camerasRaw = camerasRaw,
            lensesRaw = lensesRaw,
            filmsRaw = filmsRaw,
            archetypesRaw = emptyMap(),
        )

        assertEquals(1, catalog.cameras.size)
        assertEquals(1, catalog.lenses.size)
        assertEquals(1, catalog.films.size)
        assertEquals(4, catalog.loadErrors.size)
        val errorPaths = catalog.loadErrors.map { it.assetPath }.toSet()
        assertTrue("cameras/corrupt.json" in errorPaths)
        assertTrue("cameras/bad_schema.json" in errorPaths)
        assertTrue("lenses/bad_range.json" in errorPaths)
        assertTrue("films/bad_hex.json" in errorPaths)
    }

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
}
