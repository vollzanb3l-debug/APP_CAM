package com.retrocam.app.effects

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.File

class DistortionEffectTest {

    private val eps = 1e-5f

    @Test
    fun zeroK1AndCenterPoint_areExactIdentity() {
        val points = listOf(0f to 0f, 0.5f to 0f, 0.25f to 0.75f, 1f to 1f)
        for ((u, v) in points) {
            val (du, dv) = OpticsMath.applyLensDistortion(u, v, 0.75f, 0f)
            assertEquals(u, du, eps)
            assertEquals(v, dv, eps)
        }

        for (k1 in listOf(-0.5f, -0.15f, 0.15f, 0.5f)) {
            val (cu, cv) = OpticsMath.applyLensDistortion(0.5f, 0.5f, 0.75f, k1)
            assertEquals(0.5f, cu, eps)
            assertEquals(0.5f, cv, eps)
        }
    }

    @Test
    fun barrelAndPincushion_stayInBoundsPreserveSymmetryAndBendInOppositeDirections() {
        val aspect = 0.75f
        for (k1 in listOf(-0.5f, -0.2f, 0.2f, 0.5f)) {
            for (u in listOf(0f, 0.25f, 0.5f, 0.75f, 1f)) {
                for (v in listOf(0f, 0.25f, 0.5f, 0.75f, 1f)) {
                    val (du, dv) = OpticsMath.applyLensDistortion(u, v, aspect, k1)
                    assertTrue("Distorted U=$du out of bounds for k1=$k1", du in 0f..1f)
                    assertTrue("Distorted V=$dv out of bounds for k1=$k1", dv in 0f..1f)
                }
            }
            // Opposite corners must be symmetric around (0.5, 0.5)
            val tl = OpticsMath.applyLensDistortion(0.1f, 0.1f, aspect, k1)
            val br = OpticsMath.applyLensDistortion(0.9f, 0.9f, aspect, k1)
            assertEquals(1f - tl.first, br.first, eps)
            assertEquals(1f - tl.second, br.second, eps)
        }

        // Compare top-center edge (0.5, 0.0) vs top-left corner (0.0, 0.0) radial factor:
        // Barrel (k1 < 0): corner factor (1.0) > edge factor (< 1.0) -> edge bows outward
        val barrelCorner = OpticsMath.applyLensDistortion(0f, 0f, aspect, -0.3f)
        val barrelEdge = OpticsMath.applyLensDistortion(0.5f, 0f, aspect, -0.3f)
        assertEquals(0f, barrelCorner.second, eps)
        assertTrue("Barrel must pull top-center sample inward relative to corner", barrelEdge.second > barrelCorner.second)

        // Pincushion (k1 > 0): corner factor < edge factor -> corners pull inward more than edge
        val pinCorner = OpticsMath.applyLensDistortion(0f, 0f, aspect, 0.3f)
        val pinEdge = OpticsMath.applyLensDistortion(0.5f, 0f, aspect, 0.3f)
        assertTrue("Pincushion must pull corner sample further inward than edge", pinCorner.second > pinEdge.second)

        // Resolution-independent: 720x960 preview == 3000x4000 photo
        val prev = OpticsMath.applyLensDistortion(0.2f, 0.8f, 720f / 960f, -0.15f)
        val photo = OpticsMath.applyLensDistortion(0.2f, 0.8f, 3000f / 4000f, -0.15f)
        assertEquals(prev.first, photo.first, eps)
        assertEquals(prev.second, photo.second, eps)
    }

    @Test
    fun shaderAppliesDistortionInGeometryStageBeforeOptics() {
        val frag = resolveAssetFile("shaders/retro_pipeline.frag").readText()
        assertTrue(frag.contains("uDistortionK1"))
        assertTrue(frag.contains("applyLensDistortion"))
        val mainBody = frag.substringAfter("void main()")
        val distIdx = mainBody.indexOf("applyLensDistortion")
        val softIdx = mainBody.indexOf("sampleWithSoftness")
        val caIdx = mainBody.indexOf("applyChromaticAberration")
        val bloomIdx = mainBody.indexOf("applyBloomAndHalation")
        val vigIdx = mainBody.indexOf("computeVignetteFactor")
        val gradeIdx = mainBody.indexOf("applyColorGrade")
        val grainIdx = mainBody.indexOf("applyFilmGrain")
        assertTrue(distIdx in 0 until softIdx)
        assertTrue(softIdx < caIdx)
        assertTrue(caIdx < bloomIdx)
        assertTrue(bloomIdx < vigIdx)
        assertTrue(vigIdx < gradeIdx)
        assertTrue(gradeIdx < grainIdx)
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
