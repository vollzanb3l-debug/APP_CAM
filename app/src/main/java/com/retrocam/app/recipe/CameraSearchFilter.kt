package com.retrocam.app.recipe

import java.util.Locale

/**
 * Pure-Kotlin search filter for the 100-camera catalog in PickerSheet.
 * Matches multi-token queries across displayName, brand, model, aliases, id, and lookTags
 * while preserving the incoming list order (e.g. FavoritesSorter ordering).
 */
object CameraSearchFilter {

    fun filterCameras(
        cameras: List<CameraSpec>,
        rawQuery: String,
    ): List<CameraSpec> {
        val tokens = tokenize(rawQuery)
        if (tokens.isEmpty()) return cameras
        return cameras.filter { camera -> matchesAllTokens(camera, tokens) }
    }

    fun topSuggestedTags(
        cameras: List<CameraSpec>,
        limit: Int = 8,
    ): List<String> {
        if (limit <= 0 || cameras.isEmpty()) return emptyList()
        val counts = linkedMapOf<String, Int>()
        for (camera in cameras) {
            for (tag in camera.lookTags) {
                val clean = tag.trim()
                if (clean.isNotEmpty()) {
                    counts[clean] = (counts[clean] ?: 0) + 1
                }
            }
        }
        return counts.entries
            .sortedWith(compareByDescending<Map.Entry<String, Int>> { it.value }.thenBy { it.key })
            .take(limit)
            .map { it.key }
    }

    private fun tokenize(rawQuery: String): List<String> {
        val trimmed = rawQuery.trim().lowercase(Locale.US)
        if (trimmed.isEmpty()) return emptyList()
        return trimmed.split(Regex("\\s+")).filter { it.isNotEmpty() }
    }

    private fun matchesAllTokens(camera: CameraSpec, tokens: List<String>): Boolean {
        val nameLower = camera.displayName.lowercase(Locale.US)
        val brandLower = camera.brand.lowercase(Locale.US)
        val modelLower = camera.model.lowercase(Locale.US)
        val idLower = camera.id.lowercase(Locale.US)
        val aliasesLower = camera.aliases.map { it.lowercase(Locale.US) }
        val tagsNormalized = camera.lookTags.map { tag ->
            val lower = tag.lowercase(Locale.US)
            lower to lower.replace('-', ' ')
        }

        for (token in tokens) {
            val tokenSpaced = token.replace('-', ' ')
            val matched = nameLower.contains(token) ||
                brandLower.contains(token) ||
                modelLower.contains(token) ||
                idLower.contains(token) ||
                aliasesLower.any { it.contains(token) } ||
                tagsNormalized.any { (rawTag, spacedTag) ->
                    rawTag.contains(token) || spacedTag.contains(tokenSpaced)
                }
            if (!matched) return false
        }
        return true
    }
}
