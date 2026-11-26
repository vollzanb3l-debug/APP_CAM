package com.retrocam.app.effects

import com.retrocam.app.recipe.HexColorParser
import com.retrocam.app.recipe.RgbColor
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.File

class DateStampEffectTest {

    private val eps = 1e-5f
    private val retroOrange = HexColorParser.parseOrDefault("#FF8C1A")

    @Test
    fun dateEncodingAndUnpacking_roundTripsAccurately() {
        val ymd = OverlayMath.encodeDateYmd(2026, 11, 26)
        assertEquals(261126, ymd)
        assertArrayEquals(intArrayOf(2, 6, 1, 1, 2, 6), OverlayMath.unpackDateDigits(ymd))

        val ymd2 = OverlayMath.encodeDateYmd(2003, 1, 9)
        assertEquals(30109, ymd2)
        assertArrayEquals(intArrayOf(0, 3, 0, 1, 0, 9), OverlayMath.unpackDateDigits(ymd2))
    }

    @Test
    fun disabledDateStamp_isExactIdentityEverywhere() {
        val dark = RgbColor(0.15f, 0.18f, 0.20f)
        val out = OverlayMath.applyDateStamp(
            baseColor = dark,
            u = 0.88f,
            v = 0.94f,
            imageAspect = 0.75f,
            enabled = false,
            intensity = 1.0f,
            stampColor = retroOrange,
            dateYmd = 261126,
        )
        assertEquals(dark, out)
    }

    @Test
    fun enabledDateStamp_lightsUpBottomRightWithOrangeAndLeavesRestUntouched() {
        val dark = RgbColor(0.10f, 0.10f, 0.10f)
        val aspect = 0.75f

        // Center and top-left must be completely untouched
        val topLeft = OverlayMath.applyDateStamp(dark, 0.1f, 0.1f, aspect, true, 0.9f, retroOrange, 261126)
        val center = OverlayMath.applyDateStamp(dark, 0.5f, 0.5f, aspect, true, 0.9f, retroOrange, 261126)
        assertEquals(dark, topLeft)
        assertEquals(dark, center)

        // Scan bottom-right stamp region: must find illuminated orange segments (R > G > B)
        var maxR = 0f
        var orangeHitFound = false
        for (ix in 0..40) {
            val u = 0.72f + ix * 0.006f
            for (iy in 0..20) {
                val v = 0.91f + iy * 0.003f
                val c = OverlayMath.applyDateStamp(dark, u, v, aspect, true, 0.95f, retroOrange, 261126)
                if (c.r > maxR) maxR = c.r
                if (c.r > 0.75f && c.r > c.g && c.g > c.b) {
                    orangeHitFound = true
                }
            }
        }
        assertTrue("Expected bright orange LED digit pixels in bottom-right region (maxR=$maxR)", orangeHitFound)

        // Resolution independence: preview 720x960 == photo 3000x4000
        val prev = OverlayMath.applyDateStamp(dark, 0.85f, 0.945f, 720f / 960f, true, 0.9f, retroOrange, 261126)
        val photo = OverlayMath.applyDateStamp(dark, 0.85f, 0.945f, 3000f / 4000f, true, 0.9f, retroOrange, 261126)
        assertEquals(prev.r, photo.r, eps)
        assertEquals(prev.g, photo.g, eps)
        assertEquals(prev.b, photo.b, eps)
    }

    @Test
    fun shaderDeclaresDateStampOverlayAfterFilmGrain() {
        val frag = resolveAssetFile("shaders/retro_pipeline.frag").readText()
        for (u in listOf("uDateStampIntensity", "uDateStampColor", "uDateYmd", "applyDateStamp")) {
            assertTrue("Missing $u in retro_pipeline.frag", frag.contains(u))
        }
        val mainBody = frag.substringAfter("void main()")
        val grainIdx = mainBody.indexOf("applyFilmGrain")
        val dateIdx = mainBody.indexOf("applyDateStamp")
        assertTrue(grainIdx in 0 until dateIdx)
    }

    private fun resolveAssetFile(relative: String): File {
        val candidates = listOf(
            File("src/main/assets/$relative"),
            File("app/src/main/assets/$relative"),
        )
        return candidates.firstOrNull { it.exists() }
            ?: error("Could not find asset $relative from ${File(".").absolutePath}")
    }
}
