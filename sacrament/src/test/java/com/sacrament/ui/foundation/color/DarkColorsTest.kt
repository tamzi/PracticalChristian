package com.sacrament.ui.foundation.color

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

/**
 * Unit tests for dark theme color mappings.
 *
 * Tests: `sacrament/src/main/java/com/sacrament/ui/foundation/color/DarkColors.kt`
 *
 * Verifies that DarkSacramentColors correctly maps to the intended dark-mode
 * palette values, ensuring proper contrast and visibility on dark backgrounds.
 */
@DisplayName("Dark Theme Colors")
class DarkColorsTest {

    private val darkColors = DarkSacramentColors

    @Nested
    @DisplayName("Surface Colors")
    inner class SurfaceColorsTest {

        @Test
        @DisplayName("background should map to BackgroundDark")
        fun background_shouldMapToDarkValue() {
            assertEquals(BackgroundDark, darkColors.surfaces.background)
        }

        @Test
        @DisplayName("surface should map to SurfaceDark")
        fun surface_shouldMapToDarkValue() {
            assertEquals(SurfaceDark, darkColors.surfaces.surface)
        }

        @Test
        @DisplayName("surfaceVariant should map to Neutral800")
        fun surfaceVariant_shouldMapToNeutral800() {
            assertEquals(Neutral800, darkColors.surfaces.surfaceVariant)
        }

        @Test
        @DisplayName("sunlight should map to dark variant")
        fun sunlight_shouldMapToDarkVariant() {
            assertEquals(SacramentPalette.surfaceSunlightDark, darkColors.surfaces.sunlight)
        }

        @Test
        @DisplayName("sunlightSoft should map to dark variant")
        fun sunlightSoft_shouldMapToDarkVariant() {
            assertEquals(SacramentPalette.surfaceSunlightSoftDark, darkColors.surfaces.sunlightSoft)
        }

        @Test
        @DisplayName("lavender should map to dark variant")
        fun lavender_shouldMapToDarkVariant() {
            assertEquals(SacramentPalette.surfaceLavenderDark, darkColors.surfaces.lavender)
        }

        @Test
        @DisplayName("lavenderSoft should map to dark variant")
        fun lavenderSoft_shouldMapToDarkVariant() {
            assertEquals(SacramentPalette.surfaceLavenderSoftDark, darkColors.surfaces.lavenderSoft)
        }

        @Test
        @DisplayName("rose should map to dark variant")
        fun rose_shouldMapToDarkVariant() {
            assertEquals(SacramentPalette.surfaceRoseDark, darkColors.surfaces.rose)
        }

        @Test
        @DisplayName("roseTint should map to dark variant")
        fun roseTint_shouldMapToDarkVariant() {
            assertEquals(SacramentPalette.surfaceRoseTintDark, darkColors.surfaces.roseTint)
        }

        @Test
        @DisplayName("sky should map to dark variant")
        fun sky_shouldMapToDarkVariant() {
            assertEquals(SacramentPalette.surfaceSkyDark, darkColors.surfaces.sky)
        }

        @Test
        @DisplayName("mint should map to dark variant")
        fun mint_shouldMapToDarkVariant() {
            assertEquals(SacramentPalette.surfaceMintDark, darkColors.surfaces.mint)
        }

        @Test
        @DisplayName("peach should map to dark variant")
        fun peach_shouldMapToDarkVariant() {
            assertEquals(SacramentPalette.surfacePeachDark, darkColors.surfaces.peach)
        }

        @Test
        @DisplayName("peachSoft should map to dark variant")
        fun peachSoft_shouldMapToDarkVariant() {
            assertEquals(SacramentPalette.surfacePeachSoftDark, darkColors.surfaces.peachSoft)
        }
    }

    @Nested
    @DisplayName("Semantic Colors")
    inner class SemanticColorsTest {

        @Test
        @DisplayName("success should use base color")
        fun success_shouldUseBaseColor() {
            assertEquals(SuccessGreen, darkColors.semantic.success)
        }

        @Test
        @DisplayName("successLight should be lighter than success")
        fun successLight_shouldBeLighter() {
            assertEquals(SuccessGreenLight, darkColors.semantic.successLight)
        }

        @Test
        @DisplayName("successDark should be darker than success")
        fun successDark_shouldBeDarker() {
            assertEquals(SuccessGreenDark, darkColors.semantic.successDark)
        }

        @Test
        @DisplayName("warning should use base color")
        fun warning_shouldUseBaseColor() {
            assertEquals(WarningOrange, darkColors.semantic.warning)
        }

        @Test
        @DisplayName("warningLight should be lighter than warning")
        fun warningLight_shouldBeLighter() {
            assertEquals(WarningOrangeLight, darkColors.semantic.warningLight)
        }

        @Test
        @DisplayName("warningDark should be darker than warning")
        fun warningDark_shouldBeDarker() {
            assertEquals(WarningOrangeDark, darkColors.semantic.warningDark)
        }

        @Test
        @DisplayName("error should use base color")
        fun error_shouldUseBaseColor() {
            assertEquals(ErrorRed, darkColors.semantic.error)
        }

        @Test
        @DisplayName("errorLight should be lighter than error")
        fun errorLight_shouldBeLighter() {
            assertEquals(ErrorRedLight, darkColors.semantic.errorLight)
        }

        @Test
        @DisplayName("errorDark should be darker than error")
        fun errorDark_shouldBeDarker() {
            assertEquals(ErrorRedDark, darkColors.semantic.errorDark)
        }

        @Test
        @DisplayName("info should use base color")
        fun info_shouldUseBaseColor() {
            assertEquals(InfoBlue, darkColors.semantic.info)
        }

        @Test
        @DisplayName("infoLight should be lighter than info")
        fun infoLight_shouldBeLighter() {
            assertEquals(InfoBlueLight, darkColors.semantic.infoLight)
        }

        @Test
        @DisplayName("infoDark should be darker than info")
        fun infoDark_shouldBeDarker() {
            assertEquals(InfoBlueDark, darkColors.semantic.infoDark)
        }
    }

    @Nested
    @DisplayName("Utility Colors")
    inner class UtilityColorsTest {

        @Test
        @DisplayName("progressTrack should use Neutral700 for dark mode visibility")
        fun progressTrack_shouldUseNeutral700() {
            assertEquals(Neutral700, darkColors.utilities.progressTrack)
        }

        @Test
        @DisplayName("authProviderSurface should use Neutral800 for dark mode")
        fun authProviderSurface_shouldUseNeutral800() {
            assertEquals(Neutral800, darkColors.utilities.authProviderSurface)
        }

        @Test
        @DisplayName("successAction should use base success color")
        fun successAction_shouldUseBaseSuccess() {
            assertEquals(SuccessGreen, darkColors.utilities.successAction)
        }
    }

    @Nested
    @DisplayName("Navigation Colors")
    inner class NavigationColorsTest {

        @Test
        @DisplayName("barBackground should map to dark variant")
        fun barBackground_shouldMapToDarkVariant() {
            assertEquals(SacramentPalette.navigationBarDark, darkColors.navigation.barBackground)
        }

        @Test
        @DisplayName("selectedIndicator should map to dark variant")
        fun selectedIndicator_shouldMapToDarkVariant() {
            assertEquals(SacramentPalette.navigationSelectedDark, darkColors.navigation.selectedIndicator)
        }

        @Test
        @DisplayName("selectedIcon should map to dark variant")
        fun selectedIcon_shouldMapToDarkVariant() {
            assertEquals(SacramentPalette.navigationSelectedDark, darkColors.navigation.selectedIcon)
        }

        @Test
        @DisplayName("unselectedIcon should map to dark variant")
        fun unselectedIcon_shouldMapToDarkVariant() {
            assertEquals(SacramentPalette.navigationUnselectedDark, darkColors.navigation.unselectedIcon)
        }

        @Test
        @DisplayName("spotlightGlow should map to dark variant")
        fun spotlightGlow_shouldMapToDarkVariant() {
            assertEquals(SacramentPalette.navigationSpotlightDark, darkColors.navigation.spotlightGlow)
        }
    }

    @Nested
    @DisplayName("Interactive Colors")
    inner class InteractiveColorsTest {

        @Test
        @DisplayName("switchTrackOn should map to dark variant")
        fun switchTrackOn_shouldMapToDarkVariant() {
            assertEquals(SacramentPalette.switchTrackOnDark, darkColors.interactive.switchTrackOn)
        }

        @Test
        @DisplayName("switchTrackOff should map to dark variant")
        fun switchTrackOff_shouldMapToDarkVariant() {
            assertEquals(SacramentPalette.switchTrackOffDark, darkColors.interactive.switchTrackOff)
        }

        @Test
        @DisplayName("switchThumbOn should map to dark variant")
        fun switchThumbOn_shouldMapToDarkVariant() {
            assertEquals(SacramentPalette.switchThumbOnDark, darkColors.interactive.switchThumbOn)
        }

        @Test
        @DisplayName("switchThumbOff should map to dark variant")
        fun switchThumbOff_shouldMapToDarkVariant() {
            assertEquals(SacramentPalette.switchThumbOffDark, darkColors.interactive.switchThumbOff)
        }

        @Test
        @DisplayName("switchIconOn should map to dark variant")
        fun switchIconOn_shouldMapToDarkVariant() {
            assertEquals(SacramentPalette.switchIconDark, darkColors.interactive.switchIconOn)
        }

        @Test
        @DisplayName("switchIconOff should use navigation unselected color")
        fun switchIconOff_shouldUseNavigationUnselected() {
            assertEquals(SacramentPalette.navigationUnselectedDark, darkColors.interactive.switchIconOff)
        }
    }

    @Nested
    @DisplayName("Text Colors")
    inner class TextColorsTest {

        @Test
        @DisplayName("strong text should be light on dark background")
        fun strong_shouldBeLightColor() {
            assertEquals(Neutral50, darkColors.text.strong)
        }

        @Test
        @DisplayName("muted text should use Neutral400")
        fun muted_shouldUseNeutral400() {
            assertEquals(Neutral400, darkColors.text.muted)
        }
    }

    @Nested
    @DisplayName("Semantic Consistency")
    inner class SemanticConsistencyTest {

        @Test
        @DisplayName("successLight should match light theme naming pattern")
        fun successLight_shouldFollowNamingPattern() {
            // Verify that *Light is lighter than base in both themes
            assertEquals(LightSacramentColors.semantic.successLight, darkColors.semantic.successLight)
        }

        @Test
        @DisplayName("warningLight should match light theme naming pattern")
        fun warningLight_shouldFollowNamingPattern() {
            assertEquals(LightSacramentColors.semantic.warningLight, darkColors.semantic.warningLight)
        }

        @Test
        @DisplayName("errorLight should match light theme naming pattern")
        fun errorLight_shouldFollowNamingPattern() {
            assertEquals(LightSacramentColors.semantic.errorLight, darkColors.semantic.errorLight)
        }

        @Test
        @DisplayName("infoLight should match light theme naming pattern")
        fun infoLight_shouldFollowNamingPattern() {
            assertEquals(LightSacramentColors.semantic.infoLight, darkColors.semantic.infoLight)
        }
    }
}
