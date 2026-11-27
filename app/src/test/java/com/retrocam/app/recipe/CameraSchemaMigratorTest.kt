package com.retrocam.app.recipe

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.File

class CameraSchemaMigratorTest {

    private val legacyV3CompactJson = """
        {
          "schemaVersion": 3,
          "id": "compact_2003_ccd",
          "displayName": "Kompak CCD 2003",
          "inspiredBy": "Kamera saku CCD awal 2000-an",
          "era": "2000s",
          "category": "digital_compact",
          "archetype": "ccd_compact_2000s",
          "confidenceLook": "high",
          "confidenceSpecs": "high",
          "sources": ["Panduan kualitatif ccd_compact_2000s"],
          "calibrationStatus": "draft",
          "icon": "icons/cameras/compact_generic.png",
          "aspectRatio": "4:3",
          "whiteBalance": { "temp": 0.08, "tint": 0.02 },
          "tone": { "contrast": 1.10, "highlightRolloff": 0.22, "shadowLift": 0.02 },
          "saturation": 1.08,
          "sensorNoise": { "amount": 0.20, "chroma": 0.42 },
          "sharpen": 0.35,
          "dateStamp": { "available": true, "defaultOn": true, "format": "yy MM dd", "color": "#FF8C1A" },
          "notes": "Perkiraan awal"
        }
    """.trimIndent()

    private val legacyV3SlrJson = """
        {
          "schemaVersion": 3,
          "id": "classic_s_35mm",
          "displayName": "Klasik 35mm",
          "inspiredBy": "SLR 35mm klasik",
          "era": "1980s",
          "category": "film_35mm",
          "archetype": "classic_35mm_slr",
          "confidenceLook": "medium",
          "confidenceSpecs": "high",
          "sources": ["Panduan kualitatif classic_35mm_slr"],
          "calibrationStatus": "draft",
          "icon": "icons/cameras/classic_35mm.png",
          "aspectRatio": "3:2",
          "whiteBalance": { "temp": 0.02, "tint": 0.00 },
          "tone": { "contrast": 1.02, "highlightRolloff": 0.30, "shadowLift": 0.01 },
          "saturation": 1.00,
          "sensorNoise": { "amount": 0.04, "chroma": 0.10 },
          "sharpen": 0.15,
          "dateStamp": { "available": true, "defaultOn": false, "format": "yy MM dd", "color": "#FF8C1A" },
          "notes": "Bodi netral"
        }
    """.trimIndent()

    @Test
    fun migrateIfNeeded_upgradesV3ToV4PreservingIdAndInferringIconType() {
        val compactResult = CameraSchemaMigrator.migrateIfNeeded(legacyV3CompactJson)
        assertTrue(compactResult.migrated)
        assertEquals(3, compactResult.fromVersion)
        assertEquals(4, compactResult.toVersion)

        val slrResult = CameraSchemaMigrator.migrateIfNeeded(legacyV3SlrJson)
        assertTrue(slrResult.migrated)

        val catalog = RecipeCatalogLoader.loadFromRaw(
            camerasRaw = mapOf(
                "cameras/compact_2003_ccd.json" to legacyV3CompactJson,
                "cameras/classic_s_35mm.json" to legacyV3SlrJson,
            ),
            lensesRaw = emptyMap(),
            filmsRaw = emptyMap(),
            archetypesRaw = emptyMap(),
        )

        assertTrue("Expected no load errors, got: ${catalog.loadErrors}", catalog.loadErrors.isEmpty())
        assertEquals(2, catalog.cameras.size)

        val compact = catalog.cameras.first { it.id == "compact_2003_ccd" }
        assertEquals(4, compact.schemaVersion)
        assertEquals("compact_2003_ccd", compact.id)
        assertEquals(CameraSchemaMigrator.DEFAULT_LEGACY_BRAND, compact.brand)
        assertEquals("Kompak CCD 2003", compact.model)
        assertEquals("Kompak CCD 2003", compact.displayName)
        assertTrue(compact.aliases.isEmpty())
        assertEquals("compact_digital", compact.iconType)
        assertEquals("ccd_compact_2000s", compact.archetype)
        assertEquals(1.10f, compact.tone.contrast, 1e-5f)

        val slr = catalog.cameras.first { it.id == "classic_s_35mm" }
        assertEquals(4, slr.schemaVersion)
        assertEquals("classic_s_35mm", slr.id)
        assertEquals("slr_film", slr.iconType)
        assertEquals("classic_35mm_slr", slr.archetype)
    }

    @Test
    fun migrateIfNeeded_passesV4ThroughUnchangedWithAliasesAndBrand() {
        val v4Json = """
            {
              "schemaVersion": 4,
              "id": "canon_powershot_sd1000",
              "brand": "Canon",
              "model": "PowerShot SD1000",
              "aliases": ["Digital IXUS 70", "IXY Digital 10"],
              "displayName": "PowerShot SD1000 (Digital IXUS 70 / IXY Digital 10)",
              "era": "2000s",
              "category": "digital_compact",
              "archetype": "ccd_compact_2000s",
              "lookTags": ["high-contrast", "punchy-daylight", "digital-noise"],
              "confidenceLook": "high",
              "confidenceSpecs": "high",
              "sources": ["https://global.canon/en/c-museum/product/dcc539.html"],
              "calibrationStatus": "draft",
              "iconType": "compact_digital",
              "aspectRatio": "4:3",
              "whiteBalance": { "temp": 0.08, "tint": 0.02 },
              "tone": { "contrast": 1.10, "highlightRolloff": 0.22, "shadowLift": 0.02 },
              "saturation": 1.08,
              "sensorNoise": { "amount": 0.20, "chroma": 0.42 },
              "sharpen": 0.35,
              "dateStamp": { "available": true, "defaultOn": true, "format": "yy MM dd", "color": "#FF8C1A" },
              "tuning_notes": "Karakter CCD bawaan."
            }
        """.trimIndent()

        val result = CameraSchemaMigrator.migrateIfNeeded(v4Json)
        assertFalse(result.migrated)
        assertEquals(4, result.fromVersion)
        assertEquals(4, result.toVersion)

        val catalog = RecipeCatalogLoader.loadFromRaw(
            camerasRaw = mapOf("cameras/canon_powershot_sd1000.json" to v4Json),
            lensesRaw = emptyMap(),
            filmsRaw = emptyMap(),
            archetypesRaw = emptyMap(),
        )

        assertTrue("Unexpected errors: ${catalog.loadErrors}", catalog.loadErrors.isEmpty())
        val cam = catalog.cameras.single()
        assertEquals("canon_powershot_sd1000", cam.id)
        assertEquals("Canon", cam.brand)
        assertEquals("PowerShot SD1000", cam.model)
        assertEquals(listOf("Digital IXUS 70", "IXY Digital 10"), cam.aliases)
        assertEquals("compact_digital", cam.iconType)
        assertEquals("Karakter CCD bawaan.", cam.tuningNotes)
    }

    @Test
    fun migrateIfNeeded_doesNotUpgradeUnsupportedSchemaVersions() {
        val v2Json = legacyV3CompactJson.replace("\"schemaVersion\": 3", "\"schemaVersion\": 2")
        val v99Json = legacyV3CompactJson.replace("\"schemaVersion\": 3", "\"schemaVersion\": 99")

        assertFalse(CameraSchemaMigrator.migrateIfNeeded(v2Json).migrated)
        assertFalse(CameraSchemaMigrator.migrateIfNeeded(v99Json).migrated)

        val catalog = RecipeCatalogLoader.loadFromRaw(
            camerasRaw = mapOf(
                "cameras/v2.json" to v2Json,
                "cameras/v99.json" to v99Json,
            ),
            lensesRaw = emptyMap(),
            filmsRaw = emptyMap(),
            archetypesRaw = emptyMap(),
        )
        assertTrue(catalog.cameras.isEmpty())
        assertEquals(2, catalog.loadErrors.size)
    }

    @Test
    fun catalogIndexJson_matchesV4SchemaIconTypesAndMetadataFields() {
        val candidates = listOf(
            File("docs/catalog/json/catalog_index.json"),
            File("../docs/catalog/json/catalog_index.json"),
        )
        val indexFile = candidates.firstOrNull { it.isFile }
            ?: error("Could not locate docs/catalog/json/catalog_index.json from ${File(".").absolutePath}")

        val root = Json.parseToJsonElement(indexFile.readText()).jsonObject
        val indexIconTypes = root["allowedIconTypes"]!!.jsonArray.map { it.jsonPrimitive.content }.toSet()
        assertEquals(RecipeValidator.allowedIconTypes, indexIconTypes)

        val cameras = root["cameras"]!!.jsonArray
        assertEquals(100, cameras.size)

        val seenIds = HashSet<String>()
        for (element in cameras) {
            val obj = element.jsonObject
            val id = obj["id"]!!.jsonPrimitive.content
            val brand = obj["brand"]!!.jsonPrimitive.content
            val model = obj["model"]!!.jsonPrimitive.content
            val displayName = obj["displayName"]!!.jsonPrimitive.content
            val iconType = obj["iconType"]!!.jsonPrimitive.content
            val archetype = obj["archetype"]!!.jsonPrimitive.content
            val aliases = obj["aliases"]!!.jsonArray

            assertTrue("Duplicate id in catalog_index.json: $id", seenIds.add(id))
            assertTrue("Blank brand for $id", brand.isNotBlank())
            assertTrue("Blank model for $id", model.isNotBlank())
            assertTrue("Blank displayName for $id", displayName.isNotBlank())
            assertTrue("Unsupported iconType '$iconType' for $id", iconType in RecipeValidator.allowedIconTypes)
            assertTrue("Blank archetype for $id", archetype.isNotBlank())
            assertTrue(
                "Blank alias entry for $id",
                aliases.none { it.jsonPrimitive.content.isBlank() },
            )
        }
    }
}
