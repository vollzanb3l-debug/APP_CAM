package com.retrocam.app.effects

import com.retrocam.app.recipe.RgbColor
import kotlin.math.abs
import kotlin.math.hypot

/**
 * Pure-Kotlin reference implementations of normalized optical effects matching
 * retro_pipeline.frag operation-for-operation for deterministic unit testing.
 */
object OpticsMath {

    /**
     * Converts a radius expressed as a fraction of the image's short side into
     * normalized (du, dv) UV offsets for the given image aspect ratio (width / height).
     */
    fun shortSideStepToUv(radiusShortSide: Float, imageAspect: Float): Pair<Float, Float> {
        val aspect = imageAspect.coerceAtLeast(0.01f)
        return if (aspect >= 1f) {
            (radiusShortSide / aspect) to radiusShortSide
        } else {
            radiusShortSide to (radiusShortSide * aspect)
        }
    }

    /**
     * Aspect-corrected radial lens distortion matching retro_pipeline.frag's applyLensDistortion.
     * Negative k1 produces barrel distortion (wide-angle); positive k1 produces pincushion.
     */
    fun applyLensDistortion(
        u: Float,
        v: Float,
        imageAspect: Float,
        distortionK1: Float,
    ): Pair<Float, Float> {
        val k1 = distortionK1.coerceIn(-0.5f, 0.5f)
        if (abs(k1) <= 0.0001f) return u to v
        val aspect = imageAspect.coerceAtLeast(0.01f)
        val scaleX = if (aspect >= 1f) aspect else 1f
        val scaleY = if (aspect >= 1f) 1f else 1f / aspect
        val cx = u - 0.5f
        val cy = v - 0.5f
        val mx = cx * scaleX
        val my = cy * scaleY
        val maxR2 = (0.25f * scaleX * scaleX + 0.25f * scaleY * scaleY).coerceAtLeast(0.0001f)
        val normR2 = (mx * mx + my * my) / maxR2

        val factor = if (k1 < 0f) {
            val k = -k1 * 0.65f
            (1f + k * normR2) / (1f + k)
        } else {
            1f - k1 * 0.45f * normR2
        }
        val outU = (0.5f + cx * factor).coerceIn(0f, 1f)
        val outV = (0.5f + cy * factor).coerceIn(0f, 1f)
        return outU to outV
    }

    /**
     * Resolution-independent softness blur matching retro_pipeline.frag's sampleWithSoftness.
     * Supports PREVIEW/VIDEO (5-tap) and PHOTO (9-tap) quality tiers with unit weight sum.
     */
    fun applySoftness(
        u: Float,
        v: Float,
        imageAspect: Float,
        softness: Float,
        quality: RenderQuality,
        sampler: (Float, Float) -> RgbColor,
    ): RgbColor {
        val center = sampler(u, v)
        if (softness <= 0.0001f) return center
        val s = softness.coerceIn(0f, 1f)
        val (stepX, stepY) = shortSideStepToUv(s * 0.0045f, imageAspect)

        val blurred = if (quality == RenderQuality.PHOTO) {
            val ix = stepX * 0.65f
            val iy = stepY * 0.65f
            val ox = stepX * 1.15f
            val oy = stepY * 1.15f
            val a1 = sampler(u + ix, v)
            val a2 = sampler(u - ix, v)
            val a3 = sampler(u, v + iy)
            val a4 = sampler(u, v - iy)
            val d1 = sampler(u + ox, v + oy)
            val d2 = sampler(u - ox, v + oy)
            val d3 = sampler(u + ox, v - oy)
            val d4 = sampler(u - ox, v - oy)
            RgbColor(
                r = center.r * 0.24f + (a1.r + a2.r + a3.r + a4.r) * 0.12f + (d1.r + d2.r + d3.r + d4.r) * 0.07f,
                g = center.g * 0.24f + (a1.g + a2.g + a3.g + a4.g) * 0.12f + (d1.g + d2.g + d3.g + d4.g) * 0.07f,
                b = center.b * 0.24f + (a1.b + a2.b + a3.b + a4.b) * 0.12f + (d1.b + d2.b + d3.b + d4.b) * 0.07f,
            )
        } else {
            val d1 = sampler(u + stepX, v + stepY)
            val d2 = sampler(u - stepX, v + stepY)
            val d3 = sampler(u + stepX, v - stepY)
            val d4 = sampler(u - stepX, v - stepY)
            RgbColor(
                r = center.r * 0.36f + (d1.r + d2.r + d3.r + d4.r) * 0.16f,
                g = center.g * 0.36f + (d1.g + d2.g + d3.g + d4.g) * 0.16f,
                b = center.b * 0.36f + (d1.b + d2.b + d3.b + d4.b) * 0.16f,
            )
        }

        val mixT = s * 0.78f
        return RgbColor(
            r = center.r + (blurred.r - center.r) * mixT,
            g = center.g + (blurred.g - center.g) * mixT,
            b = center.b + (blurred.b - center.b) * mixT,
        )
    }

    /**
     * Radial lateral chromatic aberration UV shift (short-side normalized),
     * zero at center (0.5, 0.5) and increasing smoothly toward corners.
     */
    fun chromaticAberrationOffset(
        u: Float,
        v: Float,
        imageAspect: Float,
        amount: Float,
    ): Pair<Float, Float> {
        val clamped = amount.coerceIn(0f, 1f)
        if (clamped <= 0.0001f) return 0f to 0f
        val cx = u - 0.5f
        val cy = v - 0.5f
        val r2 = cx * cx + cy * cy
        val maxShift = clamped * 0.008f
        val (scaleX, scaleY) = shortSideStepToUv(1f, imageAspect)
        val radialScale = (0.4f + 1.6f * r2) * maxShift * 2f
        return (cx * radialScale * scaleX) to (cy * radialScale * scaleY)
    }

    fun applyChromaticAberration(
        baseColor: RgbColor,
        u: Float,
        v: Float,
        imageAspect: Float,
        amount: Float,
        sampler: (Float, Float) -> RgbColor,
    ): RgbColor {
        if (amount <= 0.0001f) return baseColor
        val (dx, dy) = chromaticAberrationOffset(u, v, imageAspect, amount)
        val r = sampler((u + dx).coerceIn(0f, 1f), (v + dy).coerceIn(0f, 1f)).r
        val b = sampler((u - dx).coerceIn(0f, 1f), (v - dy).coerceIn(0f, 1f)).b
        return RgbColor(r = r, g = baseColor.g, b = b)
    }

    /**
     * Soft highlight extractor matching retro_pipeline.frag's extractHighlight.
     */
    fun extractHighlight(color: RgbColor, threshold: Float): RgbColor {
        val luma = 0.2126f * color.r + 0.7152f * color.g + 0.0722f * color.b
        val knee = 0.12f
        val weight = smoothstep(threshold - knee, threshold + knee, luma)
        val excess = (luma - threshold * 0.85f).coerceAtLeast(0f)
        val scale = weight * excess
        return RgbColor(color.r * scale, color.g * scale, color.b * scale)
    }

    /**
     * Resolution-independent bloom + film halation matching retro_pipeline.frag.
     * Supports PREVIEW/VIDEO (8-tap single ring) and PHOTO (16-tap dual ring) tiers.
     */
    fun applyBloomAndHalation(
        baseColor: RgbColor,
        u: Float,
        v: Float,
        imageAspect: Float,
        bloomIntensity: Float,
        bloomThreshold: Float,
        bloomRadius: Float,
        halationIntensity: Float,
        halationColor: RgbColor,
        quality: RenderQuality,
        sampler: (Float, Float) -> RgbColor,
    ): RgbColor {
        val bloomAmt = bloomIntensity.coerceIn(0f, 1f)
        val halationAmt = halationIntensity.coerceIn(0f, 1f)
        if (bloomAmt <= 0.0001f && halationAmt <= 0.0001f) return baseColor

        val threshold = bloomThreshold.coerceIn(0.1f, 0.99f)
        val effRadius = bloomRadius.coerceAtLeast(0.006f)
        val (stepX, stepY) = shortSideStepToUv(effRadius, imageAspect)

        fun ring(sx: Float, sy: Float): RgbColor {
            val dx = sx * 0.70710678f
            val dy = sy * 0.70710678f
            val offsets = arrayOf(
                sx to 0f,
                -sx to 0f,
                0f to sy,
                0f to -sy,
                dx to dy,
                -dx to dy,
                dx to -dy,
                -dx to -dy,
            )
            var sr = 0f
            var sg = 0f
            var sb = 0f
            for ((ox, oy) in offsets) {
                val h = extractHighlight(sampler(u + ox, v + oy), threshold)
                sr += h.r
                sg += h.g
                sb += h.b
            }
            return RgbColor(sr * 0.125f, sg * 0.125f, sb * 0.125f)
        }

        val glow = if (quality == RenderQuality.PHOTO) {
            val inner = ring(stepX * 0.55f, stepY * 0.55f)
            val outer = ring(stepX * 1.25f, stepY * 1.25f)
            RgbColor(
                r = inner.r * 0.55f + outer.r * 0.45f,
                g = inner.g * 0.55f + outer.g * 0.45f,
                b = inner.b * 0.55f + outer.b * 0.45f,
            )
        } else {
            ring(stepX, stepY)
        }

        val glowLuma = 0.2126f * glow.r + 0.7152f * glow.g + 0.0722f * glow.b
        val combR = glow.r * bloomAmt * 1.15f + halationColor.r * glowLuma * halationAmt * 1.35f
        val combG = glow.g * bloomAmt * 1.15f + halationColor.g * glowLuma * halationAmt * 1.35f
        val combB = glow.b * bloomAmt * 1.15f + halationColor.b * glowLuma * halationAmt * 1.35f

        return RgbColor(
            r = (baseColor.r + combR * (1f - baseColor.r * 0.35f)).coerceIn(0f, 1f),
            g = (baseColor.g + combG * (1f - baseColor.g * 0.35f)).coerceIn(0f, 1f),
            b = (baseColor.b + combB * (1f - baseColor.b * 0.35f)).coerceIn(0f, 1f),
        )
    }

    /**
     * Resolution-independent aspect-corrected vignette multiplier in [0.15, 1.0].
     * (u, v) are upright normalized full-image coordinates in [0, 1].
     */
    fun vignetteFactor(
        u: Float,
        v: Float,
        imageAspect: Float,
        strength: Float,
        radius: Float,
    ): Float {
        if (strength <= 0.0001f) return 1f
        val aspect = imageAspect.coerceAtLeast(0.01f)
        val scaleX = if (aspect >= 1f) aspect else 1f
        val scaleY = if (aspect >= 1f) 1f else 1f / aspect

        val dx = (u - 0.5f) * scaleX
        val dy = (v - 0.5f) * scaleY
        val cornerDist = hypot(0.5f * scaleX, 0.5f * scaleY).coerceAtLeast(0.0001f)
        val normDist = hypot(dx, dy) / cornerDist

        val inner = (radius * 0.55f).coerceIn(0.05f, 0.95f)
        val outer = maxOf(inner + 0.35f, radius)
        val falloff = smoothstep(inner, outer, normDist)
        return 1f - strength.coerceIn(0f, 1f) * falloff * 0.85f
    }

    internal fun smoothstep(edge0: Float, edge1: Float, x: Float): Float {
        val denom = (edge1 - edge0).coerceAtLeast(1e-6f)
        val t = ((x - edge0) / denom).coerceIn(0f, 1f)
        return t * t * (3f - 2f * t)
    }
}
