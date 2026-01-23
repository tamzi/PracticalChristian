package com.sacrament.ui.foundation.color

import androidx.compose.ui.graphics.Color

// Primary Colors
val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650A4)
val PurpleGrey40 = Color(0xFF625B71)
val Pink40 = Color(0xFF7D5260)

// Background Colors
val BackgroundLight = Color(0xFFF5F5F5)
val BackgroundDark = Color(0xFF141414)
val OnBackgroundLight = Color(0xFF000000)
val OnBackgroundDark = Color(0xFFFFFFFF)

// Surface Colors
val SurfaceLight = Color(0xFFFFFFFF)
val SurfaceDark = Color(0xFF202020)
val OnSurfaceLight = Color(0xFF000000)
val OnSurfaceDark = Color(0xFFFFFFFF)

// Semantic Colors
val SuccessGreen = Color(0xFF4CAF50)
val SuccessGreenLight = Color(0xFF81C784)
val SuccessGreenDark = Color(0xFF388E3C)

val WarningOrange = Color(0xFFFF9800)
val WarningOrangeLight = Color(0xFFFFB74D)
val WarningOrangeDark = Color(0xFFF57C00)

val ErrorRed = Color(0xFFF44336)
val ErrorRedLight = Color(0xFFE57373)
val ErrorRedDark = Color(0xFFD32F2F)

val InfoBlue = Color(0xFF2196F3)
val InfoBlueLight = Color(0xFF64B5F6)
val InfoBlueDark = Color(0xFF1976D2)

// Neutral Colors
val Neutral50 = Color(0xFFFAFAFA)
val Neutral100 = Color(0xFFF5F5F5)
val Neutral200 = Color(0xFFEEEEEE)
val Neutral300 = Color(0xFFE0E0E0)
val Neutral400 = Color(0xFFBDBDBD)
val Neutral500 = Color(0xFF9E9E9E)
val Neutral600 = Color(0xFF757575)
val Neutral700 = Color(0xFF616161)
val Neutral800 = Color(0xFF424242)
val Neutral900 = Color(0xFF212121)

object SemanticColors {
    val success = SuccessGreen
    val successLight = SuccessGreenLight
    val successDark = SuccessGreenDark

    val warning = WarningOrange
    val warningLight = WarningOrangeLight
    val warningDark = WarningOrangeDark

    val error = ErrorRed
    val errorLight = ErrorRedLight
    val errorDark = ErrorRedDark

    val info = InfoBlue
    val infoLight = InfoBlueLight
    val infoDark = InfoBlueDark
}

// Additional colors for tags and categorization
val Amber = Color(0xFFFFC107) // True amber: yellow-orange
val DeepOrange = Color(0xFFFF5722)
val Yellow = Color(0xFFFFEB3B)
val Lime = Color(0xFFCDDC39)
val Teal = Color(0xFF009688)
val Cyan = Color(0xFF00BCD4)
val Indigo = Color(0xFF3F51B5)
val Purple = Color(0xFF9C27B0)
val DeepPurple = Color(0xFF673AB7)
val Pink = Color(0xFFE91E63)
val Brown = Color(0xFF795548)
val Gray = Color(0xFF9E9E9E)

/**
 * Legacy palette values. Prefer `SacramentTheme` tokens for new work.
 */
object SacramentPalette {
    // Brand accents
    val brandPurple = Color(0xFF6B4FE8)
    val brandPink = Color(0xFFFF6B9D)
    val brandOrange = Color(0xFFFF8C42)

    // Text tones
    val textStrong = Color(0xFF1A1A1A)
    val textMuted = Color(0xFF666666)

    // Surface tints
    val surfaceSunlight = Color(0xFFFFF9E6)
    val surfaceSunlightSoft = Color(0xFFFFF9F0)
    val surfaceLavender = Color(0xFFE8E6FF)
    val surfaceLavenderSoft = Color(0xFFF5F3FF)
    val surfaceRose = Color(0xFFFFE8F0)
    val surfaceRoseTint = Color(0xFFFFD6E7)
    val surfaceSky = Color(0xFFE6F9FF)
    val surfaceMint = Color(0xFFE8F5E9)
    val surfacePeach = Color(0xFFFFF4E6)
    val surfacePeachSoft = Color(0xFFFFE8D6)

    // UI utility colors
    val progressTrack = Color(0xFFE0E0E0)
    val authProviderSurface = Color(0xFFF2F2F2)
    val successAction = Color(0xFF7EE081)
    val onSuccessAction = Color(0xFF000000)

    // Onboarding accents
    val onboardingAccentPrimary = Color(0xFF5E63F2)
    val onboardingAccentSecondary = Color(0xFF5A6DE8)
    val onboardingAccentTertiary = Color(0xFF5B75E5)
    val onboardingAccentQuaternary = Color(0xFF5879DB)

    // Navigation colors - Light theme
    val navigationBarLight = SurfaceLight
    val navigationSelectedLight = brandPurple
    val navigationUnselectedLight = textMuted
    val navigationSpotlightLight = brandPurple.copy(alpha = 0.1f) // 10% brand purple for subtle indicator

    // Navigation colors - Dark theme
    val navigationBarDark = SurfaceDark
    val navigationSelectedDark = Color(0xFF8B7AFF) // Lighter purple for dark mode
    val navigationUnselectedDark = Neutral400
    val navigationSpotlightDark = navigationSelectedDark.copy(alpha = 0.3f) // 30% lighter purple for spotlight glow

    // Interactive colors - Light theme
    val switchTrackOnLight = brandPurple.copy(alpha = 0.3f) // 30% brand purple
    val switchTrackOffLight = textMuted.copy(alpha = 0.2f) // 20% muted gray
    val switchThumbOnLight = brandPurple
    val switchThumbOffLight = Color.White
    val switchIconLight = Color.White

    // Interactive colors - Dark theme
    val switchTrackOnDark = navigationSelectedDark.copy(alpha = 0.3f) // 30% lighter purple
    val switchTrackOffDark = Neutral400.copy(alpha = 0.2f) // 20% lighter gray
    val switchThumbOnDark = navigationSelectedDark
    val switchThumbOffDark = Neutral800
    val switchIconDark = Color.White
}
