package com.retrocam.app.recipe

import kotlin.math.hypot

/**
 * Pure-Kotlin compositor resolving archetype inheritance and merging
 * Camera + Lens + Film into a single FinalRecipe following docs/ARCHITECTURE.md Section 7.4.
 *
 * Inheritance rule (single source of truth):
 * - Every camera inherits baseline parameters from its matching ArchetypeSpec.cameraDefaults
 *   (or neutral FALLBACK_CAMERA_DEFAULTS if no matching archetype defaults are provided).
 * - Any non-null parameter field on CameraSpec overrides the inherited archetype value.
 * - CameraSpec.tuningNotes (`tuning_notes`) is strictly human documentation and is NEVER read here.
 */
object RecipeCompositor {

    data class ResolvedCameraParameters(
        val aspectRatio: String,
        val whiteBalance: WhiteBalanceSpec,
        val tone: ToneSpec,
        val saturation: Float,
        val sensorNoise: SensorNoiseSpec,
        val sharpen: Float,
        val dateStamp: DateStampSpec,
        val bodyOptics: CameraBodyOpticsSpec,
    )

    val FALLBACK_CAMERA_DEFAULTS = CameraParameterDefaults(
        aspectRatio = "4:3",
        whiteBalance = WhiteBalanceSpec(temp = 0f, tint = 0f),
        tone = ToneSpec(contrast = 1.0f, highlightRolloff = 0.25f, shadowLift = 0f),
        saturation = 1.0f,
        sensorNoise = SensorNoiseSpec(amount = 0f, chroma = 0f),
        sharpen = 0f,
        dateStamp = DateStampSpec(
            available = true,
            defaultOn = false,
            format = "yy MM dd",
            color = "#FF8C1A",
        ),
        bodyOptics = CameraBodyOpticsSpec(),
    )

    fun resolveCamera(
        camera: CameraSpec,
        archetype: ArchetypeSpec? = null,
    ): ResolvedCameraParameters {
        val base = archetype
            ?.takeIf { it.id == camera.archetype }
            ?.cameraDefaults
            ?: FALLBACK_CAMERA_DEFAULTS

        return ResolvedCameraParameters(
            aspectRatio = camera.aspectRatio ?: base.aspectRatio,
            whiteBalance = camera.whiteBalance ?: base.whiteBalance,
            tone = camera.tone ?: base.tone,
            saturation = camera.saturation ?: base.saturation,
            sensorNoise = camera.sensorNoise ?: base.sensorNoise,
            sharpen = camera.sharpen ?: base.sharpen,
            dateStamp = camera.dateStamp ?: base.dateStamp,
            bodyOptics = camera.bodyOptics ?: base.bodyOptics,
        )
    }

    fun compose(
        camera: CameraSpec,
        lens: LensSpec,
        film: FilmSpec,
        archetypes: Collection<ArchetypeSpec>,
    ): FinalRecipe = compose(
        camera = camera,
        lens = lens,
        film = film,
        archetype = archetypes.firstOrNull { it.id == camera.archetype },
    )

    fun compose(
        camera: CameraSpec,
        lens: LensSpec,
        film: FilmSpec,
        archetype: ArchetypeSpec? = null,
    ): FinalRecipe {
        val resolved = resolveCamera(camera = camera, archetype = archetype)

        val temp = (resolved.whiteBalance.temp + film.grade.temp).coerceIn(-1f, 1f)
        val tint = (resolved.whiteBalance.tint + film.grade.tint).coerceIn(-1f, 1f)
        val contrast = (resolved.tone.contrast * film.grade.contrast).coerceIn(0.5f, 2.0f)
        val saturation = (resolved.saturation * film.grade.saturation).coerceIn(0f, 2.5f)
        val shadowLift = resolved.tone.shadowLift.coerceIn(0f, 0.4f)
        val blackLift = film.grade.blackLift.coerceIn(0f, 0.3f)
        val combinedNoise = hypot(film.grain.amount, resolved.sensorNoise.amount).coerceIn(0f, 1f)

        val softness = (resolved.bodyOptics.softness + lens.softness).coerceIn(0f, 1f)
        val bloomIntensity = (resolved.bodyOptics.bloomIntensity + lens.bloom.intensity).coerceIn(0f, 1f)
        val vignetteStrength = (resolved.bodyOptics.vignetteStrength + lens.vignette.strength).coerceIn(0f, 1f)
        val chromaticAberration = (resolved.bodyOptics.chromaticAberration + lens.chromaticAberration).coerceIn(0f, 1f)
        val lightLeakIntensity = resolved.bodyOptics.lightLeakIntensity.coerceIn(0f, 1f)

        return FinalRecipe(
            temp = temp,
            tint = tint,
            contrast = contrast,
            saturation = saturation,
            highlightRolloff = resolved.tone.highlightRolloff.coerceIn(0f, 1f),
            shadowLift = shadowLift,
            blackLift = blackLift,
            highlightTintHex = film.grade.highlightTint,
            shadowTintHex = film.grade.shadowTint,
            tintStrength = film.grade.tintStrength.coerceIn(0f, 1f),
            aspectRatio = resolved.aspectRatio,
            softness = softness,
            bloomIntensity = bloomIntensity,
            bloomThreshold = lens.bloom.threshold.coerceIn(0f, 1f),
            bloomRadius = lens.bloom.radius.coerceIn(0f, 0.1f),
            vignetteStrength = vignetteStrength,
            vignetteRadius = lens.vignette.radius.coerceIn(0.1f, 1.5f),
            distortionK1 = lens.distortion.k1.coerceIn(-0.5f, 0.5f),
            chromaticAberration = chromaticAberration,
            flare = lens.flare.coerceIn(0f, 1f),
            combinedNoiseAmount = combinedNoise,
            sensorNoiseChroma = resolved.sensorNoise.chroma.coerceIn(0f, 1f),
            sharpen = resolved.sharpen.coerceIn(0f, 1f),
            grainSize = film.grain.size.coerceIn(0.0001f, 0.02f),
            grainRoughness = film.grain.roughness.coerceIn(0f, 1f),
            halationIntensity = film.halation.intensity.coerceIn(0f, 1f),
            halationColorHex = film.halation.color,
            lut = film.lut,
            dateStampAvailable = resolved.dateStamp.available,
            dateStampDefaultOn = resolved.dateStamp.defaultOn,
            dateStampEnabled = resolved.dateStamp.available && resolved.dateStamp.defaultOn,
            dateStampFormat = resolved.dateStamp.format,
            dateStampColorHex = resolved.dateStamp.color,
            lightLeakIntensity = lightLeakIntensity,
        )
    }
}
