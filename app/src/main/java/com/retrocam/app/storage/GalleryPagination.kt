package com.retrocam.app.storage

/**
 * Pure Kotlin helpers for incremental gallery pagination, bitmap downsampling,
 * and pruning externally deleted photos without Android runtime dependencies.
 */
object GalleryPagination {

    const val DEFAULT_PAGE_SIZE: Int = 24
    const val DEFAULT_PREFETCH_THRESHOLD: Int = 6

    data class PageSlice(
        val startIndex: Int,
        val count: Int,
        val hasMore: Boolean,
    )

    fun computePageBounds(
        totalCount: Int,
        offset: Int,
        pageSize: Int = DEFAULT_PAGE_SIZE,
    ): PageSlice {
        val safeTotal = totalCount.coerceAtLeast(0)
        val safeOffset = offset.coerceAtLeast(0)
        val safePageSize = pageSize.coerceAtLeast(1)
        if (safeTotal == 0 || safeOffset >= safeTotal) {
            return PageSlice(startIndex = safeTotal, count = 0, hasMore = false)
        }
        val remaining = safeTotal - safeOffset
        val count = minOf(safePageSize, remaining)
        val hasMore = (safeOffset + count) < safeTotal
        return PageSlice(
            startIndex = safeOffset,
            count = count,
            hasMore = hasMore,
        )
    }

    fun shouldLoadMore(
        lastVisibleIndex: Int?,
        loadedCount: Int,
        hasMore: Boolean,
        isLoading: Boolean,
        prefetchThreshold: Int = DEFAULT_PREFETCH_THRESHOLD,
    ): Boolean {
        if (!hasMore || isLoading || loadedCount <= 0 || lastVisibleIndex == null) {
            return false
        }
        val thresholdIndex = (loadedCount - 1 - prefetchThreshold.coerceAtLeast(0)).coerceAtLeast(0)
        return lastVisibleIndex >= thresholdIndex
    }

    fun <T> removeMissingById(
        items: List<T>,
        missingId: Long,
        idSelector: (T) -> Long,
    ): List<T> {
        return items.filterNot { idSelector(it) == missingId }
    }

    fun computeInSampleSize(
        srcWidth: Int,
        srcHeight: Int,
        reqMaxEdge: Int,
    ): Int {
        if (srcWidth <= 0 || srcHeight <= 0 || reqMaxEdge <= 0) return 1
        var sampleSize = 1
        var longestEdge = maxOf(srcWidth, srcHeight)
        while ((longestEdge / 2) >= reqMaxEdge) {
            sampleSize *= 2
            longestEdge /= 2
        }
        return sampleSize.coerceAtLeast(1)
    }
}
