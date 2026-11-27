package com.retrocam.app.effects

import com.retrocam.app.recipe.FinalRecipe
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class PreviewPerformanceTest {

    @Test
    fun renderQualityPreviewLow_usesNegativeUniformAndKeepsExistingTiersIntact() {
        assertEquals(-1, RenderQuality.PREVIEW_LOW.uniformValue)
        assertEquals(0, RenderQuality.PREVIEW.uniformValue)
        assertEquals(1, RenderQuality.VIDEO.uniformValue)
        assertEquals(2, RenderQuality.PHOTO.uniformValue)
    }

    @Test
    fun controller_downgradesPreviewAfterSustainedSlowFramesAndRecoversWithHysteresis() {
        val controller = PreviewPerformanceController(
            windowSize = 10,
            slowFrameThresholdMs = 28f,
            recoveryFrameThresholdMs = 18f,
            slowFramesToDowngrade = 4,
            fastFramesToRecover = 6,
        )

        // 3 slow frames: not downgraded yet
        repeat(3) {
            controller.recordFrame(32f, ThermalLevel.NORMAL, heavyEffectsActive = true)
        }
        assertFalse(controller.snapshot().isDowngraded)
        assertEquals(RenderQuality.PREVIEW, controller.effectiveQuality(RenderQuality.PREVIEW))

        // 4th slow frame triggers auto-downgrade for PREVIEW only
        val downSnap = controller.recordFrame(33f, ThermalLevel.NORMAL, heavyEffectsActive = true)
        assertTrue(downSnap.isDowngraded)
        assertEquals("slow_frame", downSnap.downgradeReason)
        assertEquals(RenderQuality.PREVIEW_LOW, controller.effectiveQuality(RenderQuality.PREVIEW))
        // PHOTO and VIDEO are never downgraded
        assertEquals(RenderQuality.PHOTO, controller.effectiveQuality(RenderQuality.PHOTO))
        assertEquals(RenderQuality.VIDEO, controller.effectiveQuality(RenderQuality.VIDEO))

        // Fill window with fast frames: first few do not immediately oscillate back
        repeat(5) {
            controller.recordFrame(10f, ThermalLevel.NORMAL, heavyEffectsActive = true)
        }
        assertTrue(controller.snapshot().isDowngraded)

        // Sustained fast frames recover cleanly back to PREVIEW
        repeat(15) {
            controller.recordFrame(10f, ThermalLevel.NORMAL, heavyEffectsActive = true)
        }
        assertFalse(controller.snapshot().isDowngraded)
        assertNull(controller.snapshot().downgradeReason)
        assertEquals(RenderQuality.PREVIEW, controller.effectiveQuality(RenderQuality.PREVIEW))
    }

    @Test
    fun controller_downgradesImmediatelyOnModerateOrSevereThermalThrottling() {
        val controller = PreviewPerformanceController(fastFramesToRecover = 3)

        // Even fast frames downgrade immediately when phone is thermally throttled
        val snap = controller.recordFrame(9.5f, ThermalLevel.MODERATE, heavyEffectsActive = true)
        assertTrue(snap.isDowngraded)
        assertEquals("thermal_moderate", snap.downgradeReason)
        assertEquals(RenderQuality.PREVIEW_LOW, controller.effectiveQuality(RenderQuality.PREVIEW))
        assertEquals(RenderQuality.PHOTO, controller.effectiveQuality(RenderQuality.PHOTO))

        // Stays downgraded while thermal remains SEVERE
        val severeSnap = controller.recordFrame(8.0f, ThermalLevel.SEVERE, heavyEffectsActive = true)
        assertTrue(severeSnap.isDowngraded)
        assertEquals("thermal_severe", severeSnap.downgradeReason)

        // Recovers once thermal cools back to LIGHT/NORMAL and frames stay fast
        repeat(3) {
            controller.recordFrame(11.0f, ThermalLevel.LIGHT, heavyEffectsActive = true)
        }
        assertFalse(controller.snapshot().isDowngraded)
    }

    @Test
    fun controller_computesFrameStatsAndDetectsHeavyEffects() {
        val controller = PreviewPerformanceController(windowSize = 5)
        listOf(10f, 12f, 14f, 16f, 20f).forEach { ms ->
            controller.recordFrame(ms, ThermalLevel.NORMAL, heavyEffectsActive = true)
        }
        val snap = controller.snapshot()
        assertEquals(5, snap.sampleCount)
        assertEquals(14.4f, snap.avgFrameMs, 1e-3f)
        assertEquals(20f, snap.maxFrameMs, 1e-3f)
        assertTrue(snap.p95FrameMs >= 16f)
        assertEquals("14.4ms", PreviewPerformanceController.formatCompactBadge(snap))

        assertFalse(PreviewPerformanceController.hasHeavyEffects(FinalRecipe.NEUTRAL))
        assertTrue(PreviewPerformanceController.hasHeavyEffects(FinalRecipe.NEUTRAL.copy(bloomIntensity = 0.2f)))
        assertTrue(PreviewPerformanceController.hasHeavyEffects(FinalRecipe.NEUTRAL.copy(softness = 0.1f)))
        assertTrue(PreviewPerformanceController.hasHeavyEffects(FinalRecipe.NEUTRAL.copy(combinedNoiseAmount = 0.15f)))
    }
}
