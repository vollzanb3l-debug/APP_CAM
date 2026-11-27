package com.retrocam.app.recipe

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.jsonObject

/**
 * Pure-Kotlin JSON parser and validator for RecipeCatalog so JVM unit tests can
 * validate all asset files and malformed-JSON resilience without Android stubs.
 *
 * Supports both single-index camera bundles (`CameraCatalogBundle`, fast startup for 100+ cameras)
 * and standalone per-camera JSON files (e.g. legacy v3 files or calibrated overrides).
 */
object RecipeCatalogLoader {

    private val json = Json {
        ignoreUnknownKeys = false
    }

    fun loadFromRaw(
        camerasRaw: Map<String, String>,
        lensesRaw: Map<String, String>,
        filmsRaw: Map<String, String>,
        archetypesRaw: Map<String, String>,
    ): RecipeCatalog {
        val startNs = System.nanoTime()
        val errors = mutableListOf<RecipeLoadError>()

        val archetypes = parseEntries(
            entries = archetypesRaw,
            errors = errors,
            decode = { raw -> json.decodeFromString(ArchetypeSpec.serializer(), raw) },
            validate = RecipeValidator::validateArchetype,
        ).sortedBy { it.displayName }

        val cameras = parseCameras(
            entries = camerasRaw,
            errors = errors,
        ).sortedBy { it.displayName }

        val lenses = parseEntries(
            entries = lensesRaw,
            errors = errors,
            decode = { raw -> json.decodeFromString(LensSpec.serializer(), raw) },
            validate = RecipeValidator::validateLens,
        ).sortedBy { it.focalLengthEquiv }

        val films = parseEntries(
            entries = filmsRaw,
            errors = errors,
            decode = { raw -> json.decodeFromString(FilmSpec.serializer(), raw) },
            validate = RecipeValidator::validateFilm,
        ).sortedBy { it.displayName }

        val elapsedMs = ((System.nanoTime() - startNs).coerceAtLeast(0L)) / 1_000_000L
        return RecipeCatalog(
            cameras = cameras,
            lenses = lenses,
            films = films,
            archetypes = archetypes,
            loadErrors = errors,
            loadTimeMs = elapsedMs,
        )
    }

    private fun parseCameras(
        entries: Map<String, String>,
        errors: MutableList<RecipeLoadError>,
    ): List<CameraSpec> {
        val bundledById = linkedMapOf<String, CameraSpec>()
        val standaloneById = linkedMapOf<String, CameraSpec>()

        for ((path, raw) in entries.entries.sortedBy { it.key }) {
            try {
                val rootObj = json.parseToJsonElement(raw).jsonObject
                if (rootObj["cameras"] is JsonArray) {
                    val bundle = json.decodeFromString(CameraCatalogBundle.serializer(), raw)
                    if (bundle.schemaVersion != CameraSchemaMigrator.CURRENT_SCHEMA_VERSION) {
                        errors += RecipeLoadError(
                            path,
                            "cameraBundle.schemaVersion must be ${CameraSchemaMigrator.CURRENT_SCHEMA_VERSION}",
                        )
                        continue
                    }
                    for ((idx, camera) in bundle.cameras.withIndex()) {
                        val entryPath = "$path#${camera.id.ifBlank { idx.toString() }}"
                        val validationErrors = RecipeValidator.validateCamera(camera)
                        if (validationErrors.isNotEmpty()) {
                            errors += RecipeLoadError(entryPath, validationErrors.joinToString("; "))
                        } else if (camera.id in bundledById) {
                            errors += RecipeLoadError(entryPath, "Duplicate camera id in bundle: ${camera.id}")
                        } else {
                            bundledById[camera.id] = camera
                        }
                    }
                } else {
                    val migrated = CameraSchemaMigrator.migrateIfNeeded(raw)
                    val parsed = json.decodeFromString(CameraSpec.serializer(), migrated.jsonText)
                    val validationErrors = RecipeValidator.validateCamera(parsed)
                    if (validationErrors.isNotEmpty()) {
                        errors += RecipeLoadError(path, validationErrors.joinToString("; "))
                    } else if (parsed.id in standaloneById) {
                        errors += RecipeLoadError(path, "Duplicate standalone camera id: ${parsed.id}")
                    } else {
                        standaloneById[parsed.id] = parsed
                    }
                }
            } catch (t: Throwable) {
                errors += RecipeLoadError(path, t.message ?: t::class.simpleName.orEmpty())
            }
        }

        // Standalone per-camera files override bundled entries with the same ID (for calibrated drop-ins).
        val merged = LinkedHashMap<String, CameraSpec>(bundledById.size + standaloneById.size)
        merged.putAll(bundledById)
        merged.putAll(standaloneById)
        return merged.values.toList()
    }

    private fun <T> parseEntries(
        entries: Map<String, String>,
        errors: MutableList<RecipeLoadError>,
        decode: (String) -> T,
        validate: (T) -> List<String>,
    ): List<T> {
        val valid = mutableListOf<T>()
        for ((path, raw) in entries.entries.sortedBy { it.key }) {
            try {
                val parsed = decode(raw)
                val validationErrors = validate(parsed)
                if (validationErrors.isNotEmpty()) {
                    errors += RecipeLoadError(path, validationErrors.joinToString("; "))
                } else {
                    valid += parsed
                }
            } catch (t: Throwable) {
                errors += RecipeLoadError(path, t.message ?: t::class.simpleName.orEmpty())
            }
        }
        return valid
    }
}
