package com.retrocam.app.effects

import com.retrocam.app.recipe.RgbColor
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.File

class LightLeakEffectTest {

    private val eps = 1e-5f

    @Test
    fun zeroIntensity_isExactIdentityEverywhere() {
        val src = RgbColor(0.25f, 0.30f, 0.35f)
        for ((u, v) in listOf(0f to 0.5f, 0.5f to 0.5f, 1f to 0.5f)) {
            val out = OverlayMath.applyLightLeak(src, u, v, intensity = 0f, seed = 0.25f)
            assertEquals(src, out)
        }
    }

    @Test
    fun nonZeroIntensity_warmsEdgeMonotonicallyAndShiftsWithSeed() {
        val dark = RgbColor(0.12f, 0.12f, 0.12f)

        // seed = 0.15 places leak along left edge; seed = 0.85 places leak along right edge
        val leftLeakOnLeft = OverlayMath.applyLightLeak(dark, 0.05f, 0.55f, intensity = 0.7f, seed = 0.15f)
        val leftLeakOnRight = OverlayMath.applyLightLeak(dark, 0.95f, 0.55f, intensity = 0.7f, seed = 0.15f)
        assertTrue("Left-edge seed must warm left edge strongly", leftLeakOnLeft.r > dark.r + 0.20f)
        assertTrue("Warm light leak must boost red more than blue", leftLeakOnLeft.r > leftLeakOnLeft.b + 0.15f)
        assertTrue("Left-edge seed must affect left edge more than far right", leftLeakOnLeft.r > leftLeakOnRight.r + 0.15f)

        val rightLeakOnRight = OverlayMath.applyLightLeak(dark, 0.95f, 0.55f, intensity = 0.7f, seed = 0.85f)
        val rightLeakOnLeft = OverlayMath.applyLightLeak(dark, 0.05f, 0.55f, intensity = 0.7f, seed = 0.85f)
        assertTrue("Right-edge seed must affect right edge more than left", rightLeakOnRight.r > rightLeakOnLeft.r + 0.15f)

        // Monotonic with intensity and strictly bounded [0, 1]
        val mild = OverlayMath.applyLightLeak(dark, 0.05f, 0.55f, intensity = 0.25f, seed = 0.15f)
        val strong = OverlayMath.applyLightLeak(dark, 0.05f, 0.55f, intensity = 0.90f, seed = 0.15f)
        assertTrue(strong.r > mild.r)
        assertTrue(strong.r in 0f..1f && strong.g in 0f..1f && strong.b in 0f..1f)
    }

    @Test
    fun shaderDeclaresLightLeakInOverlayStageAfterFilmGrain() {
        val frag = resolveAssetFile("shaders/retro_pipeline.frag").readText()
        for (u in listOf("uLightLeakIntensity", "uLightLeakSeed", "applyLightLeak")) {
            assertTrue("Missing $u in retro_pipeline.frag", frag.contains(u))
        }
        val mainBody = frag.substringAfter("void main()")
        val grainIdx = mainBody.indexOf("applyFilmGrain")
        val leakIdx = mainBody.indexOf("applyLightLeak")
        val dateIdx = mainBody.indexOf("applyDateStamp")
        assertTrue(grainIdx in 0 until leakIdx)
        assertTrue(leakIdx < dateIdx)
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
