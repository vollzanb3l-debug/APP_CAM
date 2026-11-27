package com.retrocam.app.storage

import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import com.retrocam.app.recipe.ArchetypeSpec
import com.retrocam.app.recipe.CameraBodyOpticsSpec
import com.retrocam.app.recipe.CameraParameterDefaults
import com.retrocam.app.recipe.CameraSpec
import com.retrocam.app.recipe.DateStampSpec
import com.retrocam.app.recipe.FinalRecipe
import com.retrocam.app.recipe.RecipeCompositor
import com.retrocam.app.recipe.RecipeValidator
import com.retrocam.app.recipe.SensorNoiseSpec
import com.retrocam.app.recipe.ToneSpec
import com.retrocam.app.recipe.WhiteBalanceSpec
import kotlinx.serialization.json.Json
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.roundToInt

/**
 * Pure-Kotlin serializer and converter for calibrated Archetype (v2) and Camera (v4) JSON exports.
 * Exported files conform strictly to RecipeValidator so they can be copied straight into app/src/main/assets/.
 */
object CalibrationJsonBuilder {

    const val EXPORT_RELATIVE_DIR = "Documents/RetroCam/export"

    private val prettyJson = Json {
        prettyPrint = true
        encodeDefaults = true
    }

    private val allowedAspectRatios = setOf("4:3", "3:4", "3:2", "2:3", "16:9", "1:1")

    /**
     * Converts an ArchetypeSpec's cameraDefaults into a pure FinalRecipe (neutral lens & film)
     * so users can tune each of the 15 archetypes cleanly against a reference photo.
     */
    fun archetypeToPreviewRecipe(archetype: ArchetypeSpec): FinalRecipe {
        val d = archetype.cameraDefaults ?: RecipeCompositor.FALLBACK_CAMERA_DEFAULTS
        return FinalRecipe(
            temp = d.whiteBalance.temp.coerceIn(-1f, 1f),
            tint = d.whiteBalance.tint.coerceIn(-1f, 1f),
            contrast = d.tone.contrast.coerceIn(0.5f, 2.0f),
            saturation = d.saturation.coerceIn(0f, 2.5f),
            highlightRolloff = d.tone.highlightRolloff.coerceIn(0f, 1f),
            shadowLift = d.tone.shadowLift.coerceIn(0f, 0.4f),
            blackLift = 0f,
            tintStrength = 0f,
            aspectRatio = d.aspectRatio.takeIf { it in allowedAspectRatios } ?: "4:3",
            softness = d.bodyOptics.softness.coerceIn(0f, 1f),
            bloomIntensity = d.bodyOptics.bloomIntensity.coerceIn(0f, 1f),
            bloomThreshold = 0.80f,
            bloomRadius = 0.008f,
            vignetteStrength = d.bodyOptics.vignetteStrength.coerceIn(0f, 1f),
            vignetteRadius = 0.85f,
            distortionK1 = 0f,
            chromaticAberration = d.bodyOptics.chromaticAberration.coerceIn(0f, 1f),
            flare = 0f,
            combinedNoiseAmount = d.sensorNoise.amount.coerceIn(0f, 1f),
            sensorNoiseChroma = d.sensorNoise.chroma.coerceIn(0f, 1f),
            sharpen = d.sharpen.coerceIn(0f, 1f),
            grainSize = 0.0014f,
            grainRoughness = 0.5f,
            halationIntensity = 0f,
            dateStampAvailable = d.dateStamp.available,
            dateStampDefaultOn = d.dateStamp.defaultOn,
            dateStampEnabled = d.dateStamp.available && d.dateStamp.defaultOn,
            dateStampFormat = d.dateStamp.format,
            dateStampColorHex = d.dateStamp.color,
            lightLeakIntensity = d.bodyOptics.lightLeakIntensity.coerceIn(0f, 1f),
        )
    }

    fun recipeToCameraDefaults(
        recipe: FinalRecipe,
        fallbackDateStamp: DateStampSpec = RecipeCompositor.FALLBACK_CAMERA_DEFAULTS.dateStamp,
    ): CameraParameterDefaults {
        val safeAspect = recipe.aspectRatio.takeIf { it in allowedAspectRatios } ?: "4:3"
        val safeDateColor = recipe.dateStampColorHex.takeIf {
            com.retrocam.app.recipe.HexColorParser.isValidHex(it)
        } ?: fallbackDateStamp.color

        return CameraParameterDefaults(
            aspectRatio = safeAspect,
            whiteBalance = WhiteBalanceSpec(
                temp = round3(recipe.temp.coerceIn(-1f, 1f)),
                tint = round3(recipe.tint.coerceIn(-1f, 1f)),
            ),
            tone = ToneSpec(
                contrast = round3(recipe.contrast.coerceIn(0.5f, 2.0f)),
                highlightRolloff = round3(recipe.highlightRolloff.coerceIn(0f, 1f)),
                shadowLift = round3(recipe.shadowLift.coerceIn(0f, 0.4f)),
            ),
            saturation = round3(recipe.saturation.coerceIn(0f, 2.5f)),
            sensorNoise = SensorNoiseSpec(
                amount = round3(recipe.combinedNoiseAmount.coerceIn(0f, 1f)),
                chroma = round3(recipe.sensorNoiseChroma.coerceIn(0f, 1f)),
            ),
            sharpen = round3(recipe.sharpen.coerceIn(0f, 1f)),
            dateStamp = DateStampSpec(
                available = recipe.dateStampAvailable || fallbackDateStamp.available,
                defaultOn = recipe.dateStampEnabled,
                format = recipe.dateStampFormat.ifBlank { fallbackDateStamp.format },
                color = safeDateColor,
            ),
            bodyOptics = CameraBodyOpticsSpec(
                softness = round3(recipe.softness.coerceIn(0f, 1f)),
                vignetteStrength = round3(recipe.vignetteStrength.coerceIn(0f, 1f)),
                bloomIntensity = round3(recipe.bloomIntensity.coerceIn(0f, 1f)),
                chromaticAberration = round3(recipe.chromaticAberration.coerceIn(0f, 1f)),
                lightLeakIntensity = round3(recipe.lightLeakIntensity.coerceIn(0f, 1f)),
            ),
        )
    }

    fun buildCalibratedArchetype(
        archetype: ArchetypeSpec,
        tunedRecipe: FinalRecipe,
    ): ArchetypeSpec {
        val fallbackDs = archetype.cameraDefaults?.dateStamp
            ?: RecipeCompositor.FALLBACK_CAMERA_DEFAULTS.dateStamp
        return archetype.copy(
            schemaVersion = RecipeValidator.CURRENT_ARCHETYPE_SCHEMA_VERSION,
            calibrationStatus = "calibrated",
            cameraDefaults = recipeToCameraDefaults(tunedRecipe, fallbackDs),
        )
    }

    fun encodeCalibratedArchetypeJson(
        archetype: ArchetypeSpec,
        tunedRecipe: FinalRecipe,
    ): String {
        val calibrated = buildCalibratedArchetype(archetype, tunedRecipe)
        return prettyJson.encodeToString(ArchetypeSpec.serializer(), calibrated) + "\n"
    }

    fun buildCalibratedCamera(
        camera: CameraSpec,
        tunedRecipe: FinalRecipe,
        archetype: ArchetypeSpec? = null,
    ): CameraSpec {
        val fallbackDs = camera.dateStamp
            ?: archetype?.cameraDefaults?.dateStamp
            ?: RecipeCompositor.FALLBACK_CAMERA_DEFAULTS.dateStamp
        val defaults = recipeToCameraDefaults(tunedRecipe, fallbackDs)
        return camera.copy(
            schemaVersion = RecipeValidator.CURRENT_CAMERA_SCHEMA_VERSION,
            calibrationStatus = "calibrated",
            aspectRatio = defaults.aspectRatio,
            whiteBalance = defaults.whiteBalance,
            tone = defaults.tone,
            saturation = defaults.saturation,
            sensorNoise = defaults.sensorNoise,
            sharpen = defaults.sharpen,
            dateStamp = defaults.dateStamp,
            bodyOptics = defaults.bodyOptics,
        )
    }

    fun encodeCalibratedCameraJson(
        camera: CameraSpec,
        tunedRecipe: FinalRecipe,
        archetype: ArchetypeSpec? = null,
    ): String {
        val calibrated = buildCalibratedCamera(camera, tunedRecipe, archetype)
        return prettyJson.encodeToString(CameraSpec.serializer(), calibrated) + "\n"
    }

    fun buildExportFileName(
        kind: String,
        id: String,
        timestamp: String,
    ): String {
        val safeKind = sanitizeToken(kind, fallback = "recipe")
        val safeId = sanitizeToken(id, fallback = "unknown")
        val safeTs = sanitizeToken(timestamp, fallback = "export")
        return "${safeKind}_${safeId}_calibrated_${safeTs}.json"
    }

    private fun sanitizeToken(raw: String, fallback: String): String {
        val cleaned = raw.trim().lowercase(Locale.US)
            .replace(Regex("[^a-z0-9_-]+"), "_")
            .trim('_')
        return cleaned.ifEmpty { fallback }
    }

    private fun round3(value: Float): Float = (value * 1000f).roundToInt() / 1000f
}

/**
 * Writes calibrated JSON files to Documents/RetroCam/export/ so the project owner
 * can pull and copy tuned archetype/camera JSON files back into the repository.
 */
class CalibrationExporter(context: Context) {

    data class ExportResult(
        val fileName: String,
        val displayPath: String,
        val uri: Uri?,
    )

    private val appContext = context.applicationContext
    private val resolver = appContext.contentResolver

    fun exportCalibratedArchetype(
        archetype: ArchetypeSpec,
        tunedRecipe: FinalRecipe,
    ): Result<ExportResult> {
        val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
        val fileName = CalibrationJsonBuilder.buildExportFileName("archetype", archetype.id, timestamp)
        val payload = CalibrationJsonBuilder.encodeCalibratedArchetypeJson(archetype, tunedRecipe)
        return writeJsonToDocumentsExport(fileName = fileName, jsonContent = payload)
    }

    fun exportCalibratedCamera(
        camera: CameraSpec,
        tunedRecipe: FinalRecipe,
        archetype: ArchetypeSpec? = null,
    ): Result<ExportResult> {
        val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
        val fileName = CalibrationJsonBuilder.buildExportFileName("camera", camera.id, timestamp)
        val payload = CalibrationJsonBuilder.encodeCalibratedCameraJson(camera, tunedRecipe, archetype)
        return writeJsonToDocumentsExport(fileName = fileName, jsonContent = payload)
    }

    private fun writeJsonToDocumentsExport(
        fileName: String,
        jsonContent: String,
    ): Result<ExportResult> {
        val bytes = jsonContent.toByteArray(Charsets.UTF_8)
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            writeViaMediaStoreDocuments(fileName = fileName, bytes = bytes)
        } else {
            writeViaLegacyDocumentsFile(fileName = fileName, bytes = bytes)
        }
    }

    private fun writeViaMediaStoreDocuments(
        fileName: String,
        bytes: ByteArray,
    ): Result<ExportResult> {
        val relativePath = "${Environment.DIRECTORY_DOCUMENTS}/RetroCam/export"
        val values = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
            put(MediaStore.MediaColumns.MIME_TYPE, "application/json")
            put(MediaStore.MediaColumns.RELATIVE_PATH, relativePath)
            put(MediaStore.MediaColumns.IS_PENDING, 1)
        }

        val collection = MediaStore.Files.getContentUri("external")
        val uri = resolver.insert(collection, values)
            ?: return Result.failure(IllegalStateException("Gagal membuat file di $relativePath"))

        return try {
            val out = resolver.openOutputStream(uri)
                ?: throw IllegalStateException("Gagal membuka output stream untuk $fileName")
            out.use { stream ->
                stream.write(bytes)
                stream.flush()
            }
            val publish = ContentValues().apply {
                put(MediaStore.MediaColumns.IS_PENDING, 0)
            }
            resolver.update(uri, publish, null, null)
            Result.success(
                ExportResult(
                    fileName = fileName,
                    displayPath = "$relativePath/$fileName",
                    uri = uri,
                ),
            )
        } catch (t: Throwable) {
            runCatching { resolver.delete(uri, null, null) }
            Result.failure(t)
        }
    }

    private fun writeViaLegacyDocumentsFile(
        fileName: String,
        bytes: ByteArray,
    ): Result<ExportResult> {
        return try {
            @Suppress("DEPRECATION")
            val publicDocs = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)
            val targetDir = File(publicDocs, "RetroCam/export")
            if (!targetDir.exists()) {
                targetDir.mkdirs()
            }
            val file = File(targetDir, fileName)
            file.writeBytes(bytes)
            Result.success(
                ExportResult(
                    fileName = fileName,
                    displayPath = "Documents/RetroCam/export/$fileName",
                    uri = Uri.fromFile(file),
                ),
            )
        } catch (_: Throwable) {
            // Fallback to app-specific Documents directory if legacy external storage permission is unavailable.
            runCatching {
                val appDocs = appContext.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
                    ?: appContext.filesDir
                val targetDir = File(appDocs, "RetroCam/export")
                if (!targetDir.exists()) {
                    targetDir.mkdirs()
                }
                val file = File(targetDir, fileName)
                file.writeBytes(bytes)
                ExportResult(
                    fileName = fileName,
                    displayPath = file.absolutePath,
                    uri = Uri.fromFile(file),
                )
            }
        }
    }
}
