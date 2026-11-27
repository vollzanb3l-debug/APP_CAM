package com.retrocam.app.storage

import com.retrocam.app.recipe.CameraSpec
import com.retrocam.app.recipe.FilmSpec
import com.retrocam.app.recipe.LensSpec
import com.retrocam.app.recipe.RecipeCatalog
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

/**
 * Serializable recipe metadata written into JPEG EXIF UserComment so GalleryScreen
 * can display "Camera - Lens - Film" labels and photos remain reproducible across schema changes.
 */
@Serializable
data class RecipeExifMetadata(
    val schemaVersion: Int = CURRENT_SCHEMA_VERSION,
    val cameraId: String,
    val lensId: String,
    val filmId: String,
    val recipeVersion: String,
    val cameraSchemaVersion: Int = 0,
    val lensSchemaVersion: Int = 0,
    val filmSchemaVersion: Int = 0,
) {
    companion object {
        const val CURRENT_SCHEMA_VERSION: Int = 1
        const val FALLBACK_NO_METADATA_LABEL: String = "Tanpa metadata resep"

        private val json = Json {
            ignoreUnknownKeys = true
            encodeDefaults = true
        }

        fun fromSpecs(
            camera: CameraSpec?,
            lens: LensSpec?,
            film: FilmSpec?,
        ): RecipeExifMetadata? {
            if (camera == null || lens == null || film == null) return null
            val versionTag = "c${camera.schemaVersion}.l${lens.schemaVersion}.f${film.schemaVersion}"
            return RecipeExifMetadata(
                schemaVersion = CURRENT_SCHEMA_VERSION,
                cameraId = camera.id,
                lensId = lens.id,
                filmId = film.id,
                recipeVersion = versionTag,
                cameraSchemaVersion = camera.schemaVersion,
                lensSchemaVersion = lens.schemaVersion,
                filmSchemaVersion = film.schemaVersion,
            )
        }

        fun encode(metadata: RecipeExifMetadata): String {
            return json.encodeToString(serializer(), metadata)
        }

        /**
         * Safely decodes an EXIF UserComment string. Handles missing values, NUL padding,
         * EXIF charset prefixes (e.g. "ASCII\0\0\0"), and non-RetroCam comments without crashing.
         */
        fun decode(rawUserComment: String?): RecipeExifMetadata? {
            if (rawUserComment.isNullOrBlank()) return null
            val cleaned = rawUserComment.replace("\u0000", "").trim()
            val start = cleaned.indexOf('{')
            val end = cleaned.lastIndexOf('}')
            if (start < 0 || end <= start) return null
            val payload = cleaned.substring(start, end + 1)
            val parsed = runCatching {
                json.decodeFromString(serializer(), payload)
            }.getOrNull() ?: return null

            if (parsed.schemaVersion < 1) return null
            if (parsed.cameraId.isBlank() || parsed.lensId.isBlank() || parsed.filmId.isBlank()) {
                return null
            }
            return parsed
        }

        /**
         * Formats "Kamera - Lensa - Film" using display names from the active catalog,
         * falling back to raw IDs for unknown/older recipes and a safe placeholder when absent.
         */
        fun formatDisplayLabel(
            metadata: RecipeExifMetadata?,
            catalog: RecipeCatalog,
        ): String {
            if (metadata == null) return FALLBACK_NO_METADATA_LABEL
            val cameraName = catalog.cameras.firstOrNull { it.id == metadata.cameraId }?.displayName
                ?: metadata.cameraId
            val lensName = catalog.lenses.firstOrNull { it.id == metadata.lensId }?.displayName
                ?: metadata.lensId
            val filmName = catalog.films.firstOrNull { it.id == metadata.filmId }?.displayName
                ?: metadata.filmId
            return "$cameraName - $lensName - $filmName"
        }
    }
}
