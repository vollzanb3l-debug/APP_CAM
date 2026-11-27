package com.retrocam.app.effects

/**
 * Render quality tiers shared across EffectEngine (preview/video) and CaptureProcessor (photo).
 * Heavy passes (e.g. wide multi-pass bloom or high-radius blur in M4+) are enabled at full
 * fidelity only for PHOTO while preserving consistent look character across PREVIEW and VIDEO.
 */
enum class RenderQuality(val uniformValue: Int) {
    PREVIEW_LOW(-1),
    PREVIEW(0),
    VIDEO(1),
    PHOTO(2),
}

/**
 * Per-frame render context passed into the shared shader pipeline so EffectEngine is video-ready:
 * - timeSec / frameIndex advance for live streams and future video recording
 * - grainSeed stays fixed per photo and advances per frame for preview/video (unless overridden)
 * - quality selects preview / video / photo shader tier
 */
data class FrameRenderParams(
    val timeSec: Float = 0f,
    val frameIndex: Int = 0,
    val grainSeed: Float = DEFAULT_PHOTO_GRAIN_SEED,
    val quality: RenderQuality = RenderQuality.PREVIEW,
) {
    companion object {
        const val DEFAULT_PHOTO_GRAIN_SEED: Float = 1337.0f

        fun forPhoto(seed: Float = DEFAULT_PHOTO_GRAIN_SEED): FrameRenderParams =
            FrameRenderParams(
                timeSec = 0f,
                frameIndex = 0,
                grainSeed = seed,
                quality = RenderQuality.PHOTO,
            )

        fun forStreamFrame(
            frameIndex: Int,
            timeSec: Float,
            quality: RenderQuality,
            fixedSeedOverride: Float? = null,
        ): FrameRenderParams {
            val safeIndex = frameIndex.coerceAtLeast(0)
            val safeTime = timeSec.coerceAtLeast(0f)
            val seed = fixedSeedOverride ?: computeFrameSeed(safeIndex)
            return FrameRenderParams(
                timeSec = safeTime,
                frameIndex = safeIndex,
                grainSeed = seed,
                quality = quality,
            )
        }

        fun computeFrameSeed(frameIndex: Int): Float {
            val safeIndex = frameIndex.coerceAtLeast(0)
            return ((safeIndex % 10000) * 1.618034f) + 1.0f
        }
    }
}
