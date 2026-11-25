package com.retrocam.app.recipe

import android.content.Context

/**
 * Persists and restores the last chosen Camera, Lens, and Film IDs.
 */
class SelectionStore(context: Context) {

    data class SavedSelection(
        val cameraId: String?,
        val lensId: String?,
        val filmId: String?,
    )

    private val prefs = context.applicationContext.getSharedPreferences(
        PREFS_NAME,
        Context.MODE_PRIVATE,
    )

    fun load(): SavedSelection = SavedSelection(
        cameraId = prefs.getString(KEY_CAMERA_ID, null),
        lensId = prefs.getString(KEY_LENS_ID, null),
        filmId = prefs.getString(KEY_FILM_ID, null),
    )

    fun save(cameraId: String?, lensId: String?, filmId: String?) {
        prefs.edit()
            .putString(KEY_CAMERA_ID, cameraId)
            .putString(KEY_LENS_ID, lensId)
            .putString(KEY_FILM_ID, filmId)
            .apply()
    }

    companion object {
        private const val PREFS_NAME = "retrocam_selection"
        private const val KEY_CAMERA_ID = "camera_id"
        private const val KEY_LENS_ID = "lens_id"
        private const val KEY_FILM_ID = "film_id"
    }
}
