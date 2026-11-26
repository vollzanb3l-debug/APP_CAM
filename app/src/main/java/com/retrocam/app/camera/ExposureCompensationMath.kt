package com.retrocam.app.camera

import java.util.Locale
import kotlin.math.abs
import kotlin.math.roundToInt

/**
 * Pure-Kotlin exposure compensation math converting between the -2.0..+2.0 EV UI scale
 * and hardware CameraX ExposureState index clamped to the device's supported range.
 */
object ExposureCompensationMath {

    const val UI_MIN_EV: Float = -2.0f
    const val UI_MAX_EV: Float = 2.0f

    data class ExposureBounds(
        val supported: Boolean,
        val minIndex: Int,
        val maxIndex: Int,
        val stepEv: Float,
        val minEv: Float,
        val maxEv: Float,
    ) {
        companion object {
            val UNSUPPORTED = ExposureBounds(
                supported = false,
                minIndex = 0,
                maxIndex = 0,
                stepEv = 0f,
                minEv = 0f,
                maxEv = 0f,
            )
        }
    }

    fun computeBounds(
        isExposureCompensationSupported: Boolean,
        hardwareMinIndex: Int,
        hardwareMaxIndex: Int,
        stepEv: Float,
    ): ExposureBounds {
        if (!isExposureCompensationSupported || stepEv <= 0f || hardwareMinIndex >= hardwareMaxIndex) {
            return ExposureBounds.UNSUPPORTED
        }
        val hwMinEv = hardwareMinIndex * stepEv
        val hwMaxEv = hardwareMaxIndex * stepEv
        val clampedMinEv = maxOf(UI_MIN_EV, hwMinEv)
        val clampedMaxEv = minOf(UI_MAX_EV, hwMaxEv)
        val minIdx = evToIndex(clampedMinEv, hardwareMinIndex, hardwareMaxIndex, stepEv)
        val maxIdx = evToIndex(clampedMaxEv, hardwareMinIndex, hardwareMaxIndex, stepEv)
        if (minIdx >= maxIdx) {
            return ExposureBounds.UNSUPPORTED
        }
        return ExposureBounds(
            supported = true,
            minIndex = minIdx,
            maxIndex = maxIdx,
            stepEv = stepEv,
            minEv = minIdx * stepEv,
            maxEv = maxIdx * stepEv,
        )
    }

    fun evToIndex(ev: Float, bounds: ExposureBounds): Int {
        if (!bounds.supported || bounds.stepEv <= 0f) return 0
        return evToIndex(ev, bounds.minIndex, bounds.maxIndex, bounds.stepEv)
    }

    fun indexToEv(index: Int, bounds: ExposureBounds): Float {
        if (!bounds.supported || bounds.stepEv <= 0f) return 0f
        return index.coerceIn(bounds.minIndex, bounds.maxIndex) * bounds.stepEv
    }

    fun formatEv(ev: Float): String {
        val normalized = if (abs(ev) < 0.05f) 0f else ev
        return when {
            normalized > 0f -> String.format(Locale.US, "+%.1f EV", normalized)
            normalized < 0f -> String.format(Locale.US, "%.1f EV", normalized)
            else -> "0.0 EV"
        }
    }

    private fun evToIndex(ev: Float, minIndex: Int, maxIndex: Int, stepEv: Float): Int {
        val clampedEv = ev.coerceIn(UI_MIN_EV, UI_MAX_EV)
        return (clampedEv / stepEv).roundToInt().coerceIn(minIndex, maxIndex)
    }
}
