package com.sacrament.ui.components.surface

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.foundation.shape.SacramentRadiusSize
import com.sacrament.ui.foundation.shape.SacramentShapeDefaults

/**
 * Colors for cards.
 */
@Immutable
data class SacramentCardColors(
    val container: Color,
    val border: Color,
)

/**
 * Defaults for Sacrament cards.
 */
object SacramentCardDefaults {
    @Composable
    fun colors(): SacramentCardColors {
        val colors = SacramentTheme.colors
        return SacramentCardColors(
            container = colors.surfaces.surface,
            border = colors.text.muted.copy(alpha = 0.15f),
        )
    }

    @Composable
    fun shape(): Shape = SacramentShapeDefaults.rounded(SacramentRadiusSize.LG)

    @Composable
    fun elevation(enabled: Boolean): Dp {
        val elevation = SacramentTheme.elevation
        return if (enabled) elevation.level2 else elevation.level0
    }

    fun borderWidth(): Dp = 1.dp
}
