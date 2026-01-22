package com.sacrament.ui.foundation.color

import androidx.compose.ui.graphics.Color
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

/**
 * Unit tests for SacramentTagColors.
 * 
 * Tests: `sacrament/src/main/java/com/sacrament/ui/foundation/color/SacramentColorTokens.kt`
 * (specifically the `SacramentTagColors` data class and its hex value conversion logic)
 * 
 * Verifies tag color palette generation and hex value conversion.
 * Uses the actual light theme colors as the source of truth.
 */
@DisplayName("Tag Colors")
class SacramentTagColorsTest {

    // Use actual implementation colors as source of truth
    private val testTagColors = LightSacramentColors.tags

    @Test
    @DisplayName("Palette should contain all 15 tag colors")
    fun palette_shouldContain15Colors() {
        assertEquals(15, testTagColors.palette.size)
    }

    @Test
    @DisplayName("Palette should contain all defined colors in correct order")
    fun palette_shouldContainAllColorsInCorrectOrder() {
        val palette = testTagColors.palette
        assertEquals(testTagColors.red, palette[0])
        assertEquals(testTagColors.orange, palette[1])
        assertEquals(testTagColors.amber, palette[2])
        assertEquals(testTagColors.yellow, palette[3])
        assertEquals(testTagColors.lime, palette[4])
        assertEquals(testTagColors.green, palette[5])
        assertEquals(testTagColors.teal, palette[6])
        assertEquals(testTagColors.cyan, palette[7])
        assertEquals(testTagColors.blue, palette[8])
        assertEquals(testTagColors.indigo, palette[9])
        assertEquals(testTagColors.purple, palette[10])
        assertEquals(testTagColors.deepPurple, palette[11])
        assertEquals(testTagColors.pink, palette[12])
        assertEquals(testTagColors.brown, palette[13])
        assertEquals(testTagColors.gray, palette[14])
    }

    @Test
    @DisplayName("Tag colors should match palette constants")
    fun tagColors_shouldMatchPaletteConstants() {
        // Verify actual colors match the palette definitions
        assertEquals(ErrorRed, testTagColors.red)
        assertEquals(WarningOrange, testTagColors.orange)
        assertEquals(Amber, testTagColors.amber)
        assertEquals(Yellow, testTagColors.yellow)
        assertEquals(Lime, testTagColors.lime)
        assertEquals(SuccessGreen, testTagColors.green)
        assertEquals(Teal, testTagColors.teal)
        assertEquals(Cyan, testTagColors.cyan)
        assertEquals(InfoBlue, testTagColors.blue)
        assertEquals(Indigo, testTagColors.indigo)
        assertEquals(Purple, testTagColors.purple)
        assertEquals(DeepPurple, testTagColors.deepPurple)
        assertEquals(Pink, testTagColors.pink)
        assertEquals(Brown, testTagColors.brown)
        assertEquals(Gray, testTagColors.gray)
    }

    @Test
    @DisplayName("Hex values should be in proper #RRGGBB format")
    fun hexValues_shouldBeProperlyFormatted() {
        val hexValues = testTagColors.hexValues
        
        hexValues.forEach { hex ->
            assertTrue(
                hex.matches(Regex("^#[0-9A-F]{6}$")),
                "Hex value $hex should match #RRGGBB format"
            )
        }
    }

    @Test
    @DisplayName("Hex values count should match palette size")
    fun hexValues_shouldMatchPaletteSize() {
        assertEquals(
            testTagColors.palette.size,
            testTagColors.hexValues.size
        )
    }

    @Test
    @DisplayName("Actual tag colors should convert to correct hex values")
    fun actualTagColors_shouldConvertToExpectedHexValues() {
        val hexValues = testTagColors.hexValues
        
        // Verify actual palette colors convert correctly
        assertEquals("#F44336", hexValues[0])  // ErrorRed
        assertEquals("#FF9800", hexValues[1])  // WarningOrange
        assertEquals("#FFC107", hexValues[2])  // Amber
        assertEquals("#FFEB3B", hexValues[3])  // Yellow
        assertEquals("#CDDC39", hexValues[4])  // Lime
        assertEquals("#4CAF50", hexValues[5])  // SuccessGreen
        assertEquals("#009688", hexValues[6])  // Teal
        assertEquals("#00BCD4", hexValues[7])  // Cyan
        assertEquals("#2196F3", hexValues[8])  // InfoBlue
        assertEquals("#3F51B5", hexValues[9])  // Indigo
        assertEquals("#9C27B0", hexValues[10]) // Purple
        assertEquals("#673AB7", hexValues[11]) // DeepPurple
        assertEquals("#E91E63", hexValues[12]) // Pink
        assertEquals("#795548", hexValues[13]) // Brown
        assertEquals("#9E9E9E", hexValues[14]) // Gray
    }

    @Test
    @DisplayName("Pure primary colors should convert to standard hex values")
    fun purePrimaryColors_shouldConvertToStandardHex() {
        val testPureColors = SacramentTagColors(
            red = Color.Red,
            orange = Color.Black,
            amber = Color.Black,
            yellow = Color.Yellow,
            lime = Color.Black,
            green = Color.Green,
            teal = Color.Black,
            cyan = Color.Cyan,
            blue = Color.Blue,
            indigo = Color.Black,
            purple = Color.Magenta,
            deepPurple = Color.Black,
            pink = Color.Black,
            brown = Color.Black,
            gray = Color.Gray
        )
        
        val hexValues = testPureColors.hexValues
        assertEquals("#FF0000", hexValues[0])  // Red
        assertEquals("#FFFF00", hexValues[3])  // Yellow
        assertEquals("#00FF00", hexValues[5])  // Green
        assertEquals("#00FFFF", hexValues[7])  // Cyan
        assertEquals("#0000FF", hexValues[8])  // Blue
        assertEquals("#FF00FF", hexValues[10]) // Magenta
    }

    @Test
    @DisplayName("White and black colors should convert to #FFFFFF and #000000")
    fun extremeColors_shouldConvertCorrectly() {
        val extremeColorTags = SacramentTagColors(
            red = Color.White,
            orange = Color.Black,
            amber = Color.Black,
            yellow = Color.Black,
            lime = Color.Black,
            green = Color.Black,
            teal = Color.Black,
            cyan = Color.Black,
            blue = Color.Black,
            indigo = Color.Black,
            purple = Color.Black,
            deepPurple = Color.Black,
            pink = Color.Black,
            brown = Color.Black,
            gray = Color.Black
        )
        
        val hexValues = extremeColorTags.hexValues
        assertEquals("#FFFFFF", hexValues[0]) // White
        assertEquals("#000000", hexValues[1]) // Black
    }
}
