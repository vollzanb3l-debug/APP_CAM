package com.retrocam.app.effects

import com.retrocam.app.recipe.FinalRecipe
import java.util.Locale

enum class ThermalLevel {
    NORMAL,
    LIGHT,
    MODERATE,
    SEVERE,
}

data class PreviewPerfSnapshot(
    val lastFrameMs: Float = 0f,
    val avgFrameMs: Float = 0f,
    val p95FrameMs: Float = 0f,
    val maxFrameMs: Float = 0f,
    val sampleCount: Int = 0,
    val thermalLevel: ThermalLevel = ThermalLevel.NORMAL,
    val isDowngraded: Boolean = false,
    val downgradeReason: String? = null,
    val heavyEffectsActive: Boolean = false,
)

/**
 * Pure-Kotlin preview frame-time profiler and adaptive quality controller.
 *
 * Automatically downgrades ONLY [RenderQuality.PREVIEW] -> [RenderQuality.PREVIEW_LOW]
 * when preview frames are slow (> 28 ms sustained) or the device reports moderate/severe
 * thermal throttling, with hysteresis to avoid visual flicker.
 * [RenderQuality.PHOTO] and [RenderQuality.VIDEO] are never downgraded.
 */
class PreviewPerformanceController(
    private val windowSize: Int = DEFAULT_WINDOW_SIZE,
    private val slowFrameThresholdMs: Float = SLOW_FRAME_THRESHOLD_MS,
    private val recoveryFrameThresholdMs: Float = RECOVERY_FRAME_THRESHOLD_MS,
    private val slowFramesToDowngrade: Int = SLOW_FRAMES_TO_DOWNGRADE,
    private val fastFramesToRecover: Int = FAST_FRAMES_TO_RECOVER,
) {
    private val samples = FloatArray(windowSize.coerceAtLeast(4))
    private var writeIdx = 0
    private var filledCount = 0
    private var totalCount = 0

    private var slowStreak = 0
    private var fastStreak = 0
    private var downgraded = false
    private var reason: String? = null
    private var lastSnapshot = PreviewPerfSnapshot()

    fun recordFrame(
        frameDurationMs: Float,
        thermalLevel: ThermalLevel,
        heavyEffectsActive: Boolean,
    ): PreviewPerfSnapshot {
        val safeMs = if (frameDurationMs.isFinite() && frameDurationMs >= 0f) frameDurationMs else 0f
        samples[writeIdx] = safeMs
        writeIdx = (writeIdx + 1) % samples.size
        if (filledCount < samples.size) filledCount += 1
        totalCount += 1

        var sum = 0f
        var maxVal = 0f
        val sorted = FloatArray(filledCount)
        for (i in 0 until filledCount) {
            val v = samples[i]
            sum += v
            if (v > maxVal) maxVal = v
            sorted[i] = v
        }
        sorted.sort()
        val avg = sum / filledCount
        val p95Index = (((filledCount - 1) * 0.95f).toInt()).coerceIn(0, filledCount - 1)
        val p95 = sorted[p95Index]

        val thermallyThrottled = thermalLevel == ThermalLevel.MODERATE || thermalLevel == ThermalLevel.SEVERE
        if (thermallyThrottled) {
            downgraded = true
            reason = "thermal_${thermalLevel.name.lowercase(Locale.US)}"
            slowStreak = 0
            fastStreak = 0
        } else {
            val isSlow = safeMs > slowFrameThresholdMs ||
                (filledCount >= slowFramesToDowngrade && avg > slowFrameThresholdMs)
            if (isSlow) {
                slowStreak += 1
                fastStreak = 0
                if (!downgraded && slowStreak >= slowFramesToDowngrade) {
                    downgraded = true
                    reason = "slow_frame"
                }
            } else {
                slowStreak = 0
                if (downgraded) {
                    if (safeMs < recoveryFrameThresholdMs && avg < recoveryFrameThresholdMs) {
                        fastStreak += 1
                        if (fastStreak >= fastFramesToRecover) {
                            downgraded = false
                            reason = null
                            fastStreak = 0
                        }
                    } else {
                        fastStreak = 0
                    }
                }
            }
        }

        lastSnapshot = PreviewPerfSnapshot(
            lastFrameMs = safeMs,
            avgFrameMs = avg,
            p95FrameMs = p95,
            maxFrameMs = maxVal,
            sampleCount = totalCount,
            thermalLevel = thermalLevel,
            isDowngraded = downgraded,
            downgradeReason = reason,
            heavyEffectsActive = heavyEffectsActive,
        )
        return lastSnapshot
    }

    fun snapshot(): PreviewPerfSnapshot = lastSnapshot

    fun effectiveQuality(requested: RenderQuality): RenderQuality {
        if (requested == RenderQuality.PREVIEW && downgraded) {
            return RenderQuality.PREVIEW_LOW
        }
        return requested
    }

    fun reset() {
        writeIdx = 0
        filledCount = 0
        totalCount = 0
        slowStreak = 0
        fastStreak = 0
        downgraded = false
        reason = null
        lastSnapshot = PreviewPerfSnapshot()
    }

    companion object {
        const val DEFAULT_WINDOW_SIZE = 30
        const val SLOW_FRAME_THRESHOLD_MS = 28.0f
        const val RECOVERY_FRAME_THRESHOLD_MS = 18.0f
        const val SLOW_FRAMES_TO_DOWNGRADE = 8
        const val FAST_FRAMES_TO_RECOVER = 45

        fun hasHeavyEffects(recipe: FinalRecipe): Boolean {
            return recipe.bloomIntensity > 0.001f ||
                recipe.halationIntensity > 0.001f ||
                recipe.softness > 0.001f ||
                recipe.combinedNoiseAmount > 0.001f
        }

        fun formatCompactBadge(snapshot: PreviewPerfSnapshot): String? {
            if (snapshot.sampleCount <= 0) return null
            val ms = String.format(Locale.US, "%.1fms", snapshot.avgFrameMs)
            return if (snapshot.isDowngraded) "$ms · ECO" else ms
        }

        fun formatMetricsSummary(snapshot: PreviewPerfSnapshot): String {
            val mode = if (snapshot.isDowngraded) {
                "PREVIEW_LOW(${snapshot.downgradeReason ?: "auto"})"
            } else {
                "PREVIEW"
            }
            return String.format(
                Locale.US,
                "mode=%s avg=%.2fms p95=%.2fms max=%.2fms thermal=%s heavy=%s n=%d",
                mode,
                snapshot.avgFrameMs,
                snapshot.p95FrameMs,
                snapshot.maxFrameMs,
                snapshot.thermalLevel.name,
                snapshot.heavyEffectsActive,
                snapshot.sampleCount,
            )
        }
    }
}
