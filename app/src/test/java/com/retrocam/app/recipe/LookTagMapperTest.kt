package com.retrocam.app.recipe

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.File

class LookTagMapperTest {

    private fun readAssetFile(relativePath: String): File {
        val candidates = listOf(
            File("src/main/assets/$relativePath"),
            File("app/src/main/assets/$relativePath"),
        )
        return candidates.firstOrNull { it.isFile }
            ?: error("Could not find asset $relativePath from ${File(".").absolutePath}")
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

    @Test
    fun lookTagMapAsset_parsesValidatesAndRemainsDraft() {
        val raw = readAssetFile("look_tag_map.json").readText()
        val tagMap = LookTagMapper.parse(raw)

        val errors = LookTagMapper.validate(tagMap)
        assertTrue("Unexpected look_tag_map validation errors: $errors", errors.isEmpty())
        assertEquals("draft", tagMap.calibrationStatus)

        // Core qualitative tags explicitly requested must be mapped.
        val requiredTags = listOf(
            "soft-edges",
            "heavy-vignette",
            "film-like-grain",
            "clipped-highlights",
            "highlight-bloom",
            "light-leak",
            "purple-fringing",
        )
        for (tag in requiredTags) {
            assertTrue("Missing required tag in look_tag_map.json: $tag", tag in tagMap.tags)
        }

        val baseline = LookTagMapper.neutralBaseline()
        val soft = LookTagMapper.applyTags(baseline, listOf("soft-edges"), tagMap)
        assertTrue(soft.bodyOptics.softness > baseline.bodyOptics.softness)

        val vig = LookTagMapper.applyTags(baseline, listOf("heavy-vignette"), tagMap)
        assertTrue(vig.bodyOptics.vignetteStrength > baseline.bodyOptics.vignetteStrength)

        val clipped = LookTagMapper.applyTags(baseline, listOf("clipped-highlights"), tagMap)
        assertTrue(clipped.tone.highlightRolloff < baseline.tone.highlightRolloff)

        val grain = LookTagMapper.applyTags(baseline, listOf("film-like-grain"), tagMap)
        assertTrue(grain.sensorNoise.amount > baseline.sensorNoise.amount)
        assertTrue(grain.sensorNoise.chroma < baseline.sensorNoise.chroma)
    }

    @Test
    fun applyTags_ignoresUnknownTagsAndClampsExtremeStacksSafely() {
        val tagMap = LookTagMapper.parse(readAssetFile("look_tag_map.json").readText())
        val base = LookTagMapper.neutralBaseline()

        val withUnknown = LookTagMapper.applyTags(base, listOf("unknown-future-tag-xyz"), tagMap)
        assertEquals(base, withUnknown)

        val extremeStack = List(25) { "heavy-vignette" } + List(25) { "clipped-highlights" }
        val clamped = LookTagMapper.applyTags(base, extremeStack, tagMap)
        val dummyArchetype = ArchetypeSpec(
            schemaVersion = 2,
            id = "clamped_test",
            displayName = "Clamped",
            era = "2000s",
            summary = "Test",
            typicalTraits = listOf("clamped"),
            calibrationStatus = "draft",
            cameraDefaults = clamped,
        )
        assertTrue(
            "Clamped defaults failed validation: ${RecipeValidator.validateArchetype(dummyArchetype)}",
            RecipeValidator.validateArchetype(dummyArchetype).isEmpty(),
        )
    }

    @Test
    fun all15CatalogArchetypes_existAsDraftAndMatchLookTagMapDerivation() {
        val tagMap = LookTagMapper.parse(readAssetFile("look_tag_map.json").readText())
        val indexFile = listOf(
            File("docs/catalog/json/catalog_index.json"),
            File("../docs/catalog/json/catalog_index.json"),
        ).firstOrNull { it.isFile }
            ?: error("Could not locate docs/catalog/json/catalog_index.json")

        val allowedArchetypes = Json.parseToJsonElement(indexFile.readText())
            .jsonObject["allowedArchetypes"]!!
            .jsonArray
            .map { it.jsonPrimitive.content }
        assertEquals(15, allowedArchetypes.size)

        val catalog = RecipeCatalogLoader.loadFromRaw(
            camerasRaw = emptyMap(),
            lensesRaw = emptyMap(),
            filmsRaw = emptyMap(),
            archetypesRaw = readAssetsDir("archetypes"),
        )
        assertTrue("Archetype load errors: ${catalog.loadErrors}", catalog.loadErrors.isEmpty())

        val byId = catalog.archetypes.associateBy { it.id }
        for (archId in allowedArchetypes) {
            val spec = byId[archId]
            assertNotNull("Missing camera archetype asset: $archId", spec)
            spec!!
            assertEquals("Archetype $archId must remain draft", "draft", spec.calibrationStatus)
            assertTrue("Archetype $archId must declare lookTags", spec.lookTags.isNotEmpty())
            val defaults = spec.cameraDefaults
            assertNotNull("Archetype $archId must provide cameraDefaults", defaults)
            defaults!!

            val derived = LookTagMapper.deriveDraftDefaults(
                baseAspectRatio = defaults.aspectRatio,
                lookTags = spec.lookTags,
                tagMap = tagMap,
                dateStampColor = defaults.dateStamp.color,
            )
            assertEquals("Archetype $archId defaults drifted from look_tag_map.json", derived, defaults)
        }
    }
}
