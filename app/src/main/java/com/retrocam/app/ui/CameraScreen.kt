package com.retrocam.app.ui

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.view.Surface
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.WindowManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface as ComposeSurface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.retrocam.app.camera.CameraController
import com.retrocam.app.effects.CaptureProcessor
import com.retrocam.app.effects.EffectEngine
import com.retrocam.app.storage.PhotoSaver
import com.retrocam.app.ui.theme.RetroAmber
import com.retrocam.app.ui.theme.RetroDarkBackground
import com.retrocam.app.ui.theme.RetroError
import com.retrocam.app.ui.theme.RetroSurface
import com.retrocam.app.ui.theme.RetroSurfaceBorder
import com.retrocam.app.ui.theme.RetroTextPrimary
import com.retrocam.app.ui.theme.RetroTextSecondary
import kotlinx.coroutines.delay

@Composable
fun CameraScreen(
    lifecycleOwner: LifecycleOwner,
    onOpenGallery: () -> Unit,
    onOpenSettings: () -> Unit,
) {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val displayRotation = remember(configuration.orientation) { readDisplayRotation(context) }

    val requiredPermissions = remember {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        } else {
            arrayOf(Manifest.permission.CAMERA)
        }
    }

    var hasCameraPermission by remember {
        mutableStateOf(hasPermission(context, Manifest.permission.CAMERA))
    }
    var cameraError by remember { mutableStateOf<String?>(null) }
    var statusMessage by remember { mutableStateOf<String?>(null) }
    var isCapturing by remember { mutableStateOf(false) }
    var viewportSize by remember { mutableStateOf(IntSize(3, 4)) }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
    ) { results ->
        hasCameraPermission = results[Manifest.permission.CAMERA] == true ||
            hasPermission(context, Manifest.permission.CAMERA)
    }

    LaunchedEffect(Unit) {
        val missing = requiredPermissions.any { !hasPermission(context, it) }
        if (missing) {
            permissionLauncher.launch(requiredPermissions)
        }
    }

    LaunchedEffect(statusMessage) {
        if (statusMessage != null) {
            delay(2500)
            statusMessage = null
        }
    }

    val effectEngine = remember(context) { EffectEngine(context) }
    val captureProcessor = remember(context) { CaptureProcessor(context) }
    val photoSaver = remember(context) { PhotoSaver(context) }
    val cameraController = remember(context) { CameraController(context) }

    DisposableEffect(cameraController, effectEngine) {
        onDispose {
            cameraController.release()
            effectEngine.release()
        }
    }

    LaunchedEffect(hasCameraPermission, displayRotation, viewportSize) {
        if (hasCameraPermission && viewportSize.width > 0 && viewportSize.height > 0) {
            cameraController.bindToEffectEngine(
                lifecycleOwner = lifecycleOwner,
                effectEngine = effectEngine,
                targetRotation = displayRotation,
                viewportWidth = viewportSize.width,
                viewportHeight = viewportSize.height,
                onError = { cameraError = it.localizedMessage ?: "Gagal membuka kamera" },
            )
        }
    }

    ComposeSurface(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding(),
        color = RetroDarkBackground,
    ) {
        if (!hasCameraPermission) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "Izin kamera diperlukan",
                    style = MaterialTheme.typography.headlineSmall,
                    color = RetroTextPrimary,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "RetroCam membutuhkan akses kamera untuk menampilkan viewfinder dan menyimpan foto.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = RetroTextSecondary,
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { permissionLauncher.launch(requiredPermissions) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = RetroAmber,
                        contentColor = RetroDarkBackground,
                    ),
                ) {
                    Text(text = "Izinkan Kamera")
                }
            }
            return@ComposeSurface
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "RetroCam · GL Passthrough",
                    style = MaterialTheme.typography.labelLarge,
                    color = RetroAmber,
                )
                Spacer(modifier = Modifier.weight(1f))
                TextButton(onClick = onOpenSettings) {
                    Text(text = "Pengaturan", color = RetroTextPrimary)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center,
            ) {
                val viewfinderAspect = if (
                    configuration.orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE
                ) {
                    4f / 3f
                } else {
                    3f / 4f
                }

                Box(
                    modifier = Modifier
                        .aspectRatio(viewfinderAspect, matchHeightConstraintsFirst = true)
                        .clip(RoundedCornerShape(12.dp))
                        .border(1.dp, RetroSurfaceBorder, RoundedCornerShape(12.dp))
                        .onSizeChanged { size ->
                            if (size.width > 0 && size.height > 0) {
                                viewportSize = size
                            }
                        },
                ) {
                    AndroidView(
                        modifier = Modifier.fillMaxSize(),
                        factory = { ctx ->
                            SurfaceView(ctx).apply {
                                holder.addCallback(
                                    object : SurfaceHolder.Callback {
                                        override fun surfaceCreated(holder: SurfaceHolder) {
                                            val frame = holder.surfaceFrame
                                            if (frame.width() > 0 && frame.height() > 0) {
                                                effectEngine.attachPreviewSurface(
                                                    holder.surface,
                                                    frame.width(),
                                                    frame.height(),
                                                )
                                            }
                                        }

                                        override fun surfaceChanged(
                                            holder: SurfaceHolder,
                                            format: Int,
                                            width: Int,
                                            height: Int,
                                        ) {
                                            if (width > 0 && height > 0) {
                                                effectEngine.attachPreviewSurface(
                                                    holder.surface,
                                                    width,
                                                    height,
                                                )
                                            }
                                        }

                                        override fun surfaceDestroyed(holder: SurfaceHolder) {
                                            effectEngine.detachPreviewSurface()
                                        }
                                    },
                                )
                            }
                        },
                    )
                }
            }

            statusMessage?.let { msg ->
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = msg,
                    style = MaterialTheme.typography.bodySmall,
                    color = RetroAmber,
                )
            }

            cameraError?.let { err ->
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = err,
                    style = MaterialTheme.typography.bodySmall,
                    color = RetroError,
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                ActionPillButton(
                    label = "Galeri",
                    onClick = onOpenGallery,
                )

                Box(
                    modifier = Modifier
                        .size(72.dp)
                        .clip(CircleShape)
                        .background(RetroSurface)
                        .border(
                            width = 3.dp,
                            color = if (isCapturing) RetroAmber else RetroTextPrimary,
                            shape = CircleShape,
                        )
                        .clickable(enabled = !isCapturing) {
                            isCapturing = true
                            cameraError = null
                            cameraController.capturePhoto(
                                effectEngine = effectEngine,
                                captureProcessor = captureProcessor,
                                photoSaver = photoSaver,
                            ) { result ->
                                isCapturing = false
                                result.fold(
                                    onSuccess = {
                                        statusMessage = "Foto tersimpan ke galeri"
                                    },
                                    onFailure = { t ->
                                        cameraError = t.localizedMessage ?: "Gagal menyimpan foto"
                                    },
                                )
                            }
                        },
                    contentAlignment = Alignment.Center,
                ) {
                    Box(
                        modifier = Modifier
                            .size(54.dp)
                            .clip(CircleShape)
                            .background(
                                if (isCapturing) {
                                    RetroAmber.copy(alpha = 0.6f)
                                } else {
                                    RetroTextPrimary
                                },
                            ),
                    )
                }

                ActionPillButton(
                    label = "Balik",
                    onClick = {
                        cameraError = null
                        cameraController.toggleLensFacing(
                            onError = { cameraError = it.localizedMessage ?: "Gagal ganti kamera" },
                        )
                    },
                )
            }
        }
    }
}

@Composable
private fun ActionPillButton(
    label: String,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(24.dp))
            .background(RetroSurface)
            .border(1.dp, RetroSurfaceBorder, RoundedCornerShape(24.dp))
            .clickable(onClick = onClick)
            .padding(horizontal = 18.dp, vertical = 12.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelLarge,
            color = RetroTextPrimary,
        )
    }
}

private fun hasPermission(context: Context, permission: String): Boolean {
    return ContextCompat.checkSelfPermission(context, permission) ==
        PackageManager.PERMISSION_GRANTED
}

private fun readDisplayRotation(context: Context): Int {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        context.display?.rotation ?: Surface.ROTATION_0
    } else {
        @Suppress("DEPRECATION")
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as? WindowManager
        @Suppress("DEPRECATION")
        wm?.defaultDisplay?.rotation ?: Surface.ROTATION_0
    }
}
