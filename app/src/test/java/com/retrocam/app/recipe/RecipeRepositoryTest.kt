package com.retrocam.app.recipe

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.File

class RecipeRepositoryTest {

    @Test
    fun starterAssets_loadCleanlyWithoutErrorsAndReferenceExistingArchetypes() {
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
        assertEquals(3, catalog.cameras.size)
        assertEquals(3, catalog.lenses.size)
        assertEquals(3, catalog.films.size)
        assertEquals(7, catalog.archetypes.size)

        val archetypeIds = catalog.archetypes.map { it.id }.toSet()
        val cameraIds = catalog.cameras.map { it.id }.toSet()
        assertEquals(
            setOf("compact_2003_ccd", "dreamy_ccd_2008", "classic_s_35mm"),
            cameraIds,
        )
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

        val lens = catalog.lenses.first()
        val film = catalog.films.first()
        val compact = catalog.cameras.first { it.id == "compact_2003_ccd" }
        val dreamy = catalog.cameras.first { it.id == "dreamy_ccd_2008" }
        val classic = catalog.cameras.first { it.id == "classic_s_35mm" }

        val compactRecipe = RecipeCompositor.compose(compact, lens, film, catalog.archetypes)
        val dreamyRecipe = RecipeCompositor.compose(dreamy, lens, film, catalog.archetypes)
        val classicRecipe = RecipeCompositor.compose(classic, lens, film, catalog.archetypes)

        assertEquals("4:3", compactRecipe.aspectRatio)
        assertTrue(compactRecipe.dateStampDefaultOn)
        assertEquals("3:4", dreamyRecipe.aspectRatio)
        assertEquals("3:2", classicRecipe.aspectRatio)
    }

    @Test
    fun brokenJsonAndInvalidValues_areSkippedWithoutCrashing() {
        val validCameraRaw = readAssetsDir("cameras").entries.first()
        val validLensRaw = readAssetsDir("lenses").entries.first()
        val validFilmRaw = readAssetsDir("films").entries.first()

        val camerasRaw = mapOf(
            validCameraRaw.key to validCameraRaw.value,
            "cameras/corrupt.json" to "{ not valid json ",
            "cameras/bad_schema.json" to validCameraRaw.value.replaceFirst(
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
