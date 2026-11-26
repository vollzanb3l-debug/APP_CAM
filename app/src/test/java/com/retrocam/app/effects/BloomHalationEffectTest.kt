package com.retrocam.app.effects

import com.retrocam.app.recipe.HexColorParser
import com.retrocam.app.recipe.RgbColor
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.File

class BloomHalationEffectTest {

    private val eps = 1e-5f
    private val warmHalation = HexColorParser.parseOrDefault("#FF6A3D")

    @Test
    fun zeroIntensity_isExactIdentity() {
        val base = RgbColor(0.3f, 0.4f, 0.5f)
        val out = OpticsMath.applyBloomAndHalation(
            baseColor = base,
            u = 0.5f,
            v = 0.5f,
            imageAspect = 0.75f,
            bloomIntensity = 0f,
            bloomThreshold = 0.75f,
            bloomRadius = 0.01f,
            halationIntensity = 0f,
            halationColor = warmHalation,
            quality = RenderQuality.PHOTO,
        ) { _, _ -> RgbColor(1f, 1f, 1f) }
        assertEquals(base, out)
    }

    @Test
    fun darkSceneBelowThreshold_producesNoGlow() {
        val dark = RgbColor(0.25f, 0.25f, 0.25f)
        val out = OpticsMath.applyBloomAndHalation(
            baseColor = dark,
            u = 0.5f,
            v = 0.5f,
            imageAspect = 0.75f,
            bloomIntensity = 0.8f,
            bloomThreshold = 0.75f,
            bloomRadius = 0.01f,
            halationIntensity = 0.8f,
            halationColor = warmHalation,
            quality = RenderQuality.PHOTO,
        ) { _, _ -> dark }
        assertEquals(dark.r, out.r, eps)
        assertEquals(dark.g, out.g, eps)
        assertEquals(dark.b, out.b, eps)
    }

    @Test
    fun brightHighlight_producesBoundedBloomAndWarmHalationAcrossQualityTiers() {
        val base = RgbColor(0.2f, 0.2f, 0.2f)
        val brightField: (Float, Float) -> RgbColor = { _, _ -> RgbColor(1f, 1f, 1f) }

        val bloomPreview = OpticsMath.applyBloomAndHalation(
            base, 0.5f, 0.5f, 720f / 960f,
            bloomIntensity = 0.5f, bloomThreshold = 0.75f, bloomRadius = 0.01f,
            halationIntensity = 0f, halationColor = warmHalation,
            quality = RenderQuality.PREVIEW, sampler = brightField,
        )
        val bloomPhoto = OpticsMath.applyBloomAndHalation(
            base, 0.5f, 0.5f, 3000f / 4000f,
            bloomIntensity = 0.5f, bloomThreshold = 0.75f, bloomRadius = 0.01f,
            halationIntensity = 0f, halationColor = warmHalation,
            quality = RenderQuality.PHOTO, sampler = brightField,
        )
        // Uniform bright surround produces identical bloom magnitude in preview & photo tiers
        assertTrue("Bloom must brighten adjacent pixel", bloomPhoto.r > base.r + 0.05f)
        assertEquals(bloomPreview.r, bloomPhoto.r, eps)
        assertEquals(bloomPreview.g, bloomPhoto.g, eps)
        assertEquals(bloomPreview.b, bloomPhoto.b, eps)

        val halationOnly = OpticsMath.applyBloomAndHalation(
            base, 0.5f, 0.5f, 0.75f,
            bloomIntensity = 0f, bloomThreshold = 0.75f, bloomRadius = 0.01f,
            halationIntensity = 0.6f, halationColor = warmHalation,
            quality = RenderQuality.PHOTO, sampler = brightField,
        )
        assertTrue("Warm red-orange halation must boost red more than blue", halationOnly.r > halationOnly.b + 0.05f)
        assertTrue(halationOnly.r in 0f..1f && halationOnly.g in 0f..1f && halationOnly.b in 0f..1f)
    }

    @Test
    fun shaderDeclaresBloomHalationUniformsInOpticsPipelineOrder() {
        val frag = resolveAssetFile("shaders/retro_pipeline.frag").readText()
        for (u in listOf("uBloomIntensity", "uBloomThreshold", "uBloomRadius", "uHalationIntensity", "uHalationColor")) {
            assertTrue("Missing $u", frag.contains(u))
        }
        val mainBody = frag.substringAfter("void main()")
        val softIdx = mainBody.indexOf("sampleWithSoftness")
        val bloomIdx = mainBody.indexOf("applyBloomAndHalation")
        val vigIdx = mainBody.indexOf("computeVignetteFactor")
        assertTrue(softIdx in 0 until bloomIdx)
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
