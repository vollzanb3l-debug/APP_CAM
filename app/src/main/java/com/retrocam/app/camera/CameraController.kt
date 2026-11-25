package com.retrocam.app.camera

import android.content.Context
import android.util.Log
import android.util.Size
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.core.resolutionselector.AspectRatioStrategy
import androidx.camera.core.resolutionselector.ResolutionSelector
import androidx.camera.core.resolutionselector.ResolutionStrategy
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner

/**
 * Manages CameraX lifecycle binding and camera facing state.
 * Never applies image effects directly.
 */
class CameraController(context: Context) {

    private val appContext = context.applicationContext
    private var cameraProvider: ProcessCameraProvider? = null
    private var boundLifecycleOwner: LifecycleOwner? = null
    private var currentSurfaceProvider: Preview.SurfaceProvider? = null
    private var currentTargetRotation: Int = 0

    var lensFacing: Int = CameraSelector.LENS_FACING_BACK
        private set

    fun isFrontCamera(): Boolean = lensFacing == CameraSelector.LENS_FACING_FRONT

    fun bindPreview(
        lifecycleOwner: LifecycleOwner,
        surfaceProvider: Preview.SurfaceProvider,
        targetRotation: Int,
        onError: (Throwable) -> Unit = {},
    ) {
        boundLifecycleOwner = lifecycleOwner
        currentSurfaceProvider = surfaceProvider
        currentTargetRotation = targetRotation

        val existing = cameraProvider
        if (existing != null) {
            bindUseCases(existing, lifecycleOwner, surfaceProvider, targetRotation, onError)
            return
        }

        val future = ProcessCameraProvider.getInstance(appContext)
        future.addListener(
            {
                try {
                    val provider = future.get()
                    cameraProvider = provider
                    val owner = boundLifecycleOwner ?: return@addListener
                    val sp = currentSurfaceProvider ?: return@addListener
                    bindUseCases(provider, owner, sp, currentTargetRotation, onError)
                } catch (t: Throwable) {
                    Log.e(TAG, "Failed to obtain ProcessCameraProvider", t)
                    onError(t)
                }
            },
            ContextCompat.getMainExecutor(appContext),
        )
    }

    fun toggleLensFacing(onError: (Throwable) -> Unit = {}): Boolean {
        lensFacing = if (lensFacing == CameraSelector.LENS_FACING_BACK) {
            CameraSelector.LENS_FACING_FRONT
        } else {
            CameraSelector.LENS_FACING_BACK
        }
        val provider = cameraProvider ?: return false
        val owner = boundLifecycleOwner ?: return false
        val sp = currentSurfaceProvider ?: return false
        bindUseCases(provider, owner, sp, currentTargetRotation, onError)
        return true
    }

    fun unbind() {
        cameraProvider?.unbindAll()
    }

    private fun bindUseCases(
        provider: ProcessCameraProvider,
        lifecycleOwner: LifecycleOwner,
        surfaceProvider: Preview.SurfaceProvider,
        targetRotation: Int,
        onError: (Throwable) -> Unit,
    ) {
        try {
            val selector = CameraSelector.Builder()
                .requireLensFacing(lensFacing)
                .build()

            val previewResolutionSelector = ResolutionSelector.Builder()
                .setAspectRatioStrategy(AspectRatioStrategy.RATIO_4_3_FALLBACK_AUTO_STRATEGY)
                .setResolutionStrategy(
                    ResolutionStrategy(
                        Size(1280, 960),
                        ResolutionStrategy.FALLBACK_RULE_CLOSEST_HIGHER_THEN_LOWER,
                    ),
                )
                .build()

            val preview = Preview.Builder()
                .setResolutionSelector(previewResolutionSelector)
                .setTargetRotation(targetRotation)
                .build()
                .apply {
                    setSurfaceProvider(surfaceProvider)
                }

            provider.unbindAll()
            provider.bindToLifecycle(lifecycleOwner, selector, preview)
        } catch (t: Throwable) {
            Log.e(TAG, "Failed to bind CameraX preview", t)
            onError(t)
        }
    }

    companion object {
        private const val TAG = "CameraController"
    }
}
