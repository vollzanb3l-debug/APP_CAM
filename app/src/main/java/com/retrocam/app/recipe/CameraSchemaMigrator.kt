package com.retrocam.app.recipe

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.intOrNull
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

/**
 * Migrates legacy Camera JSON recipes (schemaVersion 3) to schemaVersion 4
 * without altering existing camera IDs.
 *
 * Schema v4 adds catalog metadata aligned with docs/catalog/json/catalog_index.json:
 * brand, model, aliases, displayName, iconType, archetype (plus optional lookTags & tuning_notes).
 */
object CameraSchemaMigrator {

    const val LEGACY_V3_SCHEMA_VERSION = 3
    const val CURRENT_SCHEMA_VERSION = 4
    const val DEFAULT_LEGACY_BRAND = "RetroCam"

    data class MigrationResult(
        val jsonText: String,
        val fromVersion: Int?,
        val toVersion: Int?,
        val migrated: Boolean,
    )

    private val json = Json {
        ignoreUnknownKeys = false
        encodeDefaults = true
    }

    /**
     * Migrates raw camera JSON from v3 to v4 if needed.
     * - v4 JSON is returned unchanged (`migrated = false`).
     * - v3 JSON is upgraded to v4 preserving `id` and all existing optical/sensor parameters.
     * - Unknown/unsupported versions (e.g. v2 or v99) or malformed JSON are returned untouched
     *   so downstream deserialization/validation reports the error cleanly.
     */
    fun migrateIfNeeded(rawJson: String): MigrationResult {
        val root = try {
            json.parseToJsonElement(rawJson).jsonObject
        } catch (_: Throwable) {
            return MigrationResult(rawJson, fromVersion = null, toVersion = null, migrated = false)
        }

        val version = root["schemaVersion"]?.jsonPrimitive?.intOrNull
            ?: return MigrationResult(rawJson, fromVersion = null, toVersion = null, migrated = false)

        if (version != LEGACY_V3_SCHEMA_VERSION) {
            return MigrationResult(rawJson, fromVersion = version, toVersion = version, migrated = false)
        }

        val upgraded = LinkedHashMap(root)
        upgraded["schemaVersion"] = JsonPrimitive(CURRENT_SCHEMA_VERSION)

        val displayName = root["displayName"]?.jsonPrimitive?.contentOrNull?.trim().orEmpty()
        val category = root["category"]?.jsonPrimitive?.contentOrNull?.trim().orEmpty()
        val archetype = root["archetype"]?.jsonPrimitive?.contentOrNull?.trim().orEmpty()

        val existingBrand = root["brand"]?.jsonPrimitive?.contentOrNull?.trim().orEmpty()
        if (existingBrand.isEmpty()) {
            upgraded["brand"] = JsonPrimitive(DEFAULT_LEGACY_BRAND)
        }

        val existingModel = root["model"]?.jsonPrimitive?.contentOrNull?.trim().orEmpty()
        if (existingModel.isEmpty()) {
            val fallbackModel = displayName.ifEmpty {
                root["id"]?.jsonPrimitive?.contentOrNull?.trim().orEmpty()
            }
            upgraded["model"] = JsonPrimitive(fallbackModel)
        }

        if ("aliases" !in upgraded) {
            upgraded["aliases"] = JsonArray(emptyList())
        }

        val existingIconType = root["iconType"]?.jsonPrimitive?.contentOrNull?.trim().orEmpty()
        if (existingIconType.isEmpty()) {
            upgraded["iconType"] = JsonPrimitive(inferIconType(category = category, archetype = archetype))
        }

        val migratedJson = JsonObject(upgraded).toString()
        return MigrationResult(
            jsonText = migratedJson,
            fromVersion = LEGACY_V3_SCHEMA_VERSION,
            toVersion = CURRENT_SCHEMA_VERSION,
            migrated = true,
        )
    }

    fun inferIconType(category: String, archetype: String): String {
        val cat = category.trim().lowercase()
        val arch = archetype.trim().lowercase()

        return when (cat) {
            "digital_compact", "digital_rangefinder", "digital_mirrorless" -> "compact_digital"
            "film_compact_35mm", "film_rangefinder_35mm" -> "compact_film"
            "film_35mm", "film_slr_35mm" -> "slr_film"
            "half_frame", "half_frame_35mm" -> "half_frame"
            "disposable", "disposable_35mm" -> "disposable"
            "instant", "instant_film" -> "instant"
            "medium_format", "medium_format_120" -> "medium_format"
            "toy_lens", "toy_camera_35mm", "toy_camera_120", "toy_camera_digital" -> "toy_lens"
            "camcorder" -> "camcorder"
            "camera_phone" -> "camera_phone"
            else -> when (arch) {
                "classic_35mm_slr" -> "slr_film"
                "premium_point_shoot_35mm" -> "compact_film"
                "half_frame_35mm" -> "half_frame"
                "disposable_point_shoot" -> "disposable"
                "toy_lomo_vignette" -> "toy_lens"
                "instant_integral_vintage", "instant_modern_instax" -> "instant"
                "medium_format_tlr_slr" -> "medium_format"
                "camcorder_mini_dv_vhs" -> "camcorder"
                "early_camera_phone" -> "camera_phone"
                else -> "compact_digital"
            }
        }
    }
}
