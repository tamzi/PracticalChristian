package com.sacrament.ui.foundation.color

import androidx.compose.ui.graphics.Color

internal val DarkSacramentColors = LightSacramentColors.copy(
    text = SacramentTextColors(
        strong = Neutral50,
        muted = Neutral400,
        inverse = Color.Black,
        onBrand = Color.White,
    ),
    surfaces = SacramentSurfaceColors(
        background = BackgroundDark,
        surface = SurfaceDark,
        surfaceVariant = Neutral800,
        sunlight = SacramentPalette.surfaceSunlightDark,
        sunlightSoft = SacramentPalette.surfaceSunlightSoftDark,
        lavender = SacramentPalette.surfaceLavenderDark,
        lavenderSoft = SacramentPalette.surfaceLavenderSoftDark,
        rose = SacramentPalette.surfaceRoseDark,
        roseTint = SacramentPalette.surfaceRoseTintDark,
        sky = SacramentPalette.surfaceSkyDark,
        mint = SacramentPalette.surfaceMintDark,
        peach = SacramentPalette.surfacePeachDark,
        peachSoft = SacramentPalette.surfacePeachSoftDark,
    ),
    semantic = SacramentSemanticColors(
        success = SuccessGreenLight, // Lighter variant for better visibility on dark backgrounds
        successLight = SuccessGreen,
        successDark = SuccessGreenDark,
        warning = WarningOrangeLight, // Lighter variant for better visibility on dark backgrounds
        warningLight = WarningOrange,
        warningDark = WarningOrangeDark,
        error = ErrorRedLight, // Lighter variant for better visibility on dark backgrounds
        errorLight = ErrorRed,
        errorDark = ErrorRedDark,
        info = InfoBlueLight, // Lighter variant for better visibility on dark backgrounds
        infoLight = InfoBlue,
        infoDark = InfoBlueDark,
    ),
    utilities = SacramentUtilityColors(
        progressTrack = Neutral700, // Darker for dark mode visibility
        authProviderSurface = Neutral800, // Darker surface for dark mode
        successAction = SuccessGreenLight, // Lighter for visibility
        onSuccessAction = Color.Black,
    ),
    navigation = SacramentNavigationColors(
        barBackground = SacramentPalette.navigationBarDark,
        selectedIndicator = SacramentPalette.navigationSelectedDark,
        selectedIcon = SacramentPalette.navigationSelectedDark,
        unselectedIcon = SacramentPalette.navigationUnselectedDark,
        spotlightGlow = SacramentPalette.navigationSpotlightDark,
    ),
    interactive = SacramentInteractiveColors(
        switchTrackOn = SacramentPalette.switchTrackOnDark,
        switchTrackOff = SacramentPalette.switchTrackOffDark,
        switchThumbOn = SacramentPalette.switchThumbOnDark,
        switchThumbOff = SacramentPalette.switchThumbOffDark,
        switchIconOn = SacramentPalette.switchThumbOffDark,
        switchIconOff = SacramentPalette.switchIconDark,
    ),
    // Tags inherit from light theme (same vibrant colors work well on dark backgrounds)
)
