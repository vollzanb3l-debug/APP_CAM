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

    private fun assertUvEquals(expected: Pair<Float, Float>, actual: Pair<Float, Float>) {
        assertEquals(expected.first, actual.first, eps)
        assertEquals(expected.second, actual.second, eps)
    }
}
