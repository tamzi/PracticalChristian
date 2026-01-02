package com.sacrament.ui.components.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sacrament.ui.foundation.SacramentTheme

/**
 * Colors for top app bar.
 */
@Immutable
data class SacramentTopAppBarColors(
    val container: Color,
    val content: Color,
)

/**
 * Defaults for Sacrament top app bar.
 */
object SacramentTopAppBarDefaults {
    @Composable
    fun colors(): SacramentTopAppBarColors {
        val colors = SacramentTheme.colors
        return SacramentTopAppBarColors(
            container = colors.surfaces.surface,
            content = colors.text.strong,
        )
    }

    fun height(): Dp = 56.dp
}
