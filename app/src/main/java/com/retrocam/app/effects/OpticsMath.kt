package com.retrocam.app.effects

import kotlin.math.hypot

/**
 * Pure-Kotlin reference implementations of normalized optical effects matching
 * retro_pipeline.frag operation-for-operation for deterministic unit testing.
 */
object OpticsMath {

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
