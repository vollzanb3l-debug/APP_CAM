package com.retrocam.app.ui

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.retrocam.app.camera.FrameTransform
import com.retrocam.app.effects.CaptureProcessor
import com.retrocam.app.recipe.ArchetypeSpec
import com.retrocam.app.recipe.CameraSpec
import com.retrocam.app.recipe.FilmSpec
import com.retrocam.app.recipe.FinalRecipe
import com.retrocam.app.recipe.RecipeCatalog
import com.retrocam.app.storage.CalibrationExporter
import com.retrocam.app.storage.CalibrationJsonBuilder
import com.retrocam.app.ui.theme.RetroAmber
import com.retrocam.app.ui.theme.RetroDarkBackground
import com.retrocam.app.ui.theme.RetroError
import com.retrocam.app.ui.theme.RetroSurface
import com.retrocam.app.ui.theme.RetroSurfaceBorder
import com.retrocam.app.ui.theme.RetroTextPrimary
import com.retrocam.app.ui.theme.RetroTextSecondary
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Locale

@Composable
fun CalibrationDebugScreen(
    catalog: RecipeCatalog,
    selectedCamera: CameraSpec?,
    selectedFilm: FilmSpec?,
    baseRecipe: FinalRecipe,
    currentRecipe: FinalRecipe,
    onApplyPreviewOverride: (FinalRecipe) -> Unit,
    onResetOverride: () -> Unit,
    onBack: () -> Unit,
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val captureProcessor = remember(context) { CaptureProcessor(context) }
    val calibrationExporter = remember(context) { CalibrationExporter(context) }

    val cameraArchetypes = remember(catalog.archetypes) {
        catalog.archetypes
            .filter { it.cameraDefaults != null }
            .sortedBy { it.displayName }
    }

    // null = calibrating active camera recipe; non-null = calibrating a specific camera archetype
    var selectedArchetypeId by remember { mutableStateOf<String?>(null) }
    val activeArchetype: ArchetypeSpec? = remember(selectedArchetypeId, cameraArchetypes) {
        cameraArchetypes.firstOrNull { it.id == selectedArchetypeId }
    }
    val resetBaselineRecipe = remember(activeArchetype, baseRecipe) {
        if (activeArchetype != null) {
            CalibrationJsonBuilder.archetypeToPreviewRecipe(activeArchetype)
        } else {
            baseRecipe
        }
    }

    var draftRecipe by remember(selectedArchetypeId, currentRecipe, activeArchetype) {
        mutableStateOf(
            if (activeArchetype != null) {
                CalibrationJsonBuilder.archetypeToPreviewRecipe(activeArchetype)
            } else {
                currentRecipe
            },
        )
    }
    var referenceBitmap by remember { mutableStateOf<Bitmap?>(null) }
    var simulatedBitmap by remember { mutableStateOf<Bitmap?>(null) }
    var loadError by remember { mutableStateOf<String?>(null) }
    var statusNote by remember { mutableStateOf<String?>(null) }
    var isExporting by remember { mutableStateOf(false) }

    val galleryPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
    ) { uri: Uri? ->
        if (uri != null) {
            val decoded = decodeSampledBitmap(context, uri, maxDim = 1024)
            if (decoded != null) {
                referenceBitmap = decoded
                loadError = null
            } else {
                loadError = "Gagal membaca gambar referensi dari galeri."
            }
        }
    }

    LaunchedEffect(referenceBitmap, draftRecipe) {
        val ref = referenceBitmap ?: run {
            simulatedBitmap = null
            return@LaunchedEffect
        }
        delay(60)
        val processed = withContext(Dispatchers.Default) {
            runCatching {
                val targetDims = FrameTransform.computeAspectTargetDimensions(
                    sourceWidth = ref.width,
                    sourceHeight = ref.height,
                    aspectRatio = draftRecipe.aspectRatio,
                )
                captureProcessor.process(
                    sourceBitmap = ref,
                    rotationDegrees = 0,
                    mirrorHorizontal = false,
                    viewportWidth = targetDims.width,
                    viewportHeight = targetDims.height,
                    recipe = draftRecipe,
                )
            }
        }
        processed.fold(
            onSuccess = { bmp ->
                simulatedBitmap = bmp
                loadError = null
            },
            onFailure = { err ->
                loadError = err.localizedMessage ?: "Gagal memproses simulasi GL"
            },
        )
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding(),
        color = RetroDarkBackground,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                TextButton(onClick = onBack) {
                    Text(text = "← Kembali", color = RetroAmber)
                }
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "Debug & Kalibrasi",
                    style = MaterialTheme.typography.titleMedium,
                    color = RetroTextPrimary,
                )
                Spacer(modifier = Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Target selector: Active Camera Recipe OR any of the 15 Camera Archetypes
            Text(
                text = "Target Kalibrasi (${cameraArchetypes.size} Arketipe + Resep Aktif)",
                style = MaterialTheme.typography.titleSmall,
                color = RetroTextPrimary,
            )
            Spacer(modifier = Modifier.height(6.dp))
            LazyRow(
                contentPadding = PaddingValues(end = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                item(key = "__active_recipe__") {
                    TargetChip(
                        label = "Resep Aktif",
                        selected = selectedArchetypeId == null,
                        onClick = {
                            selectedArchetypeId = null
                            statusNote = null
                        },
                    )
                }
                items(cameraArchetypes, key = { it.id }) { arch ->
                    TargetChip(
                        label = arch.displayName,
                        selected = selectedArchetypeId == arch.id,
                        onClick = {
                            selectedArchetypeId = arch.id
                            statusNote = null
                        },
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Active target metadata card
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(RetroSurface)
                    .border(1.dp, RetroSurfaceBorder, RoundedCornerShape(12.dp))
                    .padding(12.dp),
            ) {
                if (activeArchetype != null) {
                    Text(
                        text = "Arketipe: ${activeArchetype.displayName}",
                        style = MaterialTheme.typography.titleSmall,
                        color = RetroAmber,
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "ID: ${activeArchetype.id} · Era: ${activeArchetype.era} · " +
                            "Status: ${activeArchetype.calibrationStatus}",
                        style = MaterialTheme.typography.bodySmall,
                        color = RetroTextPrimary,
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = activeArchetype.summary,
                        style = MaterialTheme.typography.bodySmall,
                        color = RetroTextSecondary,
                    )
                    if (activeArchetype.lookTags.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "lookTags: ${activeArchetype.lookTags.joinToString(", ")}",
                            style = MaterialTheme.typography.labelSmall,
                            color = RetroAmber,
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    activeArchetype.typicalTraits.forEach { trait ->
                        Text(
                            text = "• $trait",
                            style = MaterialTheme.typography.bodySmall,
                            color = RetroTextSecondary,
                        )
                    }
                } else {
                    Text(
                        text = "Kamera Aktif: ${selectedCamera?.displayName ?: "-"}",
                        style = MaterialTheme.typography.titleSmall,
                        color = RetroAmber,
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Archetype: ${selectedCamera?.archetype ?: "-"} · " +
                            "Look: ${selectedCamera?.confidenceLook ?: "-"} · " +
                            "Specs: ${selectedCamera?.confidenceSpecs ?: "-"} · " +
                            "Status: ${selectedCamera?.calibrationStatus ?: "-"}",
                        style = MaterialTheme.typography.bodySmall,
                        color = RetroTextPrimary,
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    selectedCamera?.sources?.forEach { sourceNote ->
                        Text(
                            text = "• $sourceNote",
                            style = MaterialTheme.typography.bodySmall,
                            color = RetroTextSecondary,
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Film Aktif: ${selectedFilm?.displayName ?: "-"}",
                        style = MaterialTheme.typography.titleSmall,
                        color = RetroAmber,
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Archetype: ${selectedFilm?.archetype ?: "-"} · " +
                            "Look: ${selectedFilm?.confidenceLook ?: "-"} · " +
                            "Specs: ${selectedFilm?.confidenceSpecs ?: "-"} · " +
                            "Status: ${selectedFilm?.calibrationStatus ?: "-"}",
                        style = MaterialTheme.typography.bodySmall,
                        color = RetroTextPrimary,
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    selectedFilm?.sources?.forEach { sourceNote ->
                        Text(
                            text = "• $sourceNote",
                            style = MaterialTheme.typography.bodySmall,
                            color = RetroTextSecondary,
                        )
                    }
                }
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Ekspor \"Simpan sebagai calibrated\" menulis JSON ke Documents/RetroCam/export/.",
                    style = MaterialTheme.typography.labelSmall,
                    color = RetroTextSecondary,
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Side-by-side reference photo comparison
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Bandingkan Referensi",
                    style = MaterialTheme.typography.titleSmall,
                    color = RetroTextPrimary,
                )
                Spacer(modifier = Modifier.weight(1f))
                Button(
                    onClick = { galleryPicker.launch("image/*") },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = RetroAmber,
                        contentColor = RetroDarkBackground,
                    ),
                ) {
                    Text(text = "Pilih Foto")
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            val refBmp = referenceBitmap
            val simBmp = simulatedBitmap
            if (refBmp != null) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    ComparisonPane(
                        title = "Referensi",
                        bitmap = refBmp,
                        modifier = Modifier.weight(1f),
                    )
                    ComparisonPane(
                        title = "Simulasi Resep",
                        bitmap = simBmp ?: refBmp,
                        modifier = Modifier.weight(1f),
                    )
                }
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(140.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(RetroSurface)
                        .border(1.dp, RetroSurfaceBorder, RoundedCornerShape(12.dp))
                        .padding(16.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = "Pilih foto referensi dari galeri untuk melihat perbandingan berdampingan.",
                        style = MaterialTheme.typography.bodySmall,
                        color = RetroTextSecondary,
                    )
                }
            }

            loadError?.let { err ->
                Spacer(modifier = Modifier.height(6.dp))
                Text(text = err, style = MaterialTheme.typography.bodySmall, color = RetroError)
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Color grade parameter sliders
            Text(
                text = "Parameter Color Grade & Sensor",
                style = MaterialTheme.typography.titleSmall,
                color = RetroTextPrimary,
            )
            Spacer(modifier = Modifier.height(6.dp))

            ParameterSlider(
                label = "Temp",
                value = draftRecipe.temp,
                range = -0.5f..0.5f,
                onValueChange = { draftRecipe = draftRecipe.copy(temp = it) },
            )
            ParameterSlider(
                label = "Tint",
                value = draftRecipe.tint,
                range = -0.5f..0.5f,
                onValueChange = { draftRecipe = draftRecipe.copy(tint = it) },
            )
            ParameterSlider(
                label = "Contrast",
                value = draftRecipe.contrast,
                range = 0.6f..1.6f,
                onValueChange = { draftRecipe = draftRecipe.copy(contrast = it) },
            )
            ParameterSlider(
                label = "Saturation",
                value = draftRecipe.saturation,
                range = 0.0f..2.0f,
                onValueChange = { draftRecipe = draftRecipe.copy(saturation = it) },
            )
            ParameterSlider(
                label = "Highlight Rolloff",
                value = draftRecipe.highlightRolloff,
                range = 0.0f..1.0f,
                onValueChange = { draftRecipe = draftRecipe.copy(highlightRolloff = it) },
            )
            ParameterSlider(
                label = "Shadow Lift",
                value = draftRecipe.shadowLift,
                range = 0.0f..0.4f,
                onValueChange = { draftRecipe = draftRecipe.copy(shadowLift = it) },
            )
            ParameterSlider(
                label = "Black Lift",
                value = draftRecipe.blackLift,
                range = 0.0f..0.3f,
                onValueChange = { draftRecipe = draftRecipe.copy(blackLift = it) },
            )
            ParameterSlider(
                label = "Tint Strength",
                value = draftRecipe.tintStrength,
                range = 0.0f..0.5f,
                onValueChange = { draftRecipe = draftRecipe.copy(tintStrength = it) },
            )
            ParameterSlider(
                label = "Sharpen",
                value = draftRecipe.sharpen,
                range = 0.0f..1.0f,
                onValueChange = { draftRecipe = draftRecipe.copy(sharpen = it) },
            )

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Parameter Geometri & Optik",
                style = MaterialTheme.typography.titleSmall,
                color = RetroTextPrimary,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                listOf("4:3", "3:4", "3:2", "2:3", "1:1", "16:9").forEach { ratio ->
                    val selected = draftRecipe.aspectRatio == ratio
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(8.dp))
                            .background(RetroSurface)
                            .border(
                                width = 1.dp,
                                color = if (selected) RetroAmber else RetroSurfaceBorder,
                                shape = RoundedCornerShape(8.dp),
                            )
                            .clickable { draftRecipe = draftRecipe.copy(aspectRatio = ratio) }
                            .padding(vertical = 8.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = ratio,
                            style = MaterialTheme.typography.labelSmall,
                            color = if (selected) RetroAmber else RetroTextSecondary,
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(6.dp))
            ParameterSlider(
                label = "Lens Distortion (k1)",
                value = draftRecipe.distortionK1,
                range = -0.35f..0.35f,
                onValueChange = { draftRecipe = draftRecipe.copy(distortionK1 = it) },
            )
            ParameterSlider(
                label = "Softness",
                value = draftRecipe.softness,
                range = 0.0f..1.0f,
                onValueChange = { draftRecipe = draftRecipe.copy(softness = it) },
            )
            ParameterSlider(
                label = "Chromatic Aberration",
                value = draftRecipe.chromaticAberration,
                range = 0.0f..1.0f,
                onValueChange = { draftRecipe = draftRecipe.copy(chromaticAberration = it) },
            )
            ParameterSlider(
                label = "Bloom Intensity",
                value = draftRecipe.bloomIntensity,
                range = 0.0f..1.0f,
                onValueChange = { draftRecipe = draftRecipe.copy(bloomIntensity = it) },
            )
            ParameterSlider(
                label = "Bloom Threshold",
                value = draftRecipe.bloomThreshold,
                range = 0.40f..0.98f,
                onValueChange = { draftRecipe = draftRecipe.copy(bloomThreshold = it) },
            )
            ParameterSlider(
                label = "Bloom Radius (Short Side)",
                value = draftRecipe.bloomRadius,
                range = 0.002f..0.030f,
                format = "%.4f",
                onValueChange = { draftRecipe = draftRecipe.copy(bloomRadius = it) },
            )
            ParameterSlider(
                label = "Halation Intensity",
                value = draftRecipe.halationIntensity,
                range = 0.0f..1.0f,
                onValueChange = { draftRecipe = draftRecipe.copy(halationIntensity = it) },
            )
            ParameterSlider(
                label = "Flare",
                value = draftRecipe.flare,
                range = 0.0f..1.0f,
                onValueChange = { draftRecipe = draftRecipe.copy(flare = it) },
            )
            ParameterSlider(
                label = "Vignette Strength",
                value = draftRecipe.vignetteStrength,
                range = 0.0f..1.0f,
                onValueChange = { draftRecipe = draftRecipe.copy(vignetteStrength = it) },
            )
            ParameterSlider(
                label = "Vignette Radius",
                value = draftRecipe.vignetteRadius,
                range = 0.2f..1.4f,
                onValueChange = { draftRecipe = draftRecipe.copy(vignetteRadius = it) },
            )

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Parameter Grain & Noise",
                style = MaterialTheme.typography.titleSmall,
                color = RetroTextPrimary,
            )
            Spacer(modifier = Modifier.height(4.dp))
            ParameterSlider(
                label = "Grain / Sensor Noise Amount",
                value = draftRecipe.combinedNoiseAmount,
                range = 0.0f..0.7f,
                onValueChange = { draftRecipe = draftRecipe.copy(combinedNoiseAmount = it) },
            )
            ParameterSlider(
                label = "Grain Size (Short Side)",
                value = draftRecipe.grainSize,
                range = 0.0005f..0.0060f,
                format = "%.4f",
                onValueChange = { draftRecipe = draftRecipe.copy(grainSize = it) },
            )
            ParameterSlider(
                label = "Grain Roughness",
                value = draftRecipe.grainRoughness,
                range = 0.0f..1.0f,
                onValueChange = { draftRecipe = draftRecipe.copy(grainRoughness = it) },
            )
            ParameterSlider(
                label = "Sensor Noise Chroma",
                value = draftRecipe.sensorNoiseChroma,
                range = 0.0f..1.0f,
                onValueChange = { draftRecipe = draftRecipe.copy(sensorNoiseChroma = it) },
            )

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Parameter Overlay",
                style = MaterialTheme.typography.titleSmall,
                color = RetroTextPrimary,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Date Stamp Digital",
                    style = MaterialTheme.typography.labelMedium,
                    color = RetroTextPrimary,
                )
                Spacer(modifier = Modifier.weight(1f))
                OutlinedButton(
                    onClick = {
                        draftRecipe = draftRecipe.copy(dateStampEnabled = !draftRecipe.dateStampEnabled)
                    },
                ) {
                    Text(
                        text = if (draftRecipe.dateStampEnabled) "AKTIF" else "NONAKTIF",
                        color = if (draftRecipe.dateStampEnabled) RetroAmber else RetroTextSecondary,
                    )
                }
            }
            ParameterSlider(
                label = "Date Stamp Intensity",
                value = draftRecipe.dateStampIntensity,
                range = 0.0f..1.0f,
                onValueChange = { draftRecipe = draftRecipe.copy(dateStampIntensity = it) },
            )
            ParameterSlider(
                label = "Light Leak Intensity (Opsional)",
                value = draftRecipe.lightLeakIntensity,
                range = 0.0f..1.0f,
                onValueChange = { draftRecipe = draftRecipe.copy(lightLeakIntensity = it) },
            )
            ParameterSlider(
                label = "Light Leak Pattern (Seed)",
                value = draftRecipe.lightLeakSeed,
                range = 0.0f..1.0f,
                onValueChange = { draftRecipe = draftRecipe.copy(lightLeakSeed = it) },
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                OutlinedButton(
                    modifier = Modifier.weight(1f),
                    onClick = {
                        draftRecipe = resetBaselineRecipe
                        onResetOverride()
                        statusNote = if (activeArchetype != null) {
                            "Direset ke nilai awal arketipe ${activeArchetype.id}"
                        } else {
                            "Direset ke resep JSON aktif"
                        }
                    },
                ) {
                    Text(text = "Reset", color = RetroTextPrimary)
                }
                Button(
                    modifier = Modifier.weight(1f),
                    onClick = {
                        onApplyPreviewOverride(draftRecipe)
                        statusNote = "Diterapkan ke preview kamera"
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = RetroSurface,
                        contentColor = RetroAmber,
                    ),
                ) {
                    Text(text = "Terapkan ke Preview")
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                modifier = Modifier.fillMaxWidth(),
                enabled = !isExporting && (activeArchetype != null || selectedCamera != null),
                onClick = {
                    val targetArch = activeArchetype
                    val targetCam = selectedCamera
                    if (targetArch == null && targetCam == null) return@Button
                    isExporting = true
                    statusNote = "Menyimpan JSON ke Documents/RetroCam/export/..."
                    scope.launch {
                        val result = withContext(Dispatchers.IO) {
                            if (targetArch != null) {
                                calibrationExporter.exportCalibratedArchetype(targetArch, draftRecipe)
                            } else {
                                val cam = targetCam!!
                                val matchingArch = catalog.archetypes.firstOrNull { it.id == cam.archetype }
                                calibrationExporter.exportCalibratedCamera(cam, draftRecipe, matchingArch)
                            }
                        }
                        isExporting = false
                        result.fold(
                            onSuccess = { exported ->
                                statusNote = "Tersimpan (calibrated): ${exported.displayPath}"
                                loadError = null
                            },
                            onFailure = { err ->
                                loadError = err.localizedMessage ?: "Gagal mengekspor JSON kalibrasi"
                                statusNote = null
                            },
                        )
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = RetroAmber,
                    contentColor = RetroDarkBackground,
                ),
            ) {
                Text(
                    text = if (isExporting) "Menyimpan..." else "Simpan sebagai calibrated",
                )
            }

            statusNote?.let { note ->
                Spacer(modifier = Modifier.height(6.dp))
                Text(text = note, style = MaterialTheme.typography.bodySmall, color = RetroAmber)
            }

            Spacer(modifier = Modifier.height(14.dp))

            // JSON validation / load error log (ARCHITECTURE.md Section 7.5)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(RetroSurface)
                    .border(1.dp, RetroSurfaceBorder, RoundedCornerShape(12.dp))
                    .padding(12.dp),
            ) {
                Text(
                    text = "Status Validasi JSON (${catalog.loadErrors.size} error)",
                    style = MaterialTheme.typography.titleSmall,
                    color = RetroTextPrimary,
                )
                Spacer(modifier = Modifier.height(4.dp))
                if (catalog.loadErrors.isEmpty()) {
                    Text(
                        text = "Semua file JSON valid (${catalog.cameras.size} camera, " +
                            "${catalog.lenses.size} lens, ${catalog.films.size} film, " +
                            "${catalog.archetypes.size} archetype).",
                        style = MaterialTheme.typography.bodySmall,
                        color = RetroTextSecondary,
                    )
                } else {
                    catalog.loadErrors.forEach { err ->
                        Text(
                            text = "• ${err.assetPath}: ${err.message}",
                            style = MaterialTheme.typography.bodySmall,
                            color = RetroError,
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun TargetChip(
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(if (selected) RetroAmber else RetroSurface)
            .border(
                width = 1.dp,
                color = if (selected) RetroAmber else RetroSurfaceBorder,
                shape = RoundedCornerShape(16.dp),
            )
            .clickable(onClick = onClick)
            .padding(horizontal = 12.dp, vertical = 7.dp),
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = if (selected) RetroDarkBackground else RetroTextPrimary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Composable
private fun ComparisonPane(
    title: String,
    bitmap: Bitmap,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.labelSmall,
            color = RetroTextSecondary,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Image(
            bitmap = bitmap.asImageBitmap(),
            contentDescription = title,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(RoundedCornerShape(10.dp))
                .background(RetroSurface)
                .border(1.dp, RetroSurfaceBorder, RoundedCornerShape(10.dp)),
        )
    }
}

@Composable
private fun ParameterSlider(
    label: String,
    value: Float,
    range: ClosedFloatingPointRange<Float>,
    onValueChange: (Float) -> Unit,
    format: String = "%.2f",
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelMedium,
                color = RetroTextPrimary,
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = String.format(Locale.US, format, value),
                style = MaterialTheme.typography.labelMedium,
                color = RetroAmber,
            )
        }
        Slider(
            value = value.coerceIn(range.start, range.endInclusive),
            onValueChange = onValueChange,
            valueRange = range,
            colors = SliderDefaults.colors(
                thumbColor = RetroAmber,
                activeTrackColor = RetroAmber,
                inactiveTrackColor = RetroSurfaceBorder,
            ),
        )
    }
}

private fun decodeSampledBitmap(context: Context, uri: Uri, maxDim: Int): Bitmap? {
    return try {
        val bounds = BitmapFactory.Options().apply { inJustDecodeBounds = true }
        context.contentResolver.openInputStream(uri)?.use { stream ->
            BitmapFactory.decodeStream(stream, null, bounds)
        }
        if (bounds.outWidth <= 0 || bounds.outHeight <= 0) return null

        var sampleSize = 1
        while (bounds.outWidth / sampleSize > maxDim || bounds.outHeight / sampleSize > maxDim) {
            sampleSize *= 2
        }

        val decodeOpts = BitmapFactory.Options().apply { inSampleSize = sampleSize }
        context.contentResolver.openInputStream(uri)?.use { stream ->
            BitmapFactory.decodeStream(stream, null, decodeOpts)
        }
    } catch (_: Throwable) {
        null
    }
}
