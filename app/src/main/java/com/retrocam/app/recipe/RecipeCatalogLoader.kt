package com.retrocam.app.recipe

import kotlinx.serialization.json.Json

/**
 * Pure-Kotlin JSON parser and validator for RecipeCatalog so JVM unit tests can
 * validate all asset files and malformed-JSON resilience without Android stubs.
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
        val errors = mutableListOf<RecipeLoadError>()

        val archetypes = parseEntries(
            entries = archetypesRaw,
            errors = errors,
            decode = { raw -> json.decodeFromString(ArchetypeSpec.serializer(), raw) },
            validate = RecipeValidator::validateArchetype,
        ).sortedBy { it.displayName }

        val cameras = parseEntries(
            entries = camerasRaw,
            errors = errors,
            decode = { raw ->
                val migrated = CameraSchemaMigrator.migrateIfNeeded(raw)
                json.decodeFromString(CameraSpec.serializer(), migrated.jsonText)
            },
            validate = RecipeValidator::validateCamera,
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

        return RecipeCatalog(
            cameras = cameras,
            lenses = lenses,
            films = films,
            archetypes = archetypes,
            loadErrors = errors,
        )
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
