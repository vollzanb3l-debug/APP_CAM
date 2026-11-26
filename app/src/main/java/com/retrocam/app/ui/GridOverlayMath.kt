package com.retrocam.app.ui

/**
 * Pure-Kotlin rule-of-thirds viewfinder grid geometry helper so grid coordinates
 * stay exact across every aspect ratio and screen size, and can be unit-tested on JVM.
 */
object GridOverlayMath {

    data class LineSegment(
        val startX: Float,
        val startY: Float,
        val endX: Float,
        val endY: Float,
    )

    val THIRDS_FRACTIONS: FloatArray = floatArrayOf(1f / 3f, 2f / 3f)

    fun computeRuleOfThirdsLines(width: Float, height: Float): List<LineSegment> {
        if (width <= 0f || height <= 0f) return emptyList()
        return buildList(4) {
            for (fraction in THIRDS_FRACTIONS) {
                val x = width * fraction
                add(LineSegment(startX = x, startY = 0f, endX = x, endY = height))
            }
            for (fraction in THIRDS_FRACTIONS) {
                val y = height * fraction
                add(LineSegment(startX = 0f, startY = y, endX = width, endY = y))
            }
        }
    }
}
