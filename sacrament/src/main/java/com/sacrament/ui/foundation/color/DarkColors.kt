package com.sacrament.ui.foundation.color

import androidx.compose.ui.graphics.Color

internal val DarkSacramentColors = LightSacramentColors.copy(
    text = SacramentTextColors(
        strong = Neutral50,
        muted = Neutral400,
        inverse = Color.Black,
        onBrand = Color.White,
    ),
    surfaces = LightSacramentColors.surfaces.copy(
        background = BackgroundDark,
        surface = SurfaceDark,
        surfaceVariant = Neutral800,
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
        switchIconOff = SacramentPalette.switchIconDark,
    ),
    // Tags inherit from light theme (same vibrant colors work well on dark backgrounds)
)
