package com.sacrament.ui.components.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sacrament.ui.foundation.SacramentTheme

/**
 * Colors for navigation rail.
 */
@Immutable
data class SacramentNavigationRailColors(
    val container: Color,
    val activeContent: Color,
    val inactiveContent: Color,
)

/**
 * Defaults for Sacrament navigation rail.
 */
object SacramentNavigationRailDefaults {
    @Composable
    fun colors(): SacramentNavigationRailColors {
        val colors = SacramentTheme.colors
        return SacramentNavigationRailColors(
            container = colors.surfaces.surface,
            activeContent = colors.brand.primary,
            inactiveContent = colors.text.muted,
        )
    }

    fun width(): Dp = 80.dp
}
