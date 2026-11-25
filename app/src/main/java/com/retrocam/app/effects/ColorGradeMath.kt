package com.retrocam.app.effects

import com.retrocam.app.recipe.FinalRecipe
import com.retrocam.app.recipe.HexColorParser
import com.retrocam.app.recipe.RgbColor
import kotlin.math.roundToInt

/**
 * Pure-Kotlin reference implementation matching retro_pipeline.frag's applyColorGrade
 * operation-for-operation, used for deterministic golden-image tests.
 */
object ColorGradeMath {

    fun applyColorGrade(pixel: RgbColor, recipe: FinalRecipe): RgbColor {
        val highlightTint = HexColorParser.parseOrDefault(
            recipe.highlightTintHex,
            RgbColor(1f, 1f, 1f),
        )
        val shadowTint = HexColorParser.parseOrDefault(
            recipe.shadowTintHex,
            RgbColor(0f, 0f, 0f),
        )
        return applyColorGrade(pixel, recipe, highlightTint, shadowTint)
    }

    fun applyColorGrade(
        pixel: RgbColor,
        recipe: FinalRecipe,
        highlightTint: RgbColor,
        shadowTint: RgbColor,
    ): RgbColor {
        // 1. White balance gain
        val rGain = (1f + recipe.temp * 0.25f + recipe.tint * 0.10f).coerceAtLeast(0.1f)
        val gGain = (1f - recipe.tint * 0.15f).coerceAtLeast(0.1f)
        val bGain = (1f - recipe.temp * 0.25f + recipe.tint * 0.10f).coerceAtLeast(0.1f)

        var r = (pixel.r * rGain).coerceIn(0f, 1f)
        var g = (pixel.g * gGain).coerceIn(0f, 1f)
        var b = (pixel.b * bGain).coerceIn(0f, 1f)

        // 2. Shadow lift
        r = liftShadow(r, recipe.shadowLift)
        g = liftShadow(g, recipe.shadowLift)
        b = liftShadow(b, recipe.shadowLift)

        // 3. Smooth highlight rolloff above 0.5
        r = rolloffHighlight(r, recipe.highlightRolloff)
        g = rolloffHighlight(g, recipe.highlightRolloff)
        b = rolloffHighlight(b, recipe.highlightRolloff)

        // 4. Contrast around mid-gray 0.5
        r = ((r - 0.5f) * recipe.contrast + 0.5f).coerceIn(0f, 1f)
        g = ((g - 0.5f) * recipe.contrast + 0.5f).coerceIn(0f, 1f)
        b = ((b - 0.5f) * recipe.contrast + 0.5f).coerceIn(0f, 1f)

        // 5. Saturation (Rec.709 luma)
        val satLuma = 0.2126f * r + 0.7152f * g + 0.0722f * b
        r = (satLuma + (r - satLuma) * recipe.saturation).coerceIn(0f, 1f)
        g = (satLuma + (g - satLuma) * recipe.saturation).coerceIn(0f, 1f)
        b = (satLuma + (b - satLuma) * recipe.saturation).coerceIn(0f, 1f)

        // 6. Film split-tone tint
        if (recipe.tintStrength > 0.0001f) {
            val toneLuma = 0.2126f * r + 0.7152f * g + 0.0722f * b
            val targetR = shadowTint.r + (highlightTint.r - shadowTint.r) * toneLuma
            val targetG = shadowTint.g + (highlightTint.g - shadowTint.g) * toneLuma
            val targetB = shadowTint.b + (highlightTint.b - shadowTint.b) * toneLuma
            r = (r + (targetR - r) * recipe.tintStrength).coerceIn(0f, 1f)
            g = (g + (targetG - g) * recipe.tintStrength).coerceIn(0f, 1f)
            b = (b + (targetB - b) * recipe.tintStrength).coerceIn(0f, 1f)
        }

        // 7. Film black lift / fade
        r = (r * (1f - recipe.blackLift) + recipe.blackLift).coerceIn(0f, 1f)
        g = (g * (1f - recipe.blackLift) + recipe.blackLift).coerceIn(0f, 1f)
        b = (b * (1f - recipe.blackLift) + recipe.blackLift).coerceIn(0f, 1f)

        return RgbColor(r, g, b)
    }

    fun applyToRgbBytes(rgb8: IntArray, recipe: FinalRecipe): IntArray {
        require(rgb8.size % 3 == 0) { "Expected flat RGB triples" }
        val out = IntArray(rgb8.size)
        val highlightTint = HexColorParser.parseOrDefault(
            recipe.highlightTintHex,
            RgbColor(1f, 1f, 1f),
        )
        val shadowTint = HexColorParser.parseOrDefault(
            recipe.shadowTintHex,
            RgbColor(0f, 0f, 0f),
        )
        var i = 0
        while (i < rgb8.size) {
            val src = RgbColor(
                rgb8[i] / 255f,
                rgb8[i + 1] / 255f,
                rgb8[i + 2] / 255f,
            )
            val dst = applyColorGrade(src, recipe, highlightTint, shadowTint)
            out[i] = (dst.r * 255f).roundToInt().coerceIn(0, 255)
            out[i + 1] = (dst.g * 255f).roundToInt().coerceIn(0, 255)
            out[i + 2] = (dst.b * 255f).roundToInt().coerceIn(0, 255)
            i += 3
        }
        return out
    }

    private fun liftShadow(v: Float, shadowLift: Float): Float {
        val inv = 1f - v
        return (v + shadowLift * inv * inv).coerceIn(0f, 1f)
    }

    private fun rolloffHighlight(v: Float, highlightRolloff: Float): Float {
        val over = (v - 0.5f).coerceAtLeast(0f)
        return (v - highlightRolloff * 0.18f * over * over * 4.0f).coerceIn(0f, 1f)
    }
}
