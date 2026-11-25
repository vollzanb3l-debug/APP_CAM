package com.retrocam.app.recipe

/**
 * Merged output of Camera + Lens + Film passed into EffectEngine and CaptureProcessor.
 * M1 uses neutral passthrough only; aesthetic parameters are added in M2-M4.
 */
data class FinalRecipe(
    val passthroughOnly: Boolean = true,
)
