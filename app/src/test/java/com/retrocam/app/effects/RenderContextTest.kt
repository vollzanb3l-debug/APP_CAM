package com.retrocam.app.effects

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

class RenderContextTest {

    @Test
    fun renderQuality_exposesDistinctPreviewVideoPhotoTiers() {
        assertEquals(0, RenderQuality.PREVIEW.uniformValue)
        assertEquals(1, RenderQuality.VIDEO.uniformValue)
        assertEquals(2, RenderQuality.PHOTO.uniformValue)
    }

    @Test
    fun photoFrameParams_usesFixedDeterministicGrainSeed() {
        val a = FrameRenderParams.forPhoto()
        val b = FrameRenderParams.forPhoto()
        assertEquals(RenderQuality.PHOTO, a.quality)
        assertEquals(a.grainSeed, b.grainSeed, 0f)

        val custom = FrameRenderParams.forPhoto(seed = 42.5f)
        assertEquals(42.5f, custom.grainSeed, 0f)
        assertEquals(RenderQuality.PHOTO, custom.quality)
    }

    @Test
    fun streamFrameParams_advancesSeedPerFrameAndHonorsOverride() {
        val frame1 = FrameRenderParams.forStreamFrame(
            frameIndex = 1,
            timeSec = 0.033f,
            quality = RenderQuality.VIDEO,
        )
        val frame2 = FrameRenderParams.forStreamFrame(
            frameIndex = 2,
            timeSec = 0.066f,
            quality = RenderQuality.VIDEO,
        )
        assertEquals(RenderQuality.VIDEO, frame1.quality)
        assertEquals(1, frame1.frameIndex)
        assertEquals(2, frame2.frameIndex)
        assertNotEquals(frame1.grainSeed, frame2.grainSeed, 0f)

        val overridden1 = FrameRenderParams.forStreamFrame(
            frameIndex = 1,
            timeSec = 0.033f,
            quality = RenderQuality.PREVIEW,
            fixedSeedOverride = 99.0f,
        )
        val overridden2 = FrameRenderParams.forStreamFrame(
            frameIndex = 5,
            timeSec = 0.200f,
            quality = RenderQuality.PREVIEW,
            fixedSeedOverride = 99.0f,
        )
        assertEquals(99.0f, overridden1.grainSeed, 0f)
        assertEquals(99.0f, overridden2.grainSeed, 0f)
    }
}
