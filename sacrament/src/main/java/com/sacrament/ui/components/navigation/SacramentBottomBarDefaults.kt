package com.sacrament.ui.components.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sacrament.ui.foundation.SacramentTheme

/**
 * Colors for bottom bar.
 */
@Immutable
data class SacramentBottomBarColors(
    val container: Color,
    val content: Color,
)

/**
 * Defaults for Sacrament bottom bar.
 */
object SacramentBottomBarDefaults {
    @Composable
    fun colors(): SacramentBottomBarColors {
        val colors = SacramentTheme.colors
        return SacramentBottomBarColors(
            container = colors.surfaces.surface,
            content = colors.text.muted,
        )
    }

    fun height(): Dp = 64.dp
}
