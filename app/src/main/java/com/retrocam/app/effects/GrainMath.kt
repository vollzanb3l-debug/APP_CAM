package com.retrocam.app.effects

import com.retrocam.app.recipe.RgbColor
import kotlin.math.floor

/**
 * Pure-Kotlin reference implementation of normalized short-side-relative film grain
 * matching retro_pipeline.frag's applyFilmGrain for deterministic JVM unit tests.
 */
object GrainMath {

    fun applyGrain(
        pixel: RgbColor,
        u: Float,
        v: Float,
        imageAspect: Float,
        amount: Float,
        chroma: Float,
        grainSize: Float,
        roughness: Float,
        frameParams: FrameRenderParams,
    ): RgbColor {
        if (amount <= 0.0001f) return pixel
        val aspect = imageAspect.coerceAtLeast(0.01f)
        val posX = if (aspect >= 1f) u * aspect else u
        val posY = if (aspect >= 1f) v else v / aspect
        val cellSize = grainSize.coerceAtLeast(0.0005f)
        val gx = posX / cellSize
        val gy = posY / cellSize

        val seed = frameParams.grainSeed
        val baseNoise = valueNoise(gx, gy, seed) * 2f - 1f
        val lumaNoise = if (frameParams.quality == RenderQuality.PHOTO) {
            val fineNoise = valueNoise(gx * 2.07f + 17.3f, gy * 2.07f + 9.1f, seed * 1.31f) * 2f - 1f
            val fineWeight = roughness.coerceIn(0f, 1f) * 0.35f
            lerp(baseNoise, fineNoise, fineWeight)
        } else {
            baseNoise
        }

        val chromaMix = chroma.coerceIn(0f, 1f) * 0.65f
        val nr: Float
        val ng: Float
        val nb: Float
        if (chroma > 0.0001f) {
            val cr = valueNoise(gx + 31.7f, gy + 11.3f, seed + 7f) * 2f - 1f
            val cg = valueNoise(gx + 13.1f, gy + 47.9f, seed + 19f) * 2f - 1f
            val cb = valueNoise(gx + 59.3f, gy + 23.5f, seed + 37f) * 2f - 1f
            nr = lerp(lumaNoise, cr, chromaMix)
            ng = lerp(lumaNoise, cg, chromaMix)
            nb = lerp(lumaNoise, cb, chromaMix)
        } else {
            nr = lumaNoise
            ng = lumaNoise
            nb = lumaNoise
        }

        val luma = 0.2126f * pixel.r + 0.7152f * pixel.g + 0.0722f * pixel.b
        val centeredLuma = 2f * luma - 1f
        val midtoneWeight = 1f - 0.55f * centeredLuma * centeredLuma
        val strength = amount.coerceIn(0f, 1f) * 0.22f * midtoneWeight

        return RgbColor(
            r = (pixel.r + nr * strength).coerceIn(0f, 1f),
            g = (pixel.g + ng * strength).coerceIn(0f, 1f),
            b = (pixel.b + nb * strength).coerceIn(0f, 1f),
        )
    }

    private fun fract(x: Float): Float = x - floor(x)

    private fun lerp(a: Float, b: Float, t: Float): Float = a + (b - a) * t

    private fun hash12(px: Float, py: Float): Float {
        var x = fract(px * 0.1031f)
        var y = fract(py * 0.1031f)
        var z = fract(px * 0.1031f)
        val d = x * (y + 33.33f) + y * (z + 33.33f) + z * (x + 33.33f)
        x += d
        y += d
        z += d
        return fract((x + y) * z)
    }

    private fun valueNoise(cx: Float, cy: Float, seedOffset: Float): Float {
        val ix = floor(cx)
        val iy = floor(cy)
        val fx = cx - ix
        val fy = cy - iy
        val ux = fx * fx * (3f - 2f * fx)
        val uy = fy * fy * (3f - 2f * fy)

        val sy = seedOffset * 0.37f
        val a = hash12(ix + seedOffset, iy + sy)
        val b = hash12(ix + 1f + seedOffset, iy + sy)
        val c = hash12(ix + seedOffset, iy + 1f + sy)
        val d = hash12(ix + 1f + seedOffset, iy + 1f + sy)
        return lerp(lerp(a, b, ux), lerp(c, d, ux), uy)
    }
}
