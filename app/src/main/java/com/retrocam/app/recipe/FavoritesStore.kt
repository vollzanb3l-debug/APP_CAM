package com.retrocam.app.recipe

import android.content.Context

enum class FavoriteCategory {
    CAMERA,
    LENS,
    FILM,
}

/**
 * Holds favorite item identifiers ONLY (never display names or full recipe objects),
 * so stored favorites remain valid across schema migrations (e.g., M6).
 */
data class FavoriteIds(
    val cameraIds: Set<String> = emptySet(),
    val lensIds: Set<String> = emptySet(),
    val filmIds: Set<String> = emptySet(),
) {
    fun idsFor(category: FavoriteCategory): Set<String> = when (category) {
        FavoriteCategory.CAMERA -> cameraIds
        FavoriteCategory.LENS -> lensIds
        FavoriteCategory.FILM -> filmIds
    }

    fun isFavorite(category: FavoriteCategory, id: String): Boolean {
        val normalized = id.trim()
        if (normalized.isEmpty()) return false
        return normalized in idsFor(category)
    }

    fun setFavorite(category: FavoriteCategory, id: String, favorite: Boolean): FavoriteIds {
        val normalized = id.trim()
        if (normalized.isEmpty()) return this
        val current = idsFor(category)
        val updated = if (favorite) {
            current + normalized
        } else {
            current - normalized
        }
        if (updated == current) return this
        return when (category) {
            FavoriteCategory.CAMERA -> copy(cameraIds = updated)
            FavoriteCategory.LENS -> copy(lensIds = updated)
            FavoriteCategory.FILM -> copy(filmIds = updated)
        }
    }

    fun toggle(category: FavoriteCategory, id: String): FavoriteIds {
        val normalized = id.trim()
        if (normalized.isEmpty()) return this
        return setFavorite(category, normalized, favorite = !isFavorite(category, normalized))
    }

    companion object {
        fun normalizeIds(raw: Iterable<String>?): Set<String> {
            if (raw == null) return emptySet()
            val result = LinkedHashSet<String>()
            for (item in raw) {
                val trimmed = item.trim()
                if (trimmed.isNotEmpty()) {
                    result.add(trimmed)
                }
            }
            return result
        }
    }
}

/**
 * Stable ordering helper that surfaces favorited items at the front/top of picker lists
 * while preserving original relative order within both favorites and non-favorites.
 */
object FavoritesSorter {

    fun <T> sortWithFavoritesFirst(
        items: List<T>,
        favoriteIds: Set<String>,
        idSelector: (T) -> String,
    ): List<T> {
        if (favoriteIds.isEmpty() || items.size <= 1) return items
        val (favorites, others) = items.partition { idSelector(it) in favoriteIds }
        if (favorites.isEmpty() || others.isEmpty()) return items
        return favorites + others
    }

    fun sortCameras(cameras: List<CameraSpec>, favorites: FavoriteIds): List<CameraSpec> =
        sortWithFavoritesFirst(cameras, favorites.cameraIds) { it.id }

    fun sortLenses(lenses: List<LensSpec>, favorites: FavoriteIds): List<LensSpec> =
        sortWithFavoritesFirst(lenses, favorites.lensIds) { it.id }

    fun sortFilms(films: List<FilmSpec>, favorites: FavoriteIds): List<FilmSpec> =
        sortWithFavoritesFirst(films, favorites.filmIds) { it.id }
}

/**
 * Minimal persistence abstraction so ID-only favorite storage is unit-testable on the JVM.
 */
interface StringSetKeyValueStore {
    fun getStringSet(key: String): Set<String>
    fun putStringSet(key: String, values: Set<String>)
}

/**
 * Pure-Kotlin repository persisting ONLY camera, lens, and film IDs.
 */
class FavoritesRepository(
    private val store: StringSetKeyValueStore,
) {
    fun load(): FavoriteIds = FavoriteIds(
        cameraIds = FavoriteIds.normalizeIds(store.getStringSet(KEY_CAMERA_IDS)),
        lensIds = FavoriteIds.normalizeIds(store.getStringSet(KEY_LENS_IDS)),
        filmIds = FavoriteIds.normalizeIds(store.getStringSet(KEY_FILM_IDS)),
    )

    fun save(favorites: FavoriteIds) {
        store.putStringSet(KEY_CAMERA_IDS, FavoriteIds.normalizeIds(favorites.cameraIds))
        store.putStringSet(KEY_LENS_IDS, FavoriteIds.normalizeIds(favorites.lensIds))
        store.putStringSet(KEY_FILM_IDS, FavoriteIds.normalizeIds(favorites.filmIds))
    }

    fun toggle(category: FavoriteCategory, id: String): FavoriteIds {
        val updated = load().toggle(category, id)
        save(updated)
        return updated
    }

    fun setFavorite(category: FavoriteCategory, id: String, favorite: Boolean): FavoriteIds {
        val updated = load().setFavorite(category, id, favorite)
        save(updated)
        return updated
    }

    fun isFavorite(category: FavoriteCategory, id: String): Boolean =
        load().isFavorite(category, id)

    companion object {
        const val KEY_CAMERA_IDS = "favorite_camera_ids"
        const val KEY_LENS_IDS = "favorite_lens_ids"
        const val KEY_FILM_IDS = "favorite_film_ids"
    }
}

/**
 * Android SharedPreferences-backed store for favorite camera, lens, and film IDs.
 */
class FavoritesStore(context: Context) {

    private val prefs = context.applicationContext.getSharedPreferences(
        PREFS_NAME,
        Context.MODE_PRIVATE,
    )

    private val repository = FavoritesRepository(
        object : StringSetKeyValueStore {
            override fun getStringSet(key: String): Set<String> {
                // Defensive copy: never mutate or retain the Set instance returned by SharedPreferences.
                return prefs.getStringSet(key, emptySet()).orEmpty().toSet()
            }

            override fun putStringSet(key: String, values: Set<String>) {
                prefs.edit()
                    .putStringSet(key, HashSet(values))
                    .apply()
            }
        },
    )

    fun load(): FavoriteIds = repository.load()

    fun save(favorites: FavoriteIds) = repository.save(favorites)

    fun toggle(category: FavoriteCategory, id: String): FavoriteIds =
        repository.toggle(category, id)

    fun setFavorite(category: FavoriteCategory, id: String, favorite: Boolean): FavoriteIds =
        repository.setFavorite(category, id, favorite)

    fun isFavorite(category: FavoriteCategory, id: String): Boolean =
        repository.isFavorite(category, id)

    companion object {
        private const val PREFS_NAME = "retrocam_favorites"
    }
}
