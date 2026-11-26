package com.retrocam.app.ui

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class GridOverlayMathTest {

    private val eps = 1e-4f

    @Test
    fun zeroOrNegativeBounds_returnsNoLines() {
        assertTrue(GridOverlayMath.computeRuleOfThirdsLines(0f, 100f).isEmpty())
        assertTrue(GridOverlayMath.computeRuleOfThirdsLines(100f, 0f).isEmpty())
        assertTrue(GridOverlayMath.computeRuleOfThirdsLines(-10f, 100f).isEmpty())
    }

    @Test
    fun validViewport_returnsTwoVerticalAndTwoHorizontalThirdsLines() {
        val lines = GridOverlayMath.computeRuleOfThirdsLines(900f, 1200f)
        assertEquals(4, lines.size)

        // Vertical lines at x = 300 and x = 600 spanning y = 0..1200
        assertEquals(300f, lines[0].startX, eps)
        assertEquals(0f, lines[0].startY, eps)
        assertEquals(300f, lines[0].endX, eps)
        assertEquals(1200f, lines[0].endY, eps)

        assertEquals(600f, lines[1].startX, eps)
        assertEquals(0f, lines[1].startY, eps)
        assertEquals(600f, lines[1].endX, eps)
        assertEquals(1200f, lines[1].endY, eps)

        // Horizontal lines at y = 400 and y = 800 spanning x = 0..900
        assertEquals(0f, lines[2].startX, eps)
        assertEquals(400f, lines[2].startY, eps)
        assertEquals(900f, lines[2].endX, eps)
        assertEquals(400f, lines[2].endY, eps)

        assertEquals(0f, lines[3].startX, eps)
        assertEquals(800f, lines[3].startY, eps)
        assertEquals(900f, lines[3].endX, eps)
        assertEquals(800f, lines[3].endY, eps)
    }
}
