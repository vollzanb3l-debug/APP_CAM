package com.retrocam.app.effects

import android.opengl.GLES30
import com.retrocam.app.recipe.FinalRecipe
import com.retrocam.app.recipe.HexColorParser
import com.retrocam.app.recipe.RgbColor
import java.util.Calendar

/**
 * Shared shader uniform binder used by both EffectEngine (preview) and
 * CaptureProcessor (full-res capture) so both paths execute identical parameters.
 */
class EffectUniforms private constructor(
    private val uImageAspect: Int,
    private val uDistortionK1: Int,
    private val uSoftness: Int,
    private val uChromaticAberration: Int,
    private val uBloomIntensity: Int,
    private val uBloomThreshold: Int,
    private val uBloomRadius: Int,
    private val uHalationIntensity: Int,
    private val uHalationColor: Int,
    private val uVignetteStrength: Int,
    private val uVignetteRadius: Int,
    private val uTemp: Int,
    private val uTint: Int,
    private val uContrast: Int,
    private val uSaturation: Int,
    private val uHighlightRolloff: Int,
    private val uShadowLift: Int,
    private val uBlackLift: Int,
    private val uHighlightTint: Int,
    private val uShadowTint: Int,
    private val uTintStrength: Int,
    private val uGrainAmount: Int,
    private val uGrainChroma: Int,
    private val uGrainSize: Int,
    private val uGrainRoughness: Int,
    private val uLightLeakIntensity: Int,
    private val uLightLeakSeed: Int,
    private val uDateStampIntensity: Int,
    private val uDateStampColor: Int,
    private val uDateYmd: Int,
    private val uTimeSec: Int,
    private val uFrameIndex: Int,
    private val uGrainSeed: Int,
    private val uQualityLevel: Int,
) {
    fun apply(
        recipe: FinalRecipe,
        frame: FrameRenderParams = FrameRenderParams(),
        imageAspect: Float = 0.75f,
    ) {
        if (uImageAspect >= 0) GLES30.glUniform1f(uImageAspect, imageAspect.coerceAtLeast(0.01f))
        if (uDistortionK1 >= 0) GLES30.glUniform1f(uDistortionK1, recipe.distortionK1)
        if (uSoftness >= 0) GLES30.glUniform1f(uSoftness, recipe.softness)
        if (uChromaticAberration >= 0) GLES30.glUniform1f(uChromaticAberration, recipe.chromaticAberration)
        if (uBloomIntensity >= 0) GLES30.glUniform1f(uBloomIntensity, recipe.bloomIntensity)
        if (uBloomThreshold >= 0) GLES30.glUniform1f(uBloomThreshold, recipe.bloomThreshold)
        if (uBloomRadius >= 0) GLES30.glUniform1f(uBloomRadius, recipe.bloomRadius)
        if (uHalationIntensity >= 0) GLES30.glUniform1f(uHalationIntensity, recipe.halationIntensity)
        val hal = HexColorParser.parseOrDefault(recipe.halationColorHex, RgbColor(1f, 0.416f, 0.239f))
        if (uHalationColor >= 0) GLES30.glUniform3f(uHalationColor, hal.r, hal.g, hal.b)
        if (uVignetteStrength >= 0) GLES30.glUniform1f(uVignetteStrength, recipe.vignetteStrength)
        if (uVignetteRadius >= 0) GLES30.glUniform1f(uVignetteRadius, recipe.vignetteRadius)

        if (uTemp >= 0) GLES30.glUniform1f(uTemp, recipe.temp)
        if (uTint >= 0) GLES30.glUniform1f(uTint, recipe.tint)
        if (uContrast >= 0) GLES30.glUniform1f(uContrast, recipe.contrast)
        if (uSaturation >= 0) GLES30.glUniform1f(uSaturation, recipe.saturation)
        if (uHighlightRolloff >= 0) GLES30.glUniform1f(uHighlightRolloff, recipe.highlightRolloff)
        if (uShadowLift >= 0) GLES30.glUniform1f(uShadowLift, recipe.shadowLift)
        if (uBlackLift >= 0) GLES30.glUniform1f(uBlackLift, recipe.blackLift)

        val hi = HexColorParser.parseOrDefault(recipe.highlightTintHex, RgbColor(1f, 1f, 1f))
        val sh = HexColorParser.parseOrDefault(recipe.shadowTintHex, RgbColor(0f, 0f, 0f))
        if (uHighlightTint >= 0) GLES30.glUniform3f(uHighlightTint, hi.r, hi.g, hi.b)
        if (uShadowTint >= 0) GLES30.glUniform3f(uShadowTint, sh.r, sh.g, sh.b)
        if (uTintStrength >= 0) GLES30.glUniform1f(uTintStrength, recipe.tintStrength)

        if (uGrainAmount >= 0) GLES30.glUniform1f(uGrainAmount, recipe.combinedNoiseAmount)
        if (uGrainChroma >= 0) GLES30.glUniform1f(uGrainChroma, recipe.sensorNoiseChroma)
        if (uGrainSize >= 0) GLES30.glUniform1f(uGrainSize, recipe.grainSize)
        if (uGrainRoughness >= 0) GLES30.glUniform1f(uGrainRoughness, recipe.grainRoughness)

        if (uLightLeakIntensity >= 0) GLES30.glUniform1f(uLightLeakIntensity, recipe.lightLeakIntensity)
        if (uLightLeakSeed >= 0) GLES30.glUniform1f(uLightLeakSeed, recipe.lightLeakSeed)

        val dateIntensity = if (recipe.dateStampEnabled) recipe.dateStampIntensity else 0f
        if (uDateStampIntensity >= 0) GLES30.glUniform1f(uDateStampIntensity, dateIntensity)
        val stampColor = HexColorParser.parseOrDefault(
            recipe.dateStampColorHex,
            RgbColor(1f, 0.549f, 0.102f),
        )
        if (uDateStampColor >= 0) GLES30.glUniform3f(uDateStampColor, stampColor.r, stampColor.g, stampColor.b)
        if (uDateYmd >= 0) {
            val cal = Calendar.getInstance()
            val ymd = OverlayMath.encodeDateYmd(
                year = cal.get(Calendar.YEAR),
                month = cal.get(Calendar.MONTH) + 1,
                day = cal.get(Calendar.DAY_OF_MONTH),
            )
            GLES30.glUniform1i(uDateYmd, ymd)
        }

        if (uTimeSec >= 0) GLES30.glUniform1f(uTimeSec, frame.timeSec)
        if (uFrameIndex >= 0) GLES30.glUniform1i(uFrameIndex, frame.frameIndex)
        if (uGrainSeed >= 0) GLES30.glUniform1f(uGrainSeed, frame.grainSeed)
        if (uQualityLevel >= 0) GLES30.glUniform1i(uQualityLevel, frame.quality.uniformValue)
    }

    companion object {
        fun lookup(programId: Int): EffectUniforms = EffectUniforms(
            uImageAspect = GLES30.glGetUniformLocation(programId, "uImageAspect"),
            uDistortionK1 = GLES30.glGetUniformLocation(programId, "uDistortionK1"),
            uSoftness = GLES30.glGetUniformLocation(programId, "uSoftness"),
            uChromaticAberration = GLES30.glGetUniformLocation(programId, "uChromaticAberration"),
            uBloomIntensity = GLES30.glGetUniformLocation(programId, "uBloomIntensity"),
            uBloomThreshold = GLES30.glGetUniformLocation(programId, "uBloomThreshold"),
            uBloomRadius = GLES30.glGetUniformLocation(programId, "uBloomRadius"),
            uHalationIntensity = GLES30.glGetUniformLocation(programId, "uHalationIntensity"),
            uHalationColor = GLES30.glGetUniformLocation(programId, "uHalationColor"),
            uVignetteStrength = GLES30.glGetUniformLocation(programId, "uVignetteStrength"),
            uVignetteRadius = GLES30.glGetUniformLocation(programId, "uVignetteRadius"),
            uTemp = GLES30.glGetUniformLocation(programId, "uTemp"),
            uTint = GLES30.glGetUniformLocation(programId, "uTint"),
            uContrast = GLES30.glGetUniformLocation(programId, "uContrast"),
            uSaturation = GLES30.glGetUniformLocation(programId, "uSaturation"),
            uHighlightRolloff = GLES30.glGetUniformLocation(programId, "uHighlightRolloff"),
            uShadowLift = GLES30.glGetUniformLocation(programId, "uShadowLift"),
            uBlackLift = GLES30.glGetUniformLocation(programId, "uBlackLift"),
            uHighlightTint = GLES30.glGetUniformLocation(programId, "uHighlightTint"),
            uShadowTint = GLES30.glGetUniformLocation(programId, "uShadowTint"),
            uTintStrength = GLES30.glGetUniformLocation(programId, "uTintStrength"),
            uGrainAmount = GLES30.glGetUniformLocation(programId, "uGrainAmount"),
            uGrainChroma = GLES30.glGetUniformLocation(programId, "uGrainChroma"),
            uGrainSize = GLES30.glGetUniformLocation(programId, "uGrainSize"),
            uGrainRoughness = GLES30.glGetUniformLocation(programId, "uGrainRoughness"),
            uLightLeakIntensity = GLES30.glGetUniformLocation(programId, "uLightLeakIntensity"),
            uLightLeakSeed = GLES30.glGetUniformLocation(programId, "uLightLeakSeed"),
            uDateStampIntensity = GLES30.glGetUniformLocation(programId, "uDateStampIntensity"),
            uDateStampColor = GLES30.glGetUniformLocation(programId, "uDateStampColor"),
            uDateYmd = GLES30.glGetUniformLocation(programId, "uDateYmd"),
            uTimeSec = GLES30.glGetUniformLocation(programId, "uTimeSec"),
            uFrameIndex = GLES30.glGetUniformLocation(programId, "uFrameIndex"),
            uGrainSeed = GLES30.glGetUniformLocation(programId, "uGrainSeed"),
            uQualityLevel = GLES30.glGetUniformLocation(programId, "uQualityLevel"),
        )
    }
}
