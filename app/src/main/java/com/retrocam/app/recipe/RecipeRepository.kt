package com.retrocam.app.recipe

import android.content.res.AssetManager
import android.util.Log
import java.io.BufferedReader

/**
 * Loads and validates Camera, Lens, Film, and Archetype JSON recipes from Android assets.
 * Broken files are skipped, logged, and reported in RecipeCatalog.loadErrors.
 */
object RecipeRepository {

    private const val TAG = "RecipeRepository"

    fun loadFromAssets(assets: AssetManager): RecipeCatalog {
        val camerasRaw = readJsonFolder(assets, "cameras")
        val lensesRaw = readJsonFolder(assets, "lenses")
        val filmsRaw = readJsonFolder(assets, "films")
        val archetypesRaw = readJsonFolder(assets, "archetypes")

        val catalog = RecipeCatalogLoader.loadFromRaw(
            camerasRaw = camerasRaw,
            lensesRaw = lensesRaw,
            filmsRaw = filmsRaw,
            archetypesRaw = archetypesRaw,
        )
        for (err in catalog.loadErrors) {
            Log.w(TAG, "Skipped invalid recipe ${err.assetPath}: ${err.message}")
        }
        return catalog
    }

    private fun readJsonFolder(assets: AssetManager, folder: String): Map<String, String> {
        val names = assets.list(folder)?.filter { it.endsWith(".json") } ?: return emptyMap()
        val result = linkedMapOf<String, String>()
        for (name in names.sorted()) {
            val path = "$folder/$name"
            try {
                result[path] = assets.open(path).bufferedReader().use(BufferedReader::readText)
            } catch (t: Throwable) {
                Log.w(TAG, "Failed reading asset $path", t)
            }
        }
        return result
    }
}
