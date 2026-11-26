package com.retrocam.app.effects

import com.retrocam.app.recipe.FinalRecipe
import com.retrocam.app.recipe.RgbColor
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.File
import kotlin.math.abs

class ColorGradeGoldenTest {

    // 8-patch synthetic test chart (RGB 0..255):
    // black, dark shadow, mid gray, bright highlight, pure red, pure green, pure blue, warm skin
    private val testChartRgb = intArrayOf(
        0, 0, 0,
        32, 32, 32,
        128, 128, 128,
        240, 240, 240,
        220, 40, 40,
        40, 200, 50,
        40, 70, 220,
        215, 165, 130,
    )

    @Test
    fun neutralRecipe_isExactIdentity() {
        val out = ColorGradeMath.applyToRgbBytes(testChartRgb, FinalRecipe.NEUTRAL)
        assertArrayEquals(testChartRgb, out)
    }

    @Test
    fun warmRetroRecipe_matchesGoldenOutput() {
        val warmRetro = FinalRecipe(
            temp = 0.16f,
            tint = 0.02f,
            contrast = 1.15f,
            saturation = 1.18f,
            highlightRolloff = 0.30f,
            shadowLift = 0.05f,
            blackLift = 0.03f,
            highlightTintHex = "#FFE9C8",
            shadowTintHex = "#1B2A3A",
            tintStrength = 0.10f,
        )

        val actual = ColorGradeMath.applyToRgbBytes(testChartRgb, warmRetro)
        val expected = intArrayOf(
            10, 12, 13,
            39, 39, 39,
            143, 136, 130,
            254, 242, 231,
            240, 43, 42,
            39, 210, 44,
            45, 76, 230,
            234, 172, 127,
        )

        for (i in expected.indices) {
            val diff = abs(expected[i] - actual[i])
            assertTrue(
                "Channel $i expected ${expected[i]} but was ${actual[i]} (diff=$diff)",
                diff <= 1,
            )
        }
    }

    @Test
    fun monochromeRecipe_producesEqualRgbChannels() {
        val bw = FinalRecipe(
            contrast = 1.25f,
            saturation = 0.0f,
            blackLift = 0.02f,
        )
        val out = ColorGradeMath.applyToRgbBytes(testChartRgb, bw)
        var i = 0
        while (i < out.size) {
            assertEquals("Patch ${i / 3} R==G", out[i], out[i + 1])
            assertEquals("Patch ${i / 3} G==B", out[i + 1], out[i + 2])
            i += 3
        }
    }

    @Test
    fun blackLift_raisesPureBlackAndPreservesWhite() {
        val fadeRecipe = FinalRecipe(blackLift = 0.08f)
        val black = ColorGradeMath.applyColorGrade(RgbColor(0f, 0f, 0f), fadeRecipe)
        val white = ColorGradeMath.applyColorGrade(RgbColor(1f, 1f, 1f), fadeRecipe)
        assertEquals(0.08f, black.r, 1e-5f)
        assertEquals(0.08f, black.g, 1e-5f)
        assertEquals(0.08f, black.b, 1e-5f)
        assertEquals(1.0f, white.r, 1e-5f)
        assertEquals(1.0f, white.g, 1e-5f)
        assertEquals(1.0f, white.b, 1e-5f)
    }

    @Test
    fun highlightRolloff_preservesShadowsAndCompressesHighlights() {
        val rolloff = FinalRecipe(highlightRolloff = 0.6f)
        val dark = ColorGradeMath.applyColorGrade(RgbColor(0.2f, 0.4f, 0.5f), rolloff)
        assertEquals(0.2f, dark.r, 1e-5f)
        assertEquals(0.4f, dark.g, 1e-5f)
        assertEquals(0.5f, dark.b, 1e-5f)

        val bright = ColorGradeMath.applyColorGrade(RgbColor(1.0f, 1.0f, 1.0f), rolloff)
        assertTrue(bright.r < 0.95f && bright.r > 0.80f)
    }

    @Test
    fun sharedShaderAsset_matchesEffectUniformContract() {
        val fragFile = resolveAssetFile("shaders/retro_pipeline.frag")
        val source = fragFile.readText()
        assertFalse("ShaderLoader prepends #version", source.contains("#version"))
        val expectedUniforms = listOf(
            "uTemp",
            "uTint",
            "uContrast",
            "uSaturation",
            "uHighlightRolloff",
            "uShadowLift",
            "uBlackLift",
            "uHighlightTint",
            "uShadowTint",
            "uTintStrength",
        )
        for (uniform in expectedUniforms) {
            assertTrue("Missing uniform $uniform in retro_pipeline.frag", source.contains(uniform))
        }
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
