package com.retrocam.app.ui

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.view.Surface
import android.view.WindowManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.view.PreviewView
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.retrocam.app.camera.CameraController
import com.retrocam.app.ui.theme.RetroAmber
import com.retrocam.app.ui.theme.RetroDarkBackground
import com.retrocam.app.ui.theme.RetroError
import com.retrocam.app.ui.theme.RetroSurface
import com.retrocam.app.ui.theme.RetroSurfaceBorder
import com.retrocam.app.ui.theme.RetroTextPrimary
import com.retrocam.app.ui.theme.RetroTextSecondary

@Composable
fun CameraScreen(
    lifecycleOwner: LifecycleOwner,
    onOpenGallery: () -> Unit,
    onOpenSettings: () -> Unit,
) {
    val context = LocalContext.current
    var hasCameraPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) ==
                PackageManager.PERMISSION_GRANTED,
        )
    }
    var cameraError by remember { mutableStateOf<String?>(null) }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
    ) { granted ->
        hasCameraPermission = granted
    }

    LaunchedEffect(Unit) {
        if (!hasCameraPermission) {
            permissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }

    val cameraController = remember(context) { CameraController(context) }

    DisposableEffect(cameraController) {
        onDispose {
            cameraController.unbind()
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
                    text = "RetroCam membutuhkan akses kamera untuk menampilkan viewfinder dan mengambil foto.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = RetroTextSecondary,
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { permissionLauncher.launch(Manifest.permission.CAMERA) },
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
                    text = "RetroCam · M0",
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
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(3f / 4f)
                        .clip(RoundedCornerShape(12.dp))
                        .border(1.dp, RetroSurfaceBorder, RoundedCornerShape(12.dp)),
                ) {
                    AndroidView(
                        modifier = Modifier.fillMaxSize(),
                        factory = { ctx ->
                            PreviewView(ctx).apply {
                                implementationMode = PreviewView.ImplementationMode.PERFORMANCE
                                scaleType = PreviewView.ScaleType.FILL_CENTER
                            }
                        },
                        update = { previewView ->
                            cameraController.bindPreview(
                                lifecycleOwner = lifecycleOwner,
                                surfaceProvider = previewView.surfaceProvider,
                                targetRotation = readDisplayRotation(context),
                                onError = { cameraError = it.localizedMessage ?: "Gagal membuka kamera" },
                            )
                        },
                    )
                }
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
                        .border(3.dp, RetroTextSecondary, CircleShape),
                    contentAlignment = Alignment.Center,
                ) {
                    Box(
                        modifier = Modifier
                            .size(54.dp)
                            .clip(CircleShape)
                            .background(RetroTextSecondary.copy(alpha = 0.4f)),
                    )
                }

                ActionPillButton(
                    label = "Balik",
                    onClick = {
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
