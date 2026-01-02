package com.sacrament.ui.foundation.color

import androidx.compose.ui.graphics.Color

internal val LightSacramentColors = SacramentColorTokens(
    brand = SacramentBrandColors(
        primary = SacramentPalette.brandPurple,
        secondary = SacramentPalette.brandPink,
        tertiary = SacramentPalette.brandOrange,
    ),
    text = SacramentTextColors(
        strong = SacramentPalette.textStrong,
        muted = SacramentPalette.textMuted,
        inverse = Color.White,
        onBrand = Color.White,
    ),
    surfaces = SacramentSurfaceColors(
        background = BackgroundLight,
        surface = SurfaceLight,
        surfaceVariant = Neutral100,
        sunlight = SacramentPalette.surfaceSunlight,
        sunlightSoft = SacramentPalette.surfaceSunlightSoft,
        lavender = SacramentPalette.surfaceLavender,
        lavenderSoft = SacramentPalette.surfaceLavenderSoft,
        rose = SacramentPalette.surfaceRose,
        roseTint = SacramentPalette.surfaceRoseTint,
        sky = SacramentPalette.surfaceSky,
        mint = SacramentPalette.surfaceMint,
        peach = SacramentPalette.surfacePeach,
        peachSoft = SacramentPalette.surfacePeachSoft,
    ),
    semantic = SacramentSemanticColors(
        success = SuccessGreen,
        successLight = SuccessGreenLight,
        successDark = SuccessGreenDark,
        warning = WarningOrange,
        warningLight = WarningOrangeLight,
        warningDark = WarningOrangeDark,
        error = ErrorRed,
        errorLight = ErrorRedLight,
        errorDark = ErrorRedDark,
        info = InfoBlue,
        infoLight = InfoBlueLight,
        infoDark = InfoBlueDark,
    ),
    utilities = SacramentUtilityColors(
        progressTrack = SacramentPalette.progressTrack,
        authProviderSurface = SacramentPalette.authProviderSurface,
        successAction = SacramentPalette.successAction,
        onSuccessAction = SacramentPalette.onSuccessAction,
    ),
    onboarding = SacramentOnboardingColors(
        accentPrimary = SacramentPalette.onboardingAccentPrimary,
        accentSecondary = SacramentPalette.onboardingAccentSecondary,
        accentTertiary = SacramentPalette.onboardingAccentTertiary,
        accentQuaternary = SacramentPalette.onboardingAccentQuaternary,
    ),
)
