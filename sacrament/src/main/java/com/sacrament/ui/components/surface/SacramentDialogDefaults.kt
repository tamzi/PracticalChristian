package com.sacrament.ui.components.surface

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.foundation.shape.SacramentRadiusSize
import com.sacrament.ui.foundation.shape.SacramentShapeDefaults

/**
 * Colors for dialogs.
 */
@Immutable
data class SacramentDialogColors(
    val container: Color,
    val scrim: Color,
)

/**
 * Defaults for Sacrament dialogs.
 */
object SacramentDialogDefaults {
    @Composable
    fun colors(): SacramentDialogColors {
        val colors = SacramentTheme.colors
        return SacramentDialogColors(
            container = colors.surfaces.surface,
            scrim = colors.text.strong.copy(alpha = 0.4f),
        )
    }

    @Composable
    fun shape(): Shape = SacramentShapeDefaults.rounded(SacramentRadiusSize.LG)
}
