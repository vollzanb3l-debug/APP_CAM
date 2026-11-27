package com.retrocam.app.camera

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import android.util.Rational
import android.util.Size
import androidx.camera.core.Camera
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.core.UseCaseGroup
import androidx.camera.core.ViewPort
import androidx.camera.core.resolutionselector.AspectRatioStrategy
import androidx.camera.core.resolutionselector.ResolutionSelector
import androidx.camera.core.resolutionselector.ResolutionStrategy
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.retrocam.app.effects.CaptureProcessor
import com.retrocam.app.effects.EffectEngine
import com.retrocam.app.storage.PhotoSaver
import com.retrocam.app.storage.RecipeExifMetadata
import java.util.concurrent.Executors

/**
 * Manages CameraX lifecycle binding, Preview SurfaceProvider wiring to EffectEngine,
 * and ImageCapture routing to CaptureProcessor + PhotoSaver.
 * Never applies image effects directly.
 */
class CameraController(context: Context) {

    private val appContext = context.applicationContext
    private val mainExecutor = ContextCompat.getMainExecutor(appContext)
    private val captureExecutor = Executors.newSingleThreadExecutor()

    private var cameraProvider: ProcessCameraProvider? = null
    private var boundCamera: Camera? = null
    private var imageCapture: ImageCapture? = null

    private var boundLifecycleOwner: LifecycleOwner? = null
    private var boundEffectEngine: EffectEngine? = null
    private var boundTargetRotation: Int = 0
    private var boundSensorOrientationDegrees: Int? = null
    private var boundViewportWidth: Int = 3
    private var boundViewportHeight: Int = 4
    private var exposureBounds: ExposureCompensationMath.ExposureBounds =
        ExposureCompensationMath.ExposureBounds.UNSUPPORTED
    private var requestedExposureEv: Float = 0f
    private var exposureStateListener: ((ExposureCompensationMath.ExposureBounds, Float) -> Unit)? = null

    var lensFacing: Int = CameraSelector.LENS_FACING_BACK
        private set

    fun isFrontCamera(): Boolean = lensFacing == CameraSelector.LENS_FACING_FRONT

    fun bindToEffectEngine(
        lifecycleOwner: LifecycleOwner,
        effectEngine: EffectEngine,
        targetRotation: Int,
        viewportWidth: Int,
        viewportHeight: Int,
        onExposureStateChanged: (ExposureCompensationMath.ExposureBounds, Float) -> Unit = { _, _ -> },
        onError: (Throwable) -> Unit = {},
    ) {
        boundLifecycleOwner = lifecycleOwner
        boundEffectEngine = effectEngine
        boundTargetRotation = targetRotation
        boundViewportWidth = viewportWidth.coerceAtLeast(1)
        boundViewportHeight = viewportHeight.coerceAtLeast(1)
        exposureStateListener = onExposureStateChanged

        val existing = cameraProvider
        if (existing != null) {
            bindUseCases(existing, onError)
            return
        }

        val future = ProcessCameraProvider.getInstance(appContext)
        future.addListener(
            {
                try {
                    cameraProvider = future.get()
                    bindUseCases(cameraProvider ?: return@addListener, onError)
                } catch (t: Throwable) {
                    Log.e(TAG, "Failed to obtain ProcessCameraProvider", t)
                    onError(t)
                }
            },
            mainExecutor,
        )
    }

    fun setExposureCompensationEv(targetEv: Float): Float {
        requestedExposureEv = targetEv.coerceIn(
            ExposureCompensationMath.UI_MIN_EV,
            ExposureCompensationMath.UI_MAX_EV,
        )
        val bounds = exposureBounds
        val camera = boundCamera ?: return 0f
        if (!bounds.supported) return 0f

        val index = ExposureCompensationMath.evToIndex(requestedExposureEv, bounds)
        val appliedEv = ExposureCompensationMath.indexToEv(index, bounds)
        try {
            camera.cameraControl.setExposureCompensationIndex(index)
        } catch (t: Throwable) {
            Log.w(TAG, "Failed to set exposure compensation index=$index", t)
        }
        return appliedEv
    }

    fun toggleLensFacing(onError: (Throwable) -> Unit = {}): Boolean {
        lensFacing = if (lensFacing == CameraSelector.LENS_FACING_BACK) {
            CameraSelector.LENS_FACING_FRONT
        } else {
            CameraSelector.LENS_FACING_BACK
        }
        val provider = cameraProvider ?: return false
        bindUseCases(provider, onError)
        return true
    }

    fun capturePhoto(
        effectEngine: EffectEngine,
        captureProcessor: CaptureProcessor,
        photoSaver: PhotoSaver,
        metadata: RecipeExifMetadata? = null,
        onComplete: (Result<Uri>) -> Unit,
    ) {
        val capture = imageCapture
        if (capture == null) {
            mainExecutor.execute {
                onComplete(Result.failure(IllegalStateException("Kamera belum siap")))
            }
            return
        }

        val mirrorHorizontal = isFrontCamera()
        val recipe = effectEngine.getRecipe()
        val viewport = effectEngine.getPreviewViewportSize()

        capture.takePicture(
            captureExecutor,
            object : ImageCapture.OnImageCapturedCallback() {
                override fun onCaptureSuccess(image: ImageProxy) {
                    var rawBitmap: android.graphics.Bitmap? = null
                    var processedBitmap: android.graphics.Bitmap? = null
                    try {
                        val buffer = image.planes[0].buffer
                        val bytes = ByteArray(buffer.remaining())
                        buffer.get(bytes)
                        val orientation = FrameTransform.CameraOrientation(
                            rotationDegrees = FrameTransform.normalizeRotation(image.imageInfo.rotationDegrees),
                            mirrorHorizontal = mirrorHorizontal,
                        )

                        rawBitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
                            ?: throw IllegalStateException("Gagal decode hasil JPEG kamera")

                        processedBitmap = captureProcessor.process(
                            sourceBitmap = rawBitmap,
                            rotationDegrees = orientation.rotationDegrees,
                            mirrorHorizontal = orientation.mirrorHorizontal,
                            viewportWidth = viewport.width,
                            viewportHeight = viewport.height,
                            recipe = recipe,
                        )
                        val result = photoSaver.saveJpeg(
                            bitmap = processedBitmap,
                            metadata = metadata,
                        )
                        mainExecutor.execute { onComplete(result) }
                    } catch (t: Throwable) {
                        Log.e(TAG, "Photo capture processing failed", t)
                        mainExecutor.execute { onComplete(Result.failure(t)) }
                    } finally {
                        processedBitmap?.recycle()
                        rawBitmap?.recycle()
                        image.close()
                    }
                }

                override fun onError(exception: ImageCaptureException) {
                    Log.e(TAG, "ImageCapture failed", exception)
                    mainExecutor.execute { onComplete(Result.failure(exception)) }
                }
            },
        )
    }

    fun unbind() {
        cameraProvider?.unbindAll()
        boundCamera = null
        imageCapture = null
    }

    fun release() {
        unbind()
        captureExecutor.shutdown()
    }

    private fun bindUseCases(
        provider: ProcessCameraProvider,
        onError: (Throwable) -> Unit,
    ) {
        val lifecycleOwner = boundLifecycleOwner ?: return
        val effectEngine = boundEffectEngine ?: return

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
                .setTargetRotation(boundTargetRotation)
                .build()
                .apply {
                    setSurfaceProvider { request ->
                        effectEngine.onSurfaceRequest(request) { isFrontCamera() }
                    }
                }

            val captureResolutionSelector = ResolutionSelector.Builder()
                .setAspectRatioStrategy(AspectRatioStrategy.RATIO_4_3_FALLBACK_AUTO_STRATEGY)
                .setResolutionStrategy(ResolutionStrategy.HIGHEST_AVAILABLE_STRATEGY)
                .build()

            val capture = ImageCapture.Builder()
                .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
                .setResolutionSelector(captureResolutionSelector)
                .setTargetRotation(boundTargetRotation)
                .build()

            val viewPort = ViewPort.Builder(
                Rational(boundViewportWidth, boundViewportHeight),
                boundTargetRotation,
            ).build()

            val useCaseGroup = UseCaseGroup.Builder()
                .addUseCase(preview)
                .addUseCase(capture)
                .setViewPort(viewPort)
                .build()

            provider.unbindAll()
            val camera = provider.bindToLifecycle(lifecycleOwner, selector, useCaseGroup)
            boundCamera = camera
            val sensorOrientation = camera.cameraInfo.sensorRotationDegrees
            boundSensorOrientationDegrees = sensorOrientation
            val displayDegrees = FrameTransform.surfaceRotationToDegrees(boundTargetRotation)
            effectEngine.setTargetOrientation(
                FrameTransform.computeCameraOrientation(
                    sensorOrientationDegrees = sensorOrientation,
                    displayRotationDegrees = displayDegrees,
                    isFrontCamera = isFrontCamera(),
                ),
            )
            imageCapture = capture

            val expState = camera.cameraInfo.exposureState
            val range = expState.exposureCompensationRange
            val bounds = ExposureCompensationMath.computeBounds(
                isExposureCompensationSupported = expState.isExposureCompensationSupported,
                hardwareMinIndex = range.lower,
                hardwareMaxIndex = range.upper,
                stepEv = expState.exposureCompensationStep.toFloat(),
            )
            exposureBounds = bounds
            val appliedEv = if (bounds.supported) {
                val index = ExposureCompensationMath.evToIndex(requestedExposureEv, bounds)
                try {
                    camera.cameraControl.setExposureCompensationIndex(index)
                } catch (t: Throwable) {
                    Log.w(TAG, "Failed to apply initial exposure compensation index=$index", t)
                }
                ExposureCompensationMath.indexToEv(index, bounds)
            } else {
                0f
            }
            requestedExposureEv = appliedEv
            exposureStateListener?.let { listener ->
                mainExecutor.execute { listener(bounds, appliedEv) }
            }
        } catch (t: Throwable) {
            Log.e(TAG, "Failed to bind CameraX use cases", t)
            onError(t)
        }
    }

    companion object {
        private const val TAG = "CameraController"
    }
}
