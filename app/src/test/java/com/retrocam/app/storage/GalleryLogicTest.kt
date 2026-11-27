package com.retrocam.app.storage

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class GalleryLogicTest {

    @Test
    fun computePageBoundsHandlesEmptyFolder() {
        val slice = GalleryPagination.computePageBounds(totalCount = 0, offset = 0, pageSize = 24)
        assertEquals(0, slice.startIndex)
        assertEquals(0, slice.count)
        assertFalse(slice.hasMore)
    }

    @Test
    fun computePageBoundsPagesLargeCollectionIncrementally() {
        val page1 = GalleryPagination.computePageBounds(totalCount = 60, offset = 0, pageSize = 24)
        assertEquals(0, page1.startIndex)
        assertEquals(24, page1.count)
        assertTrue(page1.hasMore)

        val page2 = GalleryPagination.computePageBounds(totalCount = 60, offset = 24, pageSize = 24)
        assertEquals(24, page2.startIndex)
        assertEquals(24, page2.count)
        assertTrue(page2.hasMore)

        val page3 = GalleryPagination.computePageBounds(totalCount = 60, offset = 48, pageSize = 24)
        assertEquals(48, page3.startIndex)
        assertEquals(12, page3.count)
        assertFalse(page3.hasMore)

        val pastEnd = GalleryPagination.computePageBounds(totalCount = 60, offset = 60, pageSize = 24)
        assertEquals(0, pastEnd.count)
        assertFalse(pastEnd.hasMore)
    }

    @Test
    fun shouldLoadMoreTriggersNearEndAndRespectsGuards() {
        assertFalse(GalleryPagination.shouldLoadMore(lastVisibleIndex = 5, loadedCount = 24, hasMore = true, isLoading = false))
        assertTrue(GalleryPagination.shouldLoadMore(lastVisibleIndex = 18, loadedCount = 24, hasMore = true, isLoading = false))
        assertFalse(GalleryPagination.shouldLoadMore(lastVisibleIndex = 23, loadedCount = 24, hasMore = false, isLoading = false))
        assertFalse(GalleryPagination.shouldLoadMore(lastVisibleIndex = 23, loadedCount = 24, hasMore = true, isLoading = true))
        assertFalse(GalleryPagination.shouldLoadMore(lastVisibleIndex = null, loadedCount = 24, hasMore = true, isLoading = false))
    }

    @Test
    fun removeMissingByIdPrunesExternallyDeletedPhotoSafely() {
        val items = listOf(101L, 102L, 103L)
        val afterDelete = GalleryPagination.removeMissingById(items, 102L) { it }
        assertEquals(listOf(101L, 103L), afterDelete)

        val afterMissingAgain = GalleryPagination.removeMissingById(afterDelete, 999L) { it }
        assertEquals(listOf(101L, 103L), afterMissingAgain)
    }

    @Test
    fun computeInSampleSizeScalesDownLargeImagesAndHandlesInvalidDimensions() {
        assertEquals(1, GalleryPagination.computeInSampleSize(0, 0, 360))
        assertEquals(1, GalleryPagination.computeInSampleSize(300, 200, 360))
        assertEquals(4, GalleryPagination.computeInSampleSize(4000, 3000, 600))
        assertEquals(2, GalleryPagination.computeInSampleSize(4000, 3000, 1600))
    }
}
