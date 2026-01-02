package com.sacrament.ui.foundation.color

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

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
)
