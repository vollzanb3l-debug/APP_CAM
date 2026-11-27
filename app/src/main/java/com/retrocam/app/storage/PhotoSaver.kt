package com.retrocam.app.storage

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Saves processed photos to the Android MediaStore gallery with RetroCam EXIF recipe metadata.
 */
class PhotoSaver(context: Context) {

    private val resolver = context.applicationContext.contentResolver

    fun saveJpeg(
        bitmap: Bitmap,
        quality: Int = 95,
        metadata: RecipeExifMetadata? = null,
    ): Result<Uri> {
        val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss_SSS", Locale.US).format(Date())
        val displayName = "RetroCam_$timestamp.jpg"

        val values = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, displayName)
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                put(MediaStore.Images.Media.RELATIVE_PATH, "${Environment.DIRECTORY_PICTURES}/RetroCam")
                put(MediaStore.Images.Media.IS_PENDING, 1)
            }
        }

        val uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
            ?: return Result.failure(IllegalStateException("MediaStore insert failed"))

        return try {
            val stream = resolver.openOutputStream(uri)
                ?: throw IllegalStateException("Unable to open output stream for $uri")
            stream.use { out ->
                val compressed = bitmap.compress(Bitmap.CompressFormat.JPEG, quality, out)
                if (!compressed) {
                    throw IllegalStateException("Bitmap.compress failed")
                }
                out.flush()
            }

            if (metadata != null) {
                writeRecipeExif(uri, metadata)
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                val publish = ContentValues().apply {
                    put(MediaStore.Images.Media.IS_PENDING, 0)
                }
                resolver.update(uri, publish, null, null)
            }
            Result.success(uri)
        } catch (t: Throwable) {
            runCatching { resolver.delete(uri, null, null) }
            Result.failure(t)
        }
    }

    private fun writeRecipeExif(uri: Uri, metadata: RecipeExifMetadata) {
        try {
            val encoded = RecipeExifMetadata.encode(metadata)
            resolver.openFileDescriptor(uri, "rw")?.use { pfd ->
                val exif = ExifInterface(pfd.fileDescriptor)
                exif.setAttribute(ExifInterface.TAG_USER_COMMENT, encoded)
                exif.saveAttributes()
            }
        } catch (t: Throwable) {
            Log.w(TAG, "Failed writing EXIF UserComment for $uri", t)
        }
    }

    companion object {
        private const val TAG = "PhotoSaver"
    }
}
