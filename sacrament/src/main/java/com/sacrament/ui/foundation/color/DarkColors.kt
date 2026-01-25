package com.sacrament.ui.foundation.color

import androidx.compose.ui.graphics.Color

internal val DarkSacramentColors = LightSacramentColors.copy(
    text = SacramentTextColors(
        strong = Neutral50,
        muted = Neutral400,
        inverse = Black,
        onBrand = White,
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
        success = SuccessGreen, // Base success color; use successLight for brighter option on dark backgrounds
        successLight = SuccessGreenLight,
        successDark = SuccessGreenDark,
        warning = WarningOrange, // Base warning color; use warningLight for brighter option on dark backgrounds
        warningLight = WarningOrangeLight,
        warningDark = WarningOrangeDark,
        error = ErrorRed, // Base error color; use errorLight for brighter option on dark backgrounds
        errorLight = ErrorRedLight,
        errorDark = ErrorRedDark,
        info = InfoBlue, // Base info color; use infoLight for brighter option on dark backgrounds
        infoLight = InfoBlueLight,
        infoDark = InfoBlueDark,
    ),
    utilities = SacramentUtilityColors(
        progressTrack = Neutral700, // Darker for dark mode visibility
        authProviderSurface = Neutral800, // Darker surface for dark mode
        successAction = SuccessGreen, // Base success for action buttons
        onSuccessAction = Black,
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
        switchIconOn = SacramentPalette.switchIconDark,
        switchIconOff = SacramentPalette.navigationUnselectedDark,
    ),
    // Tags inherit from light theme (same vibrant colors work well on dark backgrounds)
)
