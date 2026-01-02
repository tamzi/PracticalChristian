package com.sacrament.ui.components.surface

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import com.sacrament.ui.foundation.SacramentTheme

/**
 * Colors for sheets.
 */
@Immutable
data class SacramentSheetColors(
    val container: Color,
)

/**
 * Defaults for Sacrament sheets.
 */
object SacramentSheetDefaults {
    @Composable
    fun colors(): SacramentSheetColors {
        val colors = SacramentTheme.colors
        return SacramentSheetColors(container = colors.surfaces.surface)
    }

    @Composable
    fun shape(): Shape = RoundedCornerShape(
        topStart = SacramentTheme.radii.xl,
        topEnd = SacramentTheme.radii.xl,
    )
}
