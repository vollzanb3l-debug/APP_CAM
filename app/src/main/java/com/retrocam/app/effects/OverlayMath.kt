package com.retrocam.app.effects

import com.retrocam.app.recipe.RgbColor
import kotlin.math.abs
import kotlin.math.exp
import kotlin.math.floor
import kotlin.math.hypot
import kotlin.math.sin

/**
 * Pure-Kotlin reference implementations for Stage 5 overlays (original procedural
 * digital date stamp and optional light leak) matching retro_pipeline.frag.
 */
object OverlayMath {

    // 7-segment bitmasks: bit0=top, bit1=topR, bit2=botR, bit3=bot, bit4=botL, bit5=topL, bit6=mid
    val SEVEN_SEG_MASKS: IntArray = intArrayOf(63, 6, 91, 79, 102, 109, 125, 7, 127, 111)

    // Original RetroCam compact layout: 'YY.MM.DD (9 slots: apostrophe, 2 digits, dot, 2 digits, dot, 2 digits)
    private const val SLOT_COUNT = 9
    private const val DIGIT_HEIGHT_SHORT = 0.036f
    private const val MARGIN_SHORT = 0.045f
    private const val SLOT_WIDTH_RATIO = 0.62f

    fun encodeDateYmd(year: Int, month: Int, day: Int): Int {
        val yy = ((year % 100) + 100) % 100
        val mm = month.coerceIn(1, 12)
        val dd = day.coerceIn(1, 31)
        return yy * 10000 + mm * 100 + dd
    }

    fun unpackDateDigits(ymd: Int): IntArray {
        val safe = ymd.coerceIn(0, 999999)
        return intArrayOf(
            (safe / 100000) % 10,
            (safe / 10000) % 10,
            (safe / 1000) % 10,
            (safe / 100) % 10,
            (safe / 10) % 10,
            safe % 10,
        )
    }

    /**
     * Resolution-independent optional film-edge light leak overlay matching
     * retro_pipeline.frag's applyLightLeak.
     */
    fun applyLightLeak(
        baseColor: RgbColor,
        u: Float,
        v: Float,
        intensity: Float,
        seed: Float,
    ): RgbColor {
        val strength = intensity.coerceIn(0f, 1f)
        if (strength <= 0.0001f) return baseColor

        val s = fract(abs(seed))
        val edgeU = if (s < 0.5f) s * 0.40f else 1f - (s - 0.5f) * 0.40f
        val centerV = 0.25f + fract(s * 2.37f) * 0.55f
        val wave = sin(((v - centerV) * 5f + s * 6.2831853f).toDouble()).toFloat() * 0.035f

        val dx = (u - edgeU) + wave
        val dy = (v - centerV) * 0.55f
        val primaryBand = exp((-(dx * dx * 18f + dy * dy * 4.5f)).toDouble()).toFloat()

        val dx2 = u - edgeU
        val dy2 = v - (1f - centerV)
        val secondaryFlare = exp((-(dx2 * dx2 * 6f + dy2 * dy2 * 8f)).toDouble()).toFloat() * 0.45f

        val mask = (primaryBand + secondaryFlare).coerceIn(0f, 1f)
        val t = primaryBand.coerceIn(0f, 1f)
        val leakColorR = 0.88f + (1.00f - 0.88f) * t
        val leakColorG = 0.18f + (0.46f - 0.18f) * t
        val leakColorB = 0.12f + (0.14f - 0.12f) * t

        val scale = mask * strength * 0.85f
        val outR = (1f - (1f - baseColor.r) * (1f - leakColorR * scale)).coerceIn(0f, 1f)
        val outG = (1f - (1f - baseColor.g) * (1f - leakColorG * scale)).coerceIn(0f, 1f)
        val outB = (1f - (1f - baseColor.b) * (1f - leakColorB * scale)).coerceIn(0f, 1f)
        return RgbColor(outR, outG, outB)
    }

    private fun fract(x: Float): Float = x - floor(x)

    fun applyDateStamp(
        baseColor: RgbColor,
        u: Float,
        v: Float,
        imageAspect: Float,
        enabled: Boolean,
        intensity: Float,
        stampColor: RgbColor,
        dateYmd: Int,
    ): RgbColor {
        val strength = if (enabled) intensity.coerceIn(0f, 1f) else 0f
        if (strength <= 0.0001f) return baseColor

        val (core, glow) = evaluateDateStampCoverage(u, v, imageAspect, dateYmd)
        if (core <= 0.0001f && glow <= 0.0001f) return baseColor

        // Subtle warm-dark micro-backing under phosphor glow for legibility on bright scenes
        val darkenedR = baseColor.r * (1f - glow * 0.25f * strength)
        val darkenedG = baseColor.g * (1f - glow * 0.25f * strength)
        val darkenedB = baseColor.b * (1f - glow * 0.25f * strength)

        val coreBoostR = (stampColor.r * 1.15f).coerceAtMost(1f)
        val coreBoostG = (stampColor.g * 1.12f).coerceAtMost(1f)
        val coreBoostB = (stampColor.b * 1.05f).coerceAtMost(1f)

        val outR = (darkenedR + stampColor.r * glow * 0.55f * strength)
            .let { it + (coreBoostR - it) * (core * strength) }
            .coerceIn(0f, 1f)
        val outG = (darkenedG + stampColor.g * glow * 0.55f * strength)
            .let { it + (coreBoostG - it) * (core * strength) }
            .coerceIn(0f, 1f)
        val outB = (darkenedB + stampColor.b * glow * 0.55f * strength)
            .let { it + (coreBoostB - it) * (core * strength) }
            .coerceIn(0f, 1f)
        return RgbColor(outR, outG, outB)
    }

    fun evaluateDateStampCoverage(
        u: Float,
        v: Float,
        imageAspect: Float,
        dateYmd: Int,
    ): Pair<Float, Float> {
        val aspect = imageAspect.coerceAtLeast(0.01f)
        val widthShort = if (aspect >= 1f) aspect else 1f
        val heightShort = if (aspect >= 1f) 1f else 1f / aspect

        val posX = u * widthShort
        val posY = v * heightShort

        val boxH = DIGIT_HEIGHT_SHORT
        val slotW = boxH * SLOT_WIDTH_RATIO
        val boxW = slotW * SLOT_COUNT

        val originX = widthShort - MARGIN_SHORT - boxW
        val originY = heightShort - MARGIN_SHORT - boxH

        val lx = posX - originX
        val ly = posY - originY
        val pad = boxH * 0.35f
        if (lx < -pad || lx > boxW + pad || ly < -pad || ly > boxH + pad) {
            return 0f to 0f
        }

        val slotFloat = lx / slotW
        val slotIndex = slotFloat.toInt().coerceIn(0, SLOT_COUNT - 1)
        val cellX = (lx - slotIndex * slotW) / slotW
        val cellY = ly / boxH

        val digits = unpackDateDigits(dateYmd)
        val dist = when (slotIndex) {
            0 -> sdSegment(cellX, cellY, 0.62f, 0.12f, 0.52f, 0.34f) // apostrophe '
            1 -> sdSevenSegDigit(cellX, cellY, digits[0])
            2 -> sdSevenSegDigit(cellX, cellY, digits[1])
            3 -> hypot(cellX - 0.50f, cellY - 0.86f) - 0.07f // dot .
            4 -> sdSevenSegDigit(cellX, cellY, digits[2])
            5 -> sdSevenSegDigit(cellX, cellY, digits[3])
            6 -> hypot(cellX - 0.50f, cellY - 0.86f) - 0.07f // dot .
            7 -> sdSevenSegDigit(cellX, cellY, digits[4])
            else -> sdSevenSegDigit(cellX, cellY, digits[5])
        }

        val thickness = 0.068f
        val core = 1f - OpticsMath.smoothstep(thickness * 0.65f, thickness * 1.25f, dist)
        val glow = exp((-dist.coerceAtLeast(0f)) * 7.5f) * 0.85f
        return core.coerceIn(0f, 1f) to glow.coerceIn(0f, 1f)
    }

    private fun sdSevenSegDigit(px: Float, py: Float, digit: Int): Float {
        val mask = SEVEN_SEG_MASKS[digit.coerceIn(0, 9)]
        var d = 1e5f
        if ((mask and 1) != 0) d = minOf(d, sdSegment(px, py, 0.24f, 0.12f, 0.76f, 0.12f))
        if ((mask and 2) != 0) d = minOf(d, sdSegment(px, py, 0.78f, 0.16f, 0.78f, 0.46f))
        if ((mask and 4) != 0) d = minOf(d, sdSegment(px, py, 0.78f, 0.54f, 0.78f, 0.84f))
        if ((mask and 8) != 0) d = minOf(d, sdSegment(px, py, 0.24f, 0.88f, 0.76f, 0.88f))
        if ((mask and 16) != 0) d = minOf(d, sdSegment(px, py, 0.22f, 0.54f, 0.22f, 0.84f))
        if ((mask and 32) != 0) d = minOf(d, sdSegment(px, py, 0.22f, 0.16f, 0.22f, 0.46f))
        if ((mask and 64) != 0) d = minOf(d, sdSegment(px, py, 0.25f, 0.50f, 0.75f, 0.50f))
        return d
    }

    private fun sdSegment(
        px: Float,
        py: Float,
        ax: Float,
        ay: Float,
        bx: Float,
        by: Float,
    ): Float {
        val pax = px - ax
        val pay = py - ay
        val bax = bx - ax
        val bay = by - ay
        val denom = (bax * bax + bay * bay).coerceAtLeast(1e-6f)
        val h = ((pax * bax + pay * bay) / denom).coerceIn(0f, 1f)
        return hypot(pax - bax * h, pay - bay * h)
    }
}
