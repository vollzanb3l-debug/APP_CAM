package com.retrocam.app.effects

import com.retrocam.app.camera.FrameTransform
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.File

class VignetteEffectTest {

    private val eps = 1e-5f

    @Test
    fun zeroStrength_isExactIdentityEverywhere() {
        val points = listOf(0.5f to 0.5f, 0f to 0f, 1f to 0f, 0f to 1f, 1f to 1f, 0.2f to 0.8f)
        for ((u, v) in points) {
            assertEquals(1f, OpticsMath.vignetteFactor(u, v, 0.75f, 0f, 0.8f), eps)
            assertEquals(1f, OpticsMath.vignetteFactor(u, v, 1.5f, 0f, 0.5f), eps)
        }
    }

    @Test
    fun centerIsBrightestAndAllFourCornersAreSymmetric() {
        val center = OpticsMath.vignetteFactor(0.5f, 0.5f, 0.75f, 0.5f, 0.8f)
        val tl = OpticsMath.vignetteFactor(0f, 0f, 0.75f, 0.5f, 0.8f)
        val tr = OpticsMath.vignetteFactor(1f, 0f, 0.75f, 0.5f, 0.8f)
        val bl = OpticsMath.vignetteFactor(0f, 1f, 0.75f, 0.5f, 0.8f)
        val br = OpticsMath.vignetteFactor(1f, 1f, 0.75f, 0.5f, 0.8f)

        assertEquals(1f, center, eps)
        assertEquals(tl, tr, eps)
        assertEquals(tl, bl, eps)
        assertEquals(tl, br, eps)
        assertTrue("Corners must be darker than center", tl < 0.75f)
    }

    @Test
    fun strengthAndRadiusBehaveMonotonicallyAndStayResolutionIndependent() {
        // Same normalized coordinates & aspect ratio (3:4): 720x960 preview == 3000x4000 photo
        val previewAspect = 720f / 960f
        val photoAspect = 3000f / 4000f
        val previewCorner = OpticsMath.vignetteFactor(0f, 0f, previewAspect, 0.4f, 0.8f)
        val photoCorner = OpticsMath.vignetteFactor(0f, 0f, photoAspect, 0.4f, 0.8f)
        assertEquals(previewCorner, photoCorner, eps)

        val weak = OpticsMath.vignetteFactor(0.1f, 0.1f, 0.75f, 0.2f, 0.8f)
        val strong = OpticsMath.vignetteFactor(0.1f, 0.1f, 0.75f, 0.8f, 0.8f)
        assertTrue("Higher strength must darken more", strong < weak)

        val tight = OpticsMath.vignetteFactor(0.25f, 0.25f, 0.75f, 0.6f, 0.5f)
        val wide = OpticsMath.vignetteFactor(0.25f, 0.25f, 0.75f, 0.6f, 1.2f)
        assertTrue("Larger radius must keep mid-frame brighter", wide > tight)
    }

    @Test
    fun uprightToPreviewMatrix_matchesPhotoMatrixAtAllFourImageCorners() {
        val standardSt = FrameTransform.verticalFlipMatrix()
        for (rot in listOf(0, 90, 180, 270)) {
            for (mirror in listOf(false, true)) {
                val previewM = FrameTransform.buildUprightToPreviewTexMatrix(
                    surfaceTextureMatrix = standardSt,
                    bufferWidth = 1280,
                    bufferHeight = 720,
                    rotationDegrees = rot,
                    mirrorHorizontal = mirror,
                    viewportWidth = 900,
                    viewportHeight = 1200,
                )
                val photoM = FrameTransform.buildPhotoTexMatrix(
                    sourceWidth = 4000,
                    sourceHeight = 2250,
                    rotationDegrees = rot,
                    mirrorHorizontal = mirror,
                    viewportWidth = 900,
                    viewportHeight = 1200,
                )
                for ((u, v) in listOf(0f to 0f, 1f to 0f, 0f to 1f, 1f to 1f)) {
                    val p = FrameTransform.mapUv(previewM, u, v)
                    val f = FrameTransform.mapUv(photoM, u, v)
                    assertEquals(f.first, p.first, eps)
                    assertEquals(f.second, p.second, eps)
                }
            }
        }
    }

    @Test
    fun shaderDeclaresVignetteUniformsAndImageUv() {
        val frag = resolveAssetFile("shaders/retro_pipeline.frag").readText()
        val vert = resolveAssetFile("shaders/retro_pipeline.vert").readText()
        assertTrue(vert.contains("uImageUvRect"))
        assertTrue(vert.contains("vImageUv"))
        assertTrue(frag.contains("uImageAspect"))
        assertTrue(frag.contains("uVignetteStrength"))
        assertTrue(frag.contains("uVignetteRadius"))
        assertTrue(frag.contains("computeVignetteFactor"))
    }

    private fun resolveAssetFile(relative: String): File {
        val candidates = listOf(
            File("src/main/assets/$relative"),
            File("app/src/main/assets/$relative"),
        )
        return candidates.firstOrNull { it.exists() }
            ?: error("Could not find asset $relative from ${File(".").absolutePath}")
    }
}
