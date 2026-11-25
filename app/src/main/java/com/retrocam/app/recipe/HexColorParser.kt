package com.retrocam.app.recipe

/**
 * Pure-Kotlin RGB color in normalized 0..1 space and strict #RRGGBB parser.
 */
data class RgbColor(
    val r: Float,
    val g: Float,
    val b: Float,
)

object HexColorParser {

    private val hexRegex = Regex("^#[0-9A-Fa-f]{6}$")

    fun isValidHex(hex: String): Boolean = hexRegex.matches(hex)

    fun parseOrDefault(hex: String, fallback: RgbColor = RgbColor(1f, 1f, 1f)): RgbColor {
        if (!isValidHex(hex)) return fallback
        val r = hex.substring(1, 3).toInt(16) / 255f
        val g = hex.substring(3, 5).toInt(16) / 255f
        val b = hex.substring(5, 7).toInt(16) / 255f
        return RgbColor(r, g, b)
    }
}
