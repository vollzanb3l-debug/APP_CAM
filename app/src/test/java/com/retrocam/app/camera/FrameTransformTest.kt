package com.retrocam.app.camera

import org.junit.Assert.assertEquals
import org.junit.Test

class FrameTransformTest {

    private val eps = 1e-5f

    @Test
    fun uprightDimensions_swapsOn90And270() {
        assertEquals(FrameTransform.Dimensions(1280, 960), FrameTransform.uprightDimensions(1280, 960, 0))
        assertEquals(FrameTransform.Dimensions(960, 1280), FrameTransform.uprightDimensions(1280, 960, 90))
        assertEquals(FrameTransform.Dimensions(1280, 960), FrameTransform.uprightDimensions(1280, 960, 180))
        assertEquals(FrameTransform.Dimensions(960, 1280), FrameTransform.uprightDimensions(1280, 960, 270))
        assertEquals(FrameTransform.Dimensions(960, 1280), FrameTransform.uprightDimensions(1280, 960, -90))
    }

    @Test
    fun centerCrop_matchesWiderAndTallerAspects() {
        val wider = FrameTransform.computeCenterCropScale(1600, 900, 300, 400)
        assertEquals(0.75f / (16f / 9f), wider.scaleX, eps)
        assertEquals(1f, wider.scaleY, eps)

        val taller = FrameTransform.computeCenterCropScale(900, 1600, 300, 400)
        assertEquals(1f, taller.scaleX, eps)
        assertEquals((9f / 16f) / 0.75f, taller.scaleY, eps)

        val croppedDims = FrameTransform.computeCroppedOutputDimensions(1600, 900, 300, 400)
        assertEquals(675, croppedDims.width)
        assertEquals(900, croppedDims.height)
    }

    @Test
    fun photoMatrix_rotates90CwWithoutMirrorCorrectly() {
        // 4:3 landscape sensor rotated 90 CW to 3:4 portrait matching 3:4 viewport.
        val m = FrameTransform.buildPhotoTexMatrix(
            sourceWidth = 4000,
            sourceHeight = 3000,
            rotationDegrees = 90,
            mirrorHorizontal = false,
            viewportWidth = 300,
            viewportHeight = 400,
        )

        // Photo top-left (0,0) must sample raw buffer bottom-left (0,1).
        assertUvEquals(0f to 1f, FrameTransform.mapUv(m, 0f, 0f))
        // Photo top-right (1,0) must sample raw buffer top-left (0,0).
        assertUvEquals(0f to 0f, FrameTransform.mapUv(m, 1f, 0f))
        // Photo bottom-left (0,1) must sample raw buffer bottom-right (1,1).
        assertUvEquals(1f to 1f, FrameTransform.mapUv(m, 0f, 1f))
        // Photo bottom-right (1,1) must sample raw buffer top-right (1,0).
        assertUvEquals(1f to 0f, FrameTransform.mapUv(m, 1f, 1f))
    }

    @Test
    fun previewAndPhoto_mapSameSourceCoordinatesAcrossAllRotationsAndMirrorModes() {
        // Standard Android SurfaceTexture matrix flips GL Y so (0,1) becomes (0,0) top-down.
        val standardSurfaceTextureMatrix = FrameTransform.verticalFlipMatrix()

        val rotations = listOf(0, 90, 180, 270)
        val mirrors = listOf(false, true)

        for (rotation in rotations) {
            for (mirror in mirrors) {
                val previewM = FrameTransform.buildPreviewTexMatrix(
                    surfaceTextureMatrix = standardSurfaceTextureMatrix,
                    bufferWidth = 1280,
                    bufferHeight = 720,
                    rotationDegrees = rotation,
                    mirrorHorizontal = mirror,
                    viewportWidth = 900,
                    viewportHeight = 1200,
                )
                val photoM = FrameTransform.buildPhotoTexMatrix(
                    sourceWidth = 4000,
                    sourceHeight = 2250,
                    rotationDegrees = rotation,
                    mirrorHorizontal = mirror,
                    viewportWidth = 900,
                    viewportHeight = 1200,
                )

                // Screen quad UVs are GL bottom-up: top-left=(0,1), top-right=(1,1),
                // bottom-left=(0,0), bottom-right=(1,0).
                // Photo UVs are top-down: top-left=(0,0), top-right=(1,0),
                // bottom-left=(0,1), bottom-right=(1,1).
                val previewTopLeft = FrameTransform.mapUv(previewM, 0f, 1f)
                val photoTopLeft = FrameTransform.mapUv(photoM, 0f, 0f)
                assertUvEquals(photoTopLeft, previewTopLeft)

                val previewTopRight = FrameTransform.mapUv(previewM, 1f, 1f)
                val photoTopRight = FrameTransform.mapUv(photoM, 1f, 0f)
                assertUvEquals(photoTopRight, previewTopRight)

                val previewBottomLeft = FrameTransform.mapUv(previewM, 0f, 0f)
                val photoBottomLeft = FrameTransform.mapUv(photoM, 0f, 1f)
                assertUvEquals(photoBottomLeft, previewBottomLeft)

                val previewBottomRight = FrameTransform.mapUv(previewM, 1f, 0f)
                val photoBottomRight = FrameTransform.mapUv(photoM, 1f, 1f)
                assertUvEquals(photoBottomRight, previewBottomRight)
            }
        }
    }

    @Test
    fun computeCameraOrientation_coversSensor90And270AcrossAllDisplayRotationsAndCameras() {
        // Back camera (opposite facing): (sensor - display + 360) % 360, mirror = false
        assertEquals(
            FrameTransform.CameraOrientation(90, false),
            FrameTransform.computeCameraOrientation(90, 0, isFrontCamera = false),
        )
        assertEquals(
            FrameTransform.CameraOrientation(0, false),
            FrameTransform.computeCameraOrientation(90, 90, isFrontCamera = false),
        )
        assertEquals(
            FrameTransform.CameraOrientation(270, false),
            FrameTransform.computeCameraOrientation(90, 180, isFrontCamera = false),
        )
        assertEquals(
            FrameTransform.CameraOrientation(180, false),
            FrameTransform.computeCameraOrientation(90, 270, isFrontCamera = false),
        )

        assertEquals(
            FrameTransform.CameraOrientation(270, false),
            FrameTransform.computeCameraOrientation(270, 0, isFrontCamera = false),
        )
        assertEquals(
            FrameTransform.CameraOrientation(180, false),
            FrameTransform.computeCameraOrientation(270, 90, isFrontCamera = false),
        )
        assertEquals(
            FrameTransform.CameraOrientation(90, false),
            FrameTransform.computeCameraOrientation(270, 180, isFrontCamera = false),
        )
        assertEquals(
            FrameTransform.CameraOrientation(0, false),
            FrameTransform.computeCameraOrientation(270, 270, isFrontCamera = false),
        )

        // Front camera (same facing): (sensor + display) % 360, mirror = true
        assertEquals(
            FrameTransform.CameraOrientation(270, true),
            FrameTransform.computeCameraOrientation(270, 0, isFrontCamera = true),
        )
        assertEquals(
            FrameTransform.CameraOrientation(0, true),
            FrameTransform.computeCameraOrientation(270, 90, isFrontCamera = true),
        )
        assertEquals(
            FrameTransform.CameraOrientation(90, true),
            FrameTransform.computeCameraOrientation(270, 180, isFrontCamera = true),
        )
        assertEquals(
            FrameTransform.CameraOrientation(180, true),
            FrameTransform.computeCameraOrientation(270, 270, isFrontCamera = true),
        )

        assertEquals(
            FrameTransform.CameraOrientation(90, true),
            FrameTransform.computeCameraOrientation(90, 0, isFrontCamera = true),
        )
        assertEquals(
            FrameTransform.CameraOrientation(180, true),
            FrameTransform.computeCameraOrientation(90, 90, isFrontCamera = true),
        )
        assertEquals(
            FrameTransform.CameraOrientation(270, true),
            FrameTransform.computeCameraOrientation(90, 180, isFrontCamera = true),
        )
        assertEquals(
            FrameTransform.CameraOrientation(0, true),
            FrameTransform.computeCameraOrientation(90, 270, isFrontCamera = true),
        )
    }

    @Test
    fun previewWithCameraHalBakedSurfaceTexture_matchesPhotoAcrossAllSensorsDisplaysAndCameras() {
        val flipY = FrameTransform.verticalFlipMatrix()
        val samplePoints = listOf(0f to 0f, 1f to 0f, 0f to 1f, 1f to 1f, 0.25f to 0.75f)

        for (sensorOrientation in listOf(90, 270)) {
            for (isFront in listOf(false, true)) {
                // Simulate BOTH HAL variants:
                // 1) Camera2 HAL bakes sensor orientation (+ front mirror) into SurfaceTexture.getTransformMatrix
                // 2) Camera2 HAL bakes sensor orientation WITHOUT front mirror
                // 3) SurfaceTexture.getTransformMatrix has only GL vertical flip (no HAL rotation)
                val halMatrices = listOf(
                    FrameTransform.multiply(
                        FrameTransform.buildTopDownToBufferMatrix(
                            rotationDegrees = sensorOrientation,
                            mirrorHorizontal = isFront,
                            cropScale = FrameTransform.CropScale(1f, 1f),
                        ),
                        flipY,
                    ),
                    FrameTransform.multiply(
                        FrameTransform.buildTopDownToBufferMatrix(
                            rotationDegrees = sensorOrientation,
                            mirrorHorizontal = false,
                            cropScale = FrameTransform.CropScale(1f, 1f),
                        ),
                        flipY,
                    ),
                    flipY,
                )

                for (stMatrix in halMatrices) {
                    for (displayDegrees in listOf(0, 90, 180, 270)) {
                        val isLandscape = displayDegrees == 90 || displayDegrees == 270
                        val viewportW = if (isLandscape) 1200 else 900
                        val viewportH = if (isLandscape) 900 else 1200

                        val target = FrameTransform.computeCameraOrientation(
                            sensorOrientationDegrees = sensorOrientation,
                            displayRotationDegrees = displayDegrees,
                            isFrontCamera = isFront,
                        )
                        val previewM = FrameTransform.buildUprightToPreviewTexMatrix(
                            surfaceTextureMatrix = stMatrix,
                            bufferWidth = 1280,
                            bufferHeight = 720,
                            rotationDegrees = target.rotationDegrees,
                            mirrorHorizontal = target.mirrorHorizontal,
                            viewportWidth = viewportW,
                            viewportHeight = viewportH,
                        )
                        val photoM = FrameTransform.buildPhotoTexMatrix(
                            sourceWidth = 4000,
                            sourceHeight = 2250,
                            rotationDegrees = target.rotationDegrees,
                            mirrorHorizontal = target.mirrorHorizontal,
                            viewportWidth = viewportW,
                            viewportHeight = viewportH,
                        )

                        for ((u, v) in samplePoints) {
                            assertUvEquals(
                                FrameTransform.mapUv(photoM, u, v),
                                FrameTransform.mapUv(previewM, u, v),
                            )
                        }
                    }
                }
            }
        }
    }

    private fun assertUvEquals(expected: Pair<Float, Float>, actual: Pair<Float, Float>) {
        assertEquals(expected.first, actual.first, eps)
        assertEquals(expected.second, actual.second, eps)
    }
}
