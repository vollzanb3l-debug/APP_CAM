package com.retrocam.app.effects

import com.retrocam.app.recipe.RgbColor
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
