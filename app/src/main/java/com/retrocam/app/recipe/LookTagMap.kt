package com.retrocam.app.recipe

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlin.math.roundToInt

/**
 * Small bounded parameter adjustment for a single qualitative lookTag.
 * All values are draft initial estimates, never calibrated accuracy claims.
 */
@Serializable
data class LookTagAdjustment(
    val tempDelta: Float = 0f,
    val tintDelta: Float = 0f,
    val contrastDelta: Float = 0f,
    val highlightRolloffDelta: Float = 0f,
    val shadowLiftDelta: Float = 0f,
    val saturationDelta: Float = 0f,
    val sensorNoiseAmountDelta: Float = 0f,
    val sensorNoiseChromaDelta: Float = 0f,
    val sharpenDelta: Float = 0f,
    val softnessDelta: Float = 0f,
    val vignetteStrengthDelta: Float = 0f,
    val bloomIntensityDelta: Float = 0f,
    val chromaticAberrationDelta: Float = 0f,
    val lightLeakIntensityDelta: Float = 0f,
    val aspectRatioHint: String? = null,
    val note: String = "",
)

@Serializable
data class LookTagMapSpec(
    val schemaVersion: Int,
    val calibrationStatus: String,
    val disclaimer: String,
    val tags: Map<String, LookTagAdjustment>,
)

/**
 * Pure-Kotlin loader, validator, and deterministic applicator for `assets/look_tag_map.json`.
 * Used to derive consistent `draft` initial values for the 15 camera archetypes from qualitative lookTags.
 */
object LookTagMapper {

    const val CURRENT_SCHEMA_VERSION = 1

    private val allowedCalibrationStatus = setOf("draft", "calibrated")
    private val allowedAspectRatios = setOf("4:3", "3:4", "3:2", "2:3", "16:9", "1:1")

    private val json = Json {
        ignoreUnknownKeys = false
    }

    /**
     * Clean neutral baseline before applying lookTag adjustments for draft archetype generation.
     */
    fun neutralBaseline(
        aspectRatio: String = "4:3",
        dateStampColor: String = "#FF8C1A",
    ): CameraParameterDefaults = CameraParameterDefaults(
        aspectRatio = aspectRatio,
        whiteBalance = WhiteBalanceSpec(temp = 0f, tint = 0f),
        tone = ToneSpec(contrast = 1.00f, highlightRolloff = 0.28f, shadowLift = 0.02f),
        saturation = 1.00f,
        sensorNoise = SensorNoiseSpec(amount = 0.06f, chroma = 0.14f),
        sharpen = 0.15f,
        dateStamp = DateStampSpec(
            available = true,
            defaultOn = false,
            format = "yy MM dd",
            color = dateStampColor,
        ),
        bodyOptics = CameraBodyOpticsSpec(),
    )

    fun parse(rawJson: String): LookTagMapSpec =
        json.decodeFromString(LookTagMapSpec.serializer(), rawJson)

    fun validate(spec: LookTagMapSpec): List<String> = buildList {
        if (spec.schemaVersion != CURRENT_SCHEMA_VERSION) {
            add("lookTagMap.schemaVersion must be $CURRENT_SCHEMA_VERSION")
        }
        if (spec.calibrationStatus !in allowedCalibrationStatus) {
            add("lookTagMap.calibrationStatus must be draft/calibrated")
        }
        if (spec.disclaimer.isBlank()) {
            add("lookTagMap.disclaimer must not be blank")
        }
        if (spec.tags.isEmpty()) {
            add("lookTagMap.tags must not be empty")
        }
        for ((tag, adj) in spec.tags) {
            if (tag.isBlank()) add("lookTagMap contains blank tag key")
            if (adj.tempDelta !in -0.20f..0.20f) add("$tag.tempDelta exceeds small-adjustment bound")
            if (adj.tintDelta !in -0.15f..0.15f) add("$tag.tintDelta exceeds small-adjustment bound")
            if (adj.contrastDelta !in -0.20f..0.20f) add("$tag.contrastDelta exceeds small-adjustment bound")
            if (adj.highlightRolloffDelta !in -0.20f..0.20f) add("$tag.highlightRolloffDelta exceeds small-adjustment bound")
            if (adj.shadowLiftDelta !in -0.10f..0.10f) add("$tag.shadowLiftDelta exceeds small-adjustment bound")
            if (adj.saturationDelta !in -0.20f..0.20f) add("$tag.saturationDelta exceeds small-adjustment bound")
            if (adj.sensorNoiseAmountDelta !in -0.15f..0.15f) add("$tag.sensorNoiseAmountDelta exceeds small-adjustment bound")
            if (adj.sensorNoiseChromaDelta !in -0.20f..0.20f) add("$tag.sensorNoiseChromaDelta exceeds small-adjustment bound")
            if (adj.sharpenDelta !in -0.20f..0.20f) add("$tag.sharpenDelta exceeds small-adjustment bound")
            if (adj.softnessDelta !in -0.10f..0.15f) add("$tag.softnessDelta exceeds small-adjustment bound")
            if (adj.vignetteStrengthDelta !in -0.10f..0.25f) add("$tag.vignetteStrengthDelta exceeds small-adjustment bound")
            if (adj.bloomIntensityDelta !in -0.10f..0.15f) add("$tag.bloomIntensityDelta exceeds small-adjustment bound")
            if (adj.chromaticAberrationDelta !in -0.10f..0.15f) add("$tag.chromaticAberrationDelta exceeds small-adjustment bound")
            if (adj.lightLeakIntensityDelta !in -0.10f..0.15f) add("$tag.lightLeakIntensityDelta exceeds small-adjustment bound")
            adj.aspectRatioHint?.let { ratio ->
                if (ratio !in allowedAspectRatios) add("$tag.aspectRatioHint unsupported: $ratio")
            }
        }
    }

    /**
     * Deterministically applies recognized lookTags onto [base] and clamps every parameter
     * into valid RecipeValidator ranges. Unknown tags are safely ignored.
     */
    fun applyTags(
        base: CameraParameterDefaults,
        lookTags: List<String>,
        tagMap: LookTagMapSpec,
    ): CameraParameterDefaults {
        var aspectRatio = base.aspectRatio
        var temp = base.whiteBalance.temp
        var tint = base.whiteBalance.tint
        var contrast = base.tone.contrast
        var highlightRolloff = base.tone.highlightRolloff
        var shadowLift = base.tone.shadowLift
        var saturation = base.saturation
        var noiseAmount = base.sensorNoise.amount
        var noiseChroma = base.sensorNoise.chroma
        var sharpen = base.sharpen
        var softness = base.bodyOptics.softness
        var vignette = base.bodyOptics.vignetteStrength
        var bloom = base.bodyOptics.bloomIntensity
        var ca = base.bodyOptics.chromaticAberration
        var lightLeak = base.bodyOptics.lightLeakIntensity

        for (rawTag in lookTags) {
            val adj = tagMap.tags[rawTag.trim()] ?: continue
            temp += adj.tempDelta
            tint += adj.tintDelta
            contrast += adj.contrastDelta
            highlightRolloff += adj.highlightRolloffDelta
            shadowLift += adj.shadowLiftDelta
            saturation += adj.saturationDelta
            noiseAmount += adj.sensorNoiseAmountDelta
            noiseChroma += adj.sensorNoiseChromaDelta
            sharpen += adj.sharpenDelta
            softness += adj.softnessDelta
            vignette += adj.vignetteStrengthDelta
            bloom += adj.bloomIntensityDelta
            ca += adj.chromaticAberrationDelta
            lightLeak += adj.lightLeakIntensityDelta
            adj.aspectRatioHint?.let { aspectRatio = it }
        }

        return CameraParameterDefaults(
            aspectRatio = aspectRatio,
            whiteBalance = WhiteBalanceSpec(
                temp = round2(temp.coerceIn(-1f, 1f)),
                tint = round2(tint.coerceIn(-1f, 1f)),
            ),
            tone = ToneSpec(
                contrast = round2(contrast.coerceIn(0.5f, 2.0f)),
                highlightRolloff = round2(highlightRolloff.coerceIn(0f, 1f)),
                shadowLift = round2(shadowLift.coerceIn(0f, 0.4f)),
            ),
            saturation = round2(saturation.coerceIn(0f, 2.5f)),
            sensorNoise = SensorNoiseSpec(
                amount = round2(noiseAmount.coerceIn(0f, 1f)),
                chroma = round2(noiseChroma.coerceIn(0f, 1f)),
            ),
            sharpen = round2(sharpen.coerceIn(0f, 1f)),
            dateStamp = base.dateStamp,
            bodyOptics = CameraBodyOpticsSpec(
                softness = round2(softness.coerceIn(0f, 1f)),
                vignetteStrength = round2(vignette.coerceIn(0f, 1f)),
                bloomIntensity = round2(bloom.coerceIn(0f, 1f)),
                chromaticAberration = round2(ca.coerceIn(0f, 1f)),
                lightLeakIntensity = round2(lightLeak.coerceIn(0f, 1f)),
            ),
        )
    }

    fun deriveDraftDefaults(
        baseAspectRatio: String,
        lookTags: List<String>,
        tagMap: LookTagMapSpec,
        dateStampColor: String = "#FF8C1A",
    ): CameraParameterDefaults = applyTags(
        base = neutralBaseline(aspectRatio = baseAspectRatio, dateStampColor = dateStampColor),
        lookTags = lookTags,
        tagMap = tagMap,
    )

    private fun round2(value: Float): Float = (value * 100f).roundToInt() / 100f
}
