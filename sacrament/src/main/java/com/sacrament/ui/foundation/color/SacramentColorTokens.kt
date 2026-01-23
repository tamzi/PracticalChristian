package com.sacrament.ui.foundation.color

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import kotlin.math.roundToInt

/**
 * Brand accents.
 *
 * Usage:
 * `val colors = SacramentTheme.colors; colors.brand.primary`
 */
@Immutable
data class SacramentBrandColors(
    val primary: Color,
    val secondary: Color,
    val tertiary: Color,
)

/**
 * Text tones used across the system.
 *
 * Usage:
 * `Text("Title", color = SacramentTheme.colors.text.strong)`
 */
@Immutable
data class SacramentTextColors(
    val strong: Color,
    val muted: Color,
    val inverse: Color,
    val onBrand: Color,
)

/**
 * Surface colors for backgrounds and containers.
 *
 * Usage:
 * `Surface(color = SacramentTheme.colors.surfaces.lavender) { ... }`
 */
@Immutable
data class SacramentSurfaceColors(
    val background: Color,
    val surface: Color,
    val surfaceVariant: Color,
    val sunlight: Color,
    val sunlightSoft: Color,
    val lavender: Color,
    val lavenderSoft: Color,
    val rose: Color,
    val roseTint: Color,
    val sky: Color,
    val mint: Color,
    val peach: Color,
    val peachSoft: Color,
)

/**
 * Semantic intent colors.
 *
 * Usage:
 * `Icon(tint = SacramentTheme.colors.semantic.error, ...)`
 */
@Immutable
data class SacramentSemanticColors(
    val success: Color,
    val successLight: Color,
    val successDark: Color,
    val warning: Color,
    val warningLight: Color,
    val warningDark: Color,
    val error: Color,
    val errorLight: Color,
    val errorDark: Color,
    val info: Color,
    val infoLight: Color,
    val infoDark: Color,
)

/**
 * Utility colors used in components.
 *
 * Usage:
 * `trackColor = SacramentTheme.colors.utilities.progressTrack`
 */
@Immutable
data class SacramentUtilityColors(
    val progressTrack: Color,
    val authProviderSurface: Color,
    val successAction: Color,
    val onSuccessAction: Color,
)

/**
 * Onboarding-specific accents.
 *
 * Usage:
 * `accentColor = SacramentTheme.colors.onboarding.accentPrimary`
 */
@Immutable
data class SacramentOnboardingColors(
    val accentPrimary: Color,
    val accentSecondary: Color,
    val accentTertiary: Color,
    val accentQuaternary: Color,
)

/**
 * Navigation component colors for bottom bars, rails, and tabs.
 *
 * Usage:
 * `backgroundColor = SacramentTheme.colors.navigation.barBackground`
 */
@Immutable
data class SacramentNavigationColors(
    val barBackground: Color,
    val selectedIndicator: Color,
    val selectedIcon: Color,
    val unselectedIcon: Color,
    val spotlightGlow: Color,
)

/**
 * Interactive component colors for switches, toggles, and controls.
 *
 * Usage:
 * `trackColor = SacramentTheme.colors.interactive.switchTrackOn`
 */
@Immutable
data class SacramentInteractiveColors(
    val switchTrackOn: Color,
    val switchTrackOff: Color,
    val switchThumbOn: Color,
    val switchThumbOff: Color,
    val switchIconOn: Color,
    val switchIconOff: Color,
)

/**
 * Curated tag colors for user categorization.
 *
 * Usage:
 * `val palette = SacramentTheme.colors.tags.palette`
 * `val hexValues = SacramentTheme.colors.tags.hexValues`
 */
@Immutable
data class SacramentTagColors(
    val red: Color,
    val orange: Color,
    val amber: Color,
    val yellow: Color,
    val lime: Color,
    val green: Color,
    val teal: Color,
    val cyan: Color,
    val blue: Color,
    val indigo: Color,
    val purple: Color,
    val deepPurple: Color,
    val pink: Color,
    val brown: Color,
    val gray: Color,
) {
    /**
     * All tag colors as a list for iteration.
     */
    val palette: List<Color> = listOf(
        red, orange, amber, yellow, lime,
        green, teal, cyan, blue, indigo,
        purple, deepPurple, pink, brown, gray
    )

    /**
     * Hex string values for database storage.
     * Generated dynamically from the palette colors.
     */
    val hexValues: List<String>
        get() = palette.map { color ->
            // Compose Color stores as ARGB in ULong, need to extract RGB components
            val red = (color.red * 255).roundToInt()
            val green = (color.green * 255).roundToInt()
            val blue = (color.blue * 255).roundToInt()
            "#%02X%02X%02X".format(red, green, blue)
        }
}

/**
 * Aggregated color tokens for the design system.
 *
 * Usage:
 * `val colors = SacramentTheme.colors`
 */
@Immutable
data class SacramentColorTokens(
    val brand: SacramentBrandColors,
    val text: SacramentTextColors,
    val surfaces: SacramentSurfaceColors,
    val semantic: SacramentSemanticColors,
    val utilities: SacramentUtilityColors,
    val onboarding: SacramentOnboardingColors,
    val navigation: SacramentNavigationColors,
    val interactive: SacramentInteractiveColors,
    val tags: SacramentTagColors,
)
