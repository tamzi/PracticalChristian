package com.sacrament.ui.foundation.color

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

/**
 * Unit tests for color accessor functions.
 * 
 * Tests: `sacrament/src/main/java/com/sacrament/ui/foundation/color/ColorAccessors.kt`
 * 
 * Verifies that brand() and semantic() extension functions correctly
 * map roles to their corresponding colors.
 * Uses the actual light theme colors as the source of truth.
 */
@DisplayName("Color Accessors")
class ColorAccessorsTest {

    // Use actual implementation colors as source of truth
    private val testColorTokens = LightSacramentColors

    @Test
    @DisplayName("brand() accessor should return correct primary color")
    fun brandAccessor_shouldReturnPrimaryColor() {
        val primaryColor = testColorTokens.brand(BrandColorRole.Primary)
        assertEquals(testColorTokens.brand.primary, primaryColor)
    }

    @Test
    @DisplayName("brand() accessor should return correct secondary color")
    fun brandAccessor_shouldReturnSecondaryColor() {
        val secondaryColor = testColorTokens.brand(BrandColorRole.Secondary)
        assertEquals(testColorTokens.brand.secondary, secondaryColor)
    }

    @Test
    @DisplayName("brand() accessor should return correct tertiary color")
    fun brandAccessor_shouldReturnTertiaryColor() {
        val tertiaryColor = testColorTokens.brand(BrandColorRole.Tertiary)
        assertEquals(testColorTokens.brand.tertiary, tertiaryColor)
    }

    @Test
    @DisplayName("semantic() accessor should return correct success color")
    fun semanticAccessor_shouldReturnSuccessColor() {
        val successColor = testColorTokens.semantic(SemanticColorRole.Success)
        assertEquals(testColorTokens.semantic.success, successColor)
    }

    @Test
    @DisplayName("semantic() accessor should return correct warning color")
    fun semanticAccessor_shouldReturnWarningColor() {
        val warningColor = testColorTokens.semantic(SemanticColorRole.Warning)
        assertEquals(testColorTokens.semantic.warning, warningColor)
    }

    @Test
    @DisplayName("semantic() accessor should return correct error color")
    fun semanticAccessor_shouldReturnErrorColor() {
        val errorColor = testColorTokens.semantic(SemanticColorRole.Error)
        assertEquals(testColorTokens.semantic.error, errorColor)
    }

    @Test
    @DisplayName("semantic() accessor should return correct info color")
    fun semanticAccessor_shouldReturnInfoColor() {
        val infoColor = testColorTokens.semantic(SemanticColorRole.Info)
        assertEquals(testColorTokens.semantic.info, infoColor)
    }
}
