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
)
