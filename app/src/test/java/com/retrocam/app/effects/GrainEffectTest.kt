package com.retrocam.app.effects

import com.retrocam.app.recipe.RgbColor
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.File
import kotlin.math.abs

class GrainEffectTest {

    private val eps = 1e-5f

    @Test
    fun zeroAmount_isExactIdentity() {
        val src = RgbColor(0.45f, 0.55f, 0.65f)
        val out = GrainMath.applyGrain(
            pixel = src,
            u = 0.3f,
            v = 0.7f,
            imageAspect = 0.75f,
            amount = 0f,
            chroma = 0.5f,
            grainSize = 0.002f,
            roughness = 0.5f,
            frameParams = FrameRenderParams.forPhoto(),
        )
        assertEquals(src, out)
    }

    @Test
    fun photoSeedIsDeterministicAndResolutionIndependent() {
        val midGray = RgbColor(0.5f, 0.5f, 0.5f)
        val photo1 = GrainMath.applyGrain(
            pixel = midGray,
            u = 0.41f,
            v = 0.63f,
            imageAspect = 720f / 960f,
            amount = 0.35f,
            chroma = 0f,
            grainSize = 0.002f,
            roughness = 0.6f,
            frameParams = FrameRenderParams.forPhoto(),
        )
        val photo2 = GrainMath.applyGrain(
            pixel = midGray,
            u = 0.41f,
            v = 0.63f,
            imageAspect = 3000f / 4000f,
            amount = 0.35f,
            chroma = 0f,
            grainSize = 0.002f,
            roughness = 0.6f,
            frameParams = FrameRenderParams.forPhoto(),
        )
        assertEquals(photo1.r, photo2.r, eps)
        assertEquals(photo1.g, photo2.g, eps)
        assertEquals(photo1.b, photo2.b, eps)
        // Monochrome grain keeps R == G == B on neutral gray
        assertEquals(photo1.r, photo1.g, eps)
        assertEquals(photo1.g, photo1.b, eps)
    }

    @Test
    fun videoSeedChangesPatternAcrossFramesAndChromaSeparatesChannels() {
        val midGray = RgbColor(0.5f, 0.5f, 0.5f)
        val f1 = FrameRenderParams.forStreamFrame(frameIndex = 1, timeSec = 0.033f, quality = RenderQuality.VIDEO)
        val f2 = FrameRenderParams.forStreamFrame(frameIndex = 2, timeSec = 0.066f, quality = RenderQuality.VIDEO)

        var differingSamples = 0
        for (i in 1..12) {
            val u = i * 0.07f
            val v = i * 0.06f
            val p1 = GrainMath.applyGrain(midGray, u, v, 0.75f, 0.4f, 0f, 0.002f, 0.5f, f1)
            val p2 = GrainMath.applyGrain(midGray, u, v, 0.75f, 0.4f, 0f, 0.002f, 0.5f, f2)
            if (abs(p1.r - p2.r) > 1e-4f) differingSamples++
        }
        assertTrue("Video grain must vary across frames", differingSamples >= 8)

        var chromaSpreadFound = false
        for (i in 1..12) {
            val p = GrainMath.applyGrain(midGray, i * 0.073f, i * 0.059f, 0.75f, 0.5f, 0.9f, 0.002f, 0.5f, f1)
            if (abs(p.r - p.g) > 1e-3f || abs(p.g - p.b) > 1e-3f) {
                chromaSpreadFound = true
                break
            }
        }
        assertTrue("Non-zero chroma noise must vary across RGB channels", chromaSpreadFound)
    }

    @Test
    fun photoAndPreviewQualityTiersStayBoundedAndDistinctAtHighRoughness() {
        val midGray = RgbColor(0.5f, 0.5f, 0.5f)
        val preview = FrameRenderParams.forStreamFrame(
            frameIndex = 0,
            timeSec = 0f,
            quality = RenderQuality.PREVIEW,
            fixedSeedOverride = FrameRenderParams.DEFAULT_PHOTO_GRAIN_SEED,
        )
        val photo = FrameRenderParams.forPhoto()

        var anyPhotoFineDiff = false
        for (i in 1..16) {
            val u = i * 0.055f
            val v = i * 0.047f
            val pPrev = GrainMath.applyGrain(midGray, u, v, 0.75f, 0.4f, 0f, 0.002f, 0.8f, preview)
            val pPhoto = GrainMath.applyGrain(midGray, u, v, 0.75f, 0.4f, 0f, 0.002f, 0.8f, photo)
            assertTrue(pPrev.r in 0f..1f && pPhoto.r in 0f..1f)
            if (abs(pPrev.r - pPhoto.r) > 1e-4f) anyPhotoFineDiff = true
        }
        assertTrue("Photo tier adds fine second octave when roughness > 0", anyPhotoFineDiff)
        assertNotEquals(RenderQuality.PREVIEW, RenderQuality.PHOTO)
    }

    @Test
    fun shaderDeclaresGrainUniformsAndStage() {
        val frag = resolveAssetFile("shaders/retro_pipeline.frag").readText()
        for (token in listOf("uGrainAmount", "uGrainChroma", "uGrainSize", "uGrainRoughness", "applyFilmGrain")) {
            assertTrue("Missing $token in retro_pipeline.frag", frag.contains(token))
        }
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
