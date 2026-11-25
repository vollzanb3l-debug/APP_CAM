package com.retrocam.app.recipe

import kotlin.math.hypot

/**
 * Pure-Kotlin compositor merging Camera + Lens + Film into a single FinalRecipe
 * following docs/ARCHITECTURE.md Section 7.4 rules.
 */
object RecipeCompositor {

    fun compose(
        camera: CameraSpec,
        lens: LensSpec,
        film: FilmSpec,
    ): FinalRecipe {
        val temp = (camera.whiteBalance.temp + film.grade.temp).coerceIn(-1f, 1f)
        val tint = (camera.whiteBalance.tint + film.grade.tint).coerceIn(-1f, 1f)
        val contrast = (camera.tone.contrast * film.grade.contrast).coerceIn(0.5f, 2.0f)
        val saturation = (camera.saturation * film.grade.saturation).coerceIn(0f, 2.5f)
        val shadowLift = camera.tone.shadowLift.coerceIn(0f, 0.4f)
        val blackLift = film.grade.blackLift.coerceIn(0f, 0.3f)
        val combinedNoise = hypot(film.grain.amount, camera.sensorNoise.amount).coerceIn(0f, 1f)

        return FinalRecipe(
            temp = temp,
            tint = tint,
            contrast = contrast,
            saturation = saturation,
            highlightRolloff = camera.tone.highlightRolloff.coerceIn(0f, 1f),
            shadowLift = shadowLift,
            blackLift = blackLift,
            highlightTintHex = film.grade.highlightTint,
            shadowTintHex = film.grade.shadowTint,
            tintStrength = film.grade.tintStrength.coerceIn(0f, 1f),
            aspectRatio = camera.aspectRatio,
            softness = lens.softness.coerceIn(0f, 1f),
            bloomIntensity = lens.bloom.intensity.coerceIn(0f, 1f),
            bloomThreshold = lens.bloom.threshold.coerceIn(0f, 1f),
            bloomRadius = lens.bloom.radius.coerceIn(0f, 0.1f),
            vignetteStrength = lens.vignette.strength.coerceIn(0f, 1f),
            vignetteRadius = lens.vignette.radius.coerceIn(0.1f, 1.5f),
            distortionK1 = lens.distortion.k1.coerceIn(-0.5f, 0.5f),
            chromaticAberration = lens.chromaticAberration.coerceIn(0f, 1f),
            flare = lens.flare.coerceIn(0f, 1f),
            combinedNoiseAmount = combinedNoise,
            sensorNoiseChroma = camera.sensorNoise.chroma.coerceIn(0f, 1f),
            sharpen = camera.sharpen.coerceIn(0f, 1f),
            grainSize = film.grain.size.coerceIn(0.0001f, 0.02f),
            grainRoughness = film.grain.roughness.coerceIn(0f, 1f),
            halationIntensity = film.halation.intensity.coerceIn(0f, 1f),
            halationColorHex = film.halation.color,
            lut = film.lut,
            dateStampAvailable = camera.dateStamp.available,
            dateStampDefaultOn = camera.dateStamp.defaultOn,
            dateStampFormat = camera.dateStamp.format,
            dateStampColorHex = camera.dateStamp.color,
        )
    }
}
