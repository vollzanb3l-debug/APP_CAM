package com.retrocam.app.storage

import android.content.ContentUris
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.util.Size
import com.retrocam.app.recipe.RecipeCatalog

data class GalleryPhotoItem(
    val mediaId: Long,
    val uri: Uri,
    val displayName: String,
    val dateAddedSeconds: Long,
    val metadata: RecipeExifMetadata?,
    val recipeLabel: String,
)

data class GalleryPageResult(
    val items: List<GalleryPhotoItem>,
    val nextOffset: Int,
    val hasMore: Boolean,
    val totalCount: Int,
)

/**
 * Loads RetroCam photos from Pictures/RetroCam in incremental pages, reads EXIF UserComment
 * recipe labels, and handles missing/externally deleted files gracefully.
 */
class GalleryRepository(context: Context) {

    private val resolver = context.applicationContext.contentResolver

    fun loadPage(
        offset: Int,
        pageSize: Int = GalleryPagination.DEFAULT_PAGE_SIZE,
        catalog: RecipeCatalog,
    ): GalleryPageResult {
        val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.DATE_ADDED,
        )

        val selection: String
        val selectionArgs: Array<String>
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            selection = "${MediaStore.Images.Media.RELATIVE_PATH} LIKE ? AND ${MediaStore.Images.Media.IS_PENDING} = 0"
            selectionArgs = arrayOf("${Environment.DIRECTORY_PICTURES}/RetroCam%")
        } else {
            selection = "${MediaStore.Images.Media.DISPLAY_NAME} LIKE ?"
            selectionArgs = arrayOf("RetroCam_%")
        }

        val sortOrder = "${MediaStore.Images.Media.DATE_ADDED} DESC, ${MediaStore.Images.Media._ID} DESC"

        return try {
            resolver.query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                projection,
                selection,
                selectionArgs,
                sortOrder,
            )?.use { cursor ->
                val totalCount = cursor.count
                val slice = GalleryPagination.computePageBounds(
                    totalCount = totalCount,
                    offset = offset,
                    pageSize = pageSize,
                )
                if (slice.count == 0 || !cursor.moveToPosition(slice.startIndex)) {
                    return GalleryPageResult(
                        items = emptyList(),
                        nextOffset = totalCount,
                        hasMore = false,
                        totalCount = totalCount,
                    )
                }

                val idCol = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
                val nameCol = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)
                val dateCol = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_ADDED)

                val loaded = ArrayList<GalleryPhotoItem>(slice.count)
                var readCount = 0
                while (readCount < slice.count) {
                    val mediaId = cursor.getLong(idCol)
                    val displayName = cursor.getString(nameCol) ?: "RetroCam_$mediaId.jpg"
                    val dateAdded = cursor.getLong(dateCol)
                    val uri = ContentUris.withAppendedId(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        mediaId,
                    )

                    if (exists(uri)) {
                        val metadata = readRecipeMetadata(uri)
                        val label = RecipeExifMetadata.formatDisplayLabel(metadata, catalog)
                        loaded.add(
                            GalleryPhotoItem(
                                mediaId = mediaId,
                                uri = uri,
                                displayName = displayName,
                                dateAddedSeconds = dateAdded,
                                metadata = metadata,
                                recipeLabel = label,
                            ),
                        )
                    }

                    readCount++
                    if (readCount < slice.count && !cursor.moveToNext()) {
                        break
                    }
                }

                GalleryPageResult(
                    items = loaded,
                    nextOffset = slice.startIndex + readCount,
                    hasMore = slice.hasMore,
                    totalCount = totalCount,
                )
            } ?: GalleryPageResult(
                items = emptyList(),
                nextOffset = offset,
                hasMore = false,
                totalCount = 0,
            )
        } catch (t: Throwable) {
            Log.w(TAG, "Failed querying Pictures/RetroCam gallery page at offset=$offset", t)
            GalleryPageResult(
                items = emptyList(),
                nextOffset = offset,
                hasMore = false,
                totalCount = 0,
            )
        }
    }

    fun exists(uri: Uri): Boolean {
        return try {
            resolver.openAssetFileDescriptor(uri, "r")?.use { true } ?: false
        } catch (_: Throwable) {
            false
        }
    }

    fun readRecipeMetadata(uri: Uri): RecipeExifMetadata? {
        return try {
            resolver.openInputStream(uri)?.use { stream ->
                val rawComment = ExifInterface(stream).getAttribute(ExifInterface.TAG_USER_COMMENT)
                RecipeExifMetadata.decode(rawComment)
            }
        } catch (t: Throwable) {
            Log.w(TAG, "Unable to read EXIF UserComment for $uri", t)
            null
        }
    }

    fun loadThumbnail(uri: Uri, targetSizePx: Int = 360): Bitmap? {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val osThumb = runCatching {
                resolver.loadThumbnail(uri, Size(targetSizePx, targetSizePx), null)
            }.getOrNull()
            if (osThumb != null) return osThumb
        }
        return decodeSampledBitmap(uri, targetSizePx)
    }

    fun loadPreviewBitmap(uri: Uri, maxEdgePx: Int = 1600): Bitmap? {
        return decodeSampledBitmap(uri, maxEdgePx)
    }

    private fun decodeSampledBitmap(uri: Uri, maxEdgePx: Int): Bitmap? {
        return try {
            val bounds = BitmapFactory.Options().apply { inJustDecodeBounds = true }
            resolver.openInputStream(uri)?.use { stream ->
                BitmapFactory.decodeStream(stream, null, bounds)
            } ?: return null

            if (bounds.outWidth <= 0 || bounds.outHeight <= 0) return null

            val sampleSize = GalleryPagination.computeInSampleSize(
                srcWidth = bounds.outWidth,
                srcHeight = bounds.outHeight,
                reqMaxEdge = maxEdgePx,
            )
            val decodeOpts = BitmapFactory.Options().apply {
                inSampleSize = sampleSize
                inPreferredConfig = Bitmap.Config.ARGB_8888
            }
            resolver.openInputStream(uri)?.use { stream ->
                BitmapFactory.decodeStream(stream, null, decodeOpts)
            }
        } catch (t: Throwable) {
            Log.w(TAG, "Failed decoding bitmap for $uri", t)
            null
        }
    }

    companion object {
        private const val TAG = "GalleryRepository"
    }
}
