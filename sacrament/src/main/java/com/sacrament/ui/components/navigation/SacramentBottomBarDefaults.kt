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
    val selectedIndicator: Color,
    val spotlightGlow: Color,
)

/**
 * Defaults for Sacrament bottom bar.
 */
object SacramentBottomBarDefaults {
    @Composable
    fun colors(): SacramentBottomBarColors {
        val colors = SacramentTheme.colors
        return SacramentBottomBarColors(
            container = colors.navigation.barBackground,
            content = colors.navigation.unselectedIcon,
            selectedIndicator = colors.navigation.selectedIndicator,
            spotlightGlow = colors.navigation.spotlightGlow,
        )
    }

    fun height(): Dp = 64.dp

    fun itemCount(): Int = 3

    fun indicatorHeight(): Dp = 40.dp

    fun spotlightHeight(): Dp = 56.dp
}
