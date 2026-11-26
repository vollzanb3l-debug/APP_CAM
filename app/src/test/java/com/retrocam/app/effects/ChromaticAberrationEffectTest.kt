package com.retrocam.app.effects

import com.retrocam.app.recipe.RgbColor
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.File

class ChromaticAberrationEffectTest {

    private val eps = 1e-5f

    @Test
    fun zeroAmountAndCenterPixel_haveZeroShift() {
        val (dx0, dy0) = OpticsMath.chromaticAberrationOffset(0.1f, 0.9f, 0.75f, 0f)
        assertEquals(0f, dx0, eps)
        assertEquals(0f, dy0, eps)

        val (cx, cy) = OpticsMath.chromaticAberrationOffset(0.5f, 0.5f, 0.75f, 1.0f)
        assertEquals(0f, cx, eps)
        assertEquals(0f, cy, eps)
    }

    @Test
    fun cornersShiftRadiallySymmetricallyAndStayResolutionIndependent() {
        val previewOffset = OpticsMath.chromaticAberrationOffset(0.95f, 0.95f, 720f / 960f, 0.5f)
        val photoOffset = OpticsMath.chromaticAberrationOffset(0.95f, 0.95f, 3000f / 4000f, 0.5f)
        assertEquals(previewOffset.first, photoOffset.first, eps)
        assertEquals(previewOffset.second, photoOffset.second, eps)

        val oppOffset = OpticsMath.chromaticAberrationOffset(0.05f, 0.05f, 0.75f, 0.5f)
        assertEquals(-previewOffset.first, oppOffset.first, eps)
        assertEquals(-previewOffset.second, oppOffset.second, eps)
        assertTrue("Bottom-right corner offset must point outward (+x, +y)", previewOffset.first > 0f && previewOffset.second > 0f)

        val weak = OpticsMath.chromaticAberrationOffset(0.95f, 0.95f, 0.75f, 0.2f)
        val strong = OpticsMath.chromaticAberrationOffset(0.95f, 0.95f, 0.75f, 0.8f)
        assertTrue(strong.first > weak.first)
    }

    @Test
    fun lateralDispersion_separatesRedAndBlueWhileAnchoringGreen() {
        // Horizontal gradient: R=G=B=u so shifting +dx increases red and -dx decreases blue while green stays at u=0.9
        val out = OpticsMath.applyChromaticAberration(
            baseColor = RgbColor(0.9f, 0.9f, 0.9f),
            u = 0.9f,
            v = 0.5f,
            imageAspect = 0.75f,
            amount = 0.8f,
        ) { su, _ ->
            val clamped = su.coerceIn(0f, 1f)
            RgbColor(clamped, clamped, clamped)
        }
        assertEquals(0.9f, out.g, eps)
        assertTrue("Red must sample further outward than green", out.r > out.g)
        assertTrue("Blue must sample further inward than green", out.b < out.g)
    }

    @Test
    fun shaderDeclaresChromaticAberrationInOpticsOrder() {
        val frag = resolveAssetFile("shaders/retro_pipeline.frag").readText()
        assertTrue(frag.contains("uChromaticAberration"))
        assertTrue(frag.contains("applyChromaticAberration"))
        val mainBody = frag.substringAfter("void main()")
        val softIdx = mainBody.indexOf("sampleWithSoftness")
        val caIdx = mainBody.indexOf("applyChromaticAberration")
        val bloomIdx = mainBody.indexOf("applyBloomAndHalation")
        val vigIdx = mainBody.indexOf("computeVignetteFactor")
        assertTrue(softIdx in 0 until caIdx)
        assertTrue(caIdx < bloomIdx)
        assertTrue(bloomIdx < vigIdx)
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
