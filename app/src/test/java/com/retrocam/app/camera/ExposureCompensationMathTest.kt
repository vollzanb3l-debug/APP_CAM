package com.retrocam.app.camera

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ExposureCompensationMathTest {

    private val eps = 1e-4f

    @Test
    fun unsupportedOrDegenerateHardware_returnsUnsupportedBounds() {
        assertFalse(ExposureCompensationMath.computeBounds(false, -12, 12, 1f / 3f).supported)
        assertFalse(ExposureCompensationMath.computeBounds(true, 0, 0, 1f / 3f).supported)
        assertFalse(ExposureCompensationMath.computeBounds(true, -6, 6, 0f).supported)
        assertEquals(0, ExposureCompensationMath.evToIndex(1.5f, ExposureCompensationMath.ExposureBounds.UNSUPPORTED))
        assertEquals(0f, ExposureCompensationMath.indexToEv(3, ExposureCompensationMath.ExposureBounds.UNSUPPORTED), eps)
    }

    @Test
    fun wideHardwareRange_isClampedToMinus2ToPlus2Ev() {
        // Typical Android device: -12..+12 at 1/3 EV step (-4.0..+4.0 EV)
        val bounds = ExposureCompensationMath.computeBounds(
            isExposureCompensationSupported = true,
            hardwareMinIndex = -12,
            hardwareMaxIndex = 12,
            stepEv = 1f / 3f,
        )
        assertTrue(bounds.supported)
        assertEquals(-6, bounds.minIndex)
        assertEquals(6, bounds.maxIndex)
        assertEquals(-2.0f, bounds.minEv, eps)
        assertEquals(2.0f, bounds.maxEv, eps)

        assertEquals(-6, ExposureCompensationMath.evToIndex(-3.5f, bounds))
        assertEquals(0, ExposureCompensationMath.evToIndex(0f, bounds))
        assertEquals(3, ExposureCompensationMath.evToIndex(1.0f, bounds))
        assertEquals(6, ExposureCompensationMath.evToIndex(4.0f, bounds))
        assertEquals(1.0f, ExposureCompensationMath.indexToEv(3, bounds), eps)
    }

    @Test
    fun narrowHardwareRange_respectsDeviceLimitsWithoutExceedingHardware() {
        // Restricted front camera: -2..+2 at 0.5 EV step (-1.0..+1.0 EV)
        val bounds = ExposureCompensationMath.computeBounds(
            isExposureCompensationSupported = true,
            hardwareMinIndex = -2,
            hardwareMaxIndex = 2,
            stepEv = 0.5f,
        )
        assertTrue(bounds.supported)
        assertEquals(-2, bounds.minIndex)
        assertEquals(2, bounds.maxIndex)
        assertEquals(-1.0f, bounds.minEv, eps)
        assertEquals(1.0f, bounds.maxEv, eps)

        assertEquals(-2, ExposureCompensationMath.evToIndex(-2.0f, bounds))
        assertEquals(2, ExposureCompensationMath.evToIndex(2.0f, bounds))
        assertEquals(-1.0f, ExposureCompensationMath.indexToEv(-5, bounds), eps)
        assertEquals(1.0f, ExposureCompensationMath.indexToEv(5, bounds), eps)
    }

    @Test
    fun formatEv_formatsZeroPositiveAndNegativeCleanly() {
        assertEquals("0.0 EV", ExposureCompensationMath.formatEv(0f))
        assertEquals("0.0 EV", ExposureCompensationMath.formatEv(-0.02f))
        assertEquals("+1.3 EV", ExposureCompensationMath.formatEv(1.333f))
        assertEquals("-2.0 EV", ExposureCompensationMath.formatEv(-2.0f))
    }
}
