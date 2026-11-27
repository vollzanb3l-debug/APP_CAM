package com.retrocam.app.recipe

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.File

class CameraSearchFilterTest {

    private fun load100Cameras(): List<CameraSpec> {
        val candidates = listOf(
            File("src/main/assets/cameras"),
            File("app/src/main/assets/cameras"),
        )
        val dir = candidates.firstOrNull { it.isDirectory }
            ?: error("Could not locate assets/cameras from ${File(".").absolutePath}")
        val raw = dir.listFiles()
            ?.filter { it.isFile && it.extension == "json" }
            ?.sortedBy { it.name }
            ?.associate { "cameras/${it.name}" to it.readText() }
            ?: emptyMap()
        val catalog = RecipeCatalogLoader.loadFromRaw(
            camerasRaw = raw,
            lensesRaw = emptyMap(),
            filmsRaw = emptyMap(),
            archetypesRaw = emptyMap(),
        )
        assertEquals(100, catalog.cameras.size)
        return catalog.cameras
    }

    @Test
    fun blankQuery_returnsAll100CamerasInOriginalOrder() {
        val cameras = load100Cameras()
        assertEquals(cameras, CameraSearchFilter.filterCameras(cameras, ""))
        assertEquals(cameras, CameraSearchFilter.filterCameras(cameras, "   "))
    }

    @Test
    fun searchByNameBrandAliasAndLookTags_matchesAccurately() {
        val cameras = load100Cameras()

        // By model/name
        val sd1000 = CameraSearchFilter.filterCameras(cameras, "SD1000")
        assertTrue(sd1000.any { it.id == "canon_powershot_sd1000" })

        // By regional alias (Digital IXUS 70 -> Canon PowerShot SD1000)
        val ixus = CameraSearchFilter.filterCameras(cameras, "IXUS 70")
        assertTrue(ixus.any { it.id == "canon_powershot_sd1000" })

        // By brand (case-insensitive)
        val fujis = CameraSearchFilter.filterCameras(cameras, "fujifilm")
        assertTrue(fujis.isNotEmpty())
        assertTrue(fujis.all {
            it.brand.equals("Fujifilm", ignoreCase = true) ||
                it.displayName.contains("fujifilm", ignoreCase = true) ||
                it.aliases.any { a -> a.contains("fujifilm", ignoreCase = true) }
        })

        // By lookTag (both hyphenated and space-separated)
        val heavyVig = CameraSearchFilter.filterCameras(cameras, "heavy-vignette")
        assertTrue("Expected cameras with heavy-vignette", heavyVig.isNotEmpty())
        assertTrue(heavyVig.any { it.id == "holga_120n" || it.id == "lomo_lc_a" })

        val heavyVigSpaced = CameraSearchFilter.filterCameras(cameras, "heavy vignette")
        assertEquals(heavyVig.map { it.id }.toSet(), heavyVigSpaced.map { it.id }.toSet())

        // Multi-token brand + lookTag intersection
        val canonPunchy = CameraSearchFilter.filterCameras(cameras, "canon clipped-highlights")
        assertTrue(canonPunchy.isNotEmpty())
        assertTrue(canonPunchy.all { it.brand.equals("Canon", ignoreCase = true) })

        // Non-matching query returns empty list safely
        assertTrue(CameraSearchFilter.filterCameras(cameras, "nonexistent_brand_xyz_999").isEmpty())
    }

    @Test
    fun filterCameras_preservesFavoritesFirstOrder() {
        val cameras = load100Cameras()
        val favorites = FavoriteIds(cameraIds = setOf("canon_powershot_sd1000"))
        val sorted = FavoritesSorter.sortCameras(cameras, favorites)

        val canonResults = CameraSearchFilter.filterCameras(sorted, "canon")
        assertTrue(canonResults.size > 1)
        assertEquals("canon_powershot_sd1000", canonResults.first().id)
    }

    @Test
    fun topSuggestedTags_returnsFrequentLookTags() {
        val cameras = load100Cameras()
        val tags = CameraSearchFilter.topSuggestedTags(cameras, limit = 8)
        assertEquals(8, tags.size)
        assertTrue(tags.all { it.isNotBlank() })
        assertTrue("clipped-highlights" in tags || "soft-edges" in tags)
    }
}
