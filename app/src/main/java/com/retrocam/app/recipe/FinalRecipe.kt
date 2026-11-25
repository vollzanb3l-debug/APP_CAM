package com.retrocam.app.recipe

/**
 * Merged output of Camera + Lens + Film passed into EffectEngine and CaptureProcessor.
 * Pure Kotlin: knows nothing about GL, Android, JSON, or UI.
 */
data class FinalRecipe(
    // Color grade & tone (active in M2/M3 shader)
    val temp: Float = 0f,
    val tint: Float = 0f,
    val contrast: Float = 1f,
    val saturation: Float = 1f,
    val highlightRolloff: Float = 0f,
    val shadowLift: Float = 0f,
    val blackLift: Float = 0f,
    val highlightTintHex: String = "#FFFFFF",
    val shadowTintHex: String = "#000000",
    val tintStrength: Float = 0f,
    // Geometry, optics, film texture, and overlay fields (locked in M3 compositor, rendered in M4)
    val aspectRatio: String = "4:3",
    val softness: Float = 0f,
    val bloomIntensity: Float = 0f,
    val bloomThreshold: Float = 0.8f,
    val bloomRadius: Float = 0f,
    val vignetteStrength: Float = 0f,
    val vignetteRadius: Float = 0.85f,
    val distortionK1: Float = 0f,
    val chromaticAberration: Float = 0f,
    val flare: Float = 0f,
    val combinedNoiseAmount: Float = 0f,
    val sensorNoiseChroma: Float = 0f,
    val sharpen: Float = 0f,
    val grainSize: Float = 0.001f,
    val grainRoughness: Float = 0.5f,
    val halationIntensity: Float = 0f,
    val halationColorHex: String = "#FF6A3D",
    val lut: String? = null,
    val dateStampAvailable: Boolean = false,
    val dateStampDefaultOn: Boolean = false,
    val dateStampFormat: String = "yy MM dd",
    val dateStampColorHex: String = "#FF8C1A",
) {
    companion object {
        val NEUTRAL = FinalRecipe()
    }
}
