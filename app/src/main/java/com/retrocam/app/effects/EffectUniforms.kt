package com.retrocam.app.effects

import android.opengl.GLES30
import com.retrocam.app.recipe.FinalRecipe
import com.retrocam.app.recipe.HexColorParser
import com.retrocam.app.recipe.RgbColor

/**
 * Shared shader uniform binder used by both EffectEngine (preview) and
 * CaptureProcessor (full-res capture) so both paths execute identical parameters.
 */
class EffectUniforms private constructor(
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
    private val uTimeSec: Int,
    private val uFrameIndex: Int,
    private val uGrainSeed: Int,
    private val uQualityLevel: Int,
) {
    fun apply(
        recipe: FinalRecipe,
        frame: FrameRenderParams = FrameRenderParams(),
    ) {
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

        if (uTimeSec >= 0) GLES30.glUniform1f(uTimeSec, frame.timeSec)
        if (uFrameIndex >= 0) GLES30.glUniform1i(uFrameIndex, frame.frameIndex)
        if (uGrainSeed >= 0) GLES30.glUniform1f(uGrainSeed, frame.grainSeed)
        if (uQualityLevel >= 0) GLES30.glUniform1i(uQualityLevel, frame.quality.uniformValue)
    }

    companion object {
        fun lookup(programId: Int): EffectUniforms = EffectUniforms(
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
            uTimeSec = GLES30.glGetUniformLocation(programId, "uTimeSec"),
            uFrameIndex = GLES30.glGetUniformLocation(programId, "uFrameIndex"),
            uGrainSeed = GLES30.glGetUniformLocation(programId, "uGrainSeed"),
            uQualityLevel = GLES30.glGetUniformLocation(programId, "uQualityLevel"),
        )
    }
}
