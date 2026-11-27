package com.retrocam.app.recipe

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class FavoritesTest {

    private class InMemoryStringSetStore : StringSetKeyValueStore {
        val data = mutableMapOf<String, Set<String>>()

        override fun getStringSet(key: String): Set<String> = data[key].orEmpty().toSet()

        override fun putStringSet(key: String, values: Set<String>) {
            data[key] = values.toSet()
        }
    }

    private fun sampleCamera(id: String, displayName: String, schemaVersion: Int = 3): CameraSpec =
        CameraSpec(
            schemaVersion = schemaVersion,
            id = id,
            displayName = displayName,
            inspiredBy = "test",
            era = "2000s",
            category = "digital_compact",
            archetype = "ccd_compact_2000s",
            confidenceLook = "high",
            confidenceSpecs = "high",
            sources = listOf("test"),
            calibrationStatus = "draft",
            icon = "icons/cameras/compact.png",
            aspectRatio = "4:3",
            whiteBalance = WhiteBalanceSpec(0f, 0f),
            tone = ToneSpec(1f, 0.2f, 0f),
            saturation = 1f,
            sensorNoise = SensorNoiseSpec(0.1f, 0.2f),
            sharpen = 0.2f,
            dateStamp = DateStampSpec(true, false, "yy MM dd", "#FF8C1A"),
            notes = "",
        )

    private fun sampleLens(id: String, displayName: String): LensSpec =
        LensSpec(
            schemaVersion = 1,
            id = id,
            displayName = displayName,
            icon = "icons/lenses/prime.png",
            focalLengthEquiv = 35,
            softness = 0.1f,
            bloom = BloomSpec(0.1f, 0.8f, 0.006f),
            vignette = VignetteSpec(0.2f, 0.85f),
            distortion = DistortionSpec(0f),
            chromaticAberration = 0.05f,
            flare = 0f,
            notes = "",
        )

    private fun sampleFilm(id: String, displayName: String): FilmSpec =
        FilmSpec(
            schemaVersion = 2,
            id = id,
            displayName = displayName,
            archetype = "warm_golden_film",
            confidenceLook = "high",
            confidenceSpecs = "low",
            sources = listOf("test"),
            calibrationStatus = "draft",
            icon = "icons/films/canister.png",
            lut = null,
            grade = FilmGradeSpec(0.1f, 0f, 1f, 1f, 0.02f, "#FFFFFF", "#000000", 0.1f),
            grain = GrainSpec(0.15f, 0.0015f, 0.5f),
            halation = HalationSpec(0.05f, "#FF7244"),
            notes = "",
        )

    @Test
    fun repositoryPersistsOnlyIdsAndRoundTripsAcrossReloads() {
        val backing = InMemoryStringSetStore()
        val repo = FavoritesRepository(backing)

        assertEquals(FavoriteIds(), repo.load())

        repo.toggle(FavoriteCategory.CAMERA, "compact_2003_ccd")
        repo.toggle(FavoriteCategory.LENS, "lens_50_f14")
        repo.toggle(FavoriteCategory.FILM, "film_mono_400")

        // Verify storage contains strictly raw IDs (never display names or JSON payloads).
        assertEquals(setOf("compact_2003_ccd"), backing.data[FavoritesRepository.KEY_CAMERA_IDS])
        assertEquals(setOf("lens_50_f14"), backing.data[FavoritesRepository.KEY_LENS_IDS])
        assertEquals(setOf("film_mono_400"), backing.data[FavoritesRepository.KEY_FILM_IDS])

        // Simulate fresh repository instance after app restart.
        val reloadedRepo = FavoritesRepository(backing)
        val loaded = reloadedRepo.load()
        assertTrue(loaded.isFavorite(FavoriteCategory.CAMERA, "compact_2003_ccd"))
        assertTrue(loaded.isFavorite(FavoriteCategory.LENS, "lens_50_f14"))
        assertTrue(loaded.isFavorite(FavoriteCategory.FILM, "film_mono_400"))

        // Toggle off removes only the targeted ID.
        reloadedRepo.toggle(FavoriteCategory.LENS, "lens_50_f14")
        assertFalse(reloadedRepo.isFavorite(FavoriteCategory.LENS, "lens_50_f14"))
        assertTrue(reloadedRepo.isFavorite(FavoriteCategory.CAMERA, "compact_2003_ccd"))
        assertTrue(reloadedRepo.isFavorite(FavoriteCategory.FILM, "film_mono_400"))
    }

    @Test
    fun blankAndWhitespaceIdsAreIgnoredAndTrimmed() {
        val backing = InMemoryStringSetStore()
        val repo = FavoritesRepository(backing)

        repo.toggle(FavoriteCategory.CAMERA, "")
        repo.toggle(FavoriteCategory.CAMERA, "   ")
        repo.setFavorite(FavoriteCategory.FILM, "  film_surya_200  ", favorite = true)

        val loaded = repo.load()
        assertTrue(loaded.cameraIds.isEmpty())
        assertEquals(setOf("film_surya_200"), loaded.filmIds)
        assertTrue(loaded.isFavorite(FavoriteCategory.FILM, "film_surya_200"))
    }

    @Test
    fun sortWithFavoritesFirstPlacesFavoritesOnTopPreservingRelativeOrder() {
        val cameras = listOf(
            sampleCamera("cam_a", "Kamera A"),
            sampleCamera("cam_b", "Kamera B"),
            sampleCamera("cam_c", "Kamera C"),
            sampleCamera("cam_d", "Kamera D"),
        )
        val lenses = listOf(
            sampleLens("lens_28", "28mm"),
            sampleLens("lens_35", "35mm"),
            sampleLens("lens_50", "50mm"),
        )
        val films = listOf(
            sampleFilm("film_1", "Surya 200"),
            sampleFilm("film_2", "Linen 400"),
            sampleFilm("film_3", "Monokrom 400"),
        )

        val favorites = FavoriteIds(
            cameraIds = setOf("cam_d", "cam_b"),
            lensIds = setOf("lens_50"),
            filmIds = setOf("film_3", "film_1"),
        )

        assertEquals(
            listOf("cam_b", "cam_d", "cam_a", "cam_c"),
            FavoritesSorter.sortCameras(cameras, favorites).map { it.id },
        )
        assertEquals(
            listOf("lens_50", "lens_28", "lens_35"),
            FavoritesSorter.sortLenses(lenses, favorites).map { it.id },
        )
        assertEquals(
            listOf("film_1", "film_3", "film_2"),
            FavoritesSorter.sortFilms(films, favorites).map { it.id },
        )
    }

    @Test
    fun favoritesSurviveSchemaMigrationAndIgnoreUnknownIdsSafely() {
        val backing = InMemoryStringSetStore()
        val repo = FavoritesRepository(backing)
        repo.setFavorite(FavoriteCategory.CAMERA, "compact_2003_ccd", favorite = true)
        repo.setFavorite(FavoriteCategory.CAMERA, "removed_legacy_id", favorite = true)

        // Simulate M6 schema migration where schemaVersion & displayName change while id stays identical.
        val migratedCameras = listOf(
            sampleCamera("soft_2008", "Pocket Soft 2008 v4", schemaVersion = 4),
            sampleCamera("compact_2003_ccd", "Canon PowerShot SD1000 (Migrated)", schemaVersion = 4),
            sampleCamera("classic_35mm", "Classic 35mm v4", schemaVersion = 4),
        )

        val sorted = FavoritesSorter.sortCameras(migratedCameras, repo.load())
        assertEquals(
            listOf("compact_2003_ccd", "soft_2008", "classic_35mm"),
            sorted.map { it.id },
        )
    }

    @Test
    fun emptyFavoritesLeavesOriginalOrderUnchanged() {
        val films = listOf(
            sampleFilm("film_1", "Surya 200"),
            sampleFilm("film_2", "Linen 400"),
            sampleFilm("film_3", "Monokrom 400"),
        )
        val sorted = FavoritesSorter.sortFilms(films, FavoriteIds())
        assertEquals(listOf("film_1", "film_2", "film_3"), sorted.map { it.id })
    }
}
