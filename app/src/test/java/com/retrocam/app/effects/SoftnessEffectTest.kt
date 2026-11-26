package com.retrocam.app.effects

import com.retrocam.app.recipe.RgbColor
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.File
import kotlin.math.abs

class SoftnessEffectTest {

    private val eps = 1e-5f

    @Test
    fun zeroSoftness_isExactCenterSample() {
        val out = OpticsMath.applySoftness(
            u = 0.5f,
            v = 0.5f,
            imageAspect = 0.75f,
            softness = 0f,
            quality = RenderQuality.PHOTO,
        ) { u, v ->
            if (u == 0.5f && v == 0.5f) RgbColor(1f, 0.2f, 0.1f) else RgbColor(0f, 0f, 0f)
        }
        assertEquals(1f, out.r, eps)
        assertEquals(0.2f, out.g, eps)
        assertEquals(0.1f, out.b, eps)
    }

    @Test
    fun flatField_isPreservedInBothPreviewAndPhotoTiers() {
        val constant = RgbColor(0.37f, 0.62f, 0.81f)
        for (quality in RenderQuality.values()) {
            val out = OpticsMath.applySoftness(
                u = 0.4f,
                v = 0.6f,
                imageAspect = 1.5f,
                softness = 0.75f,
                quality = quality,
            ) { _, _ -> constant }
            assertEquals(constant.r, out.r, eps)
            assertEquals(constant.g, out.g, eps)
            assertEquals(constant.b, out.b, eps)
        }
    }

    @Test
    fun sharpImpulse_softensMonotonicallyWithSoftnessAndRespectsShortSideAspect() {
        val impulseSampler: (Float, Float) -> RgbColor = { u, v ->
            if (abs(u - 0.5f) < 1e-4f && abs(v - 0.5f) < 1e-4f) {
                RgbColor(1f, 1f, 1f)
            } else {
                RgbColor(0f, 0f, 0f)
            }
        }
        val mild = OpticsMath.applySoftness(0.5f, 0.5f, 0.75f, 0.2f, RenderQuality.PHOTO, impulseSampler)
        val strong = OpticsMath.applySoftness(0.5f, 0.5f, 0.75f, 0.9f, RenderQuality.PHOTO, impulseSampler)
        assertTrue("Stronger softness must attenuate sharp impulse more", strong.r < mild.r)

        // Short-side relative UV step: portrait 3:4 has short side = width, landscape 4:3 has short side = height.
        val (pDx, pDy) = OpticsMath.shortSideStepToUv(0.01f, 0.75f)
        assertEquals(0.01f, pDx, eps)
        assertEquals(0.0075f, pDy, eps)

        val (lDx, lDy) = OpticsMath.shortSideStepToUv(0.01f, 4f / 3f)
        assertEquals(0.0075f, lDx, eps)
        assertEquals(0.01f, lDy, eps)
    }

    @Test
    fun shaderDeclaresSoftnessUniformBeforeVignetteAndGrade() {
        val frag = resolveAssetFile("shaders/retro_pipeline.frag").readText()
        assertTrue(frag.contains("uSoftness"))
        assertTrue(frag.contains("sampleWithSoftness"))
        val mainBody = frag.substringAfter("void main()")
        val softnessIdx = mainBody.indexOf("sampleWithSoftness")
        val vignetteIdx = mainBody.indexOf("computeVignetteFactor")
        val gradeIdx = mainBody.indexOf("applyColorGrade")
        val grainIdx = mainBody.indexOf("applyFilmGrain")
        assertTrue(softnessIdx in 0 until vignetteIdx)
        assertTrue(vignetteIdx < gradeIdx)
        assertTrue(gradeIdx < grainIdx)
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
