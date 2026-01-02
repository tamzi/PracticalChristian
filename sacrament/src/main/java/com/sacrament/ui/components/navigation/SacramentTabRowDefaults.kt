package com.sacrament.ui.components.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sacrament.ui.foundation.SacramentTheme

/**
 * Colors for tabs.
 */
@Immutable
data class SacramentTabRowColors(
    val container: Color,
    val activeContent: Color,
    val inactiveContent: Color,
    val indicator: Color,
)

/**
 * Defaults for Sacrament tab rows.
 */
object SacramentTabRowDefaults {
    @Composable
    fun colors(): SacramentTabRowColors {
        val colors = SacramentTheme.colors
        return SacramentTabRowColors(
            container = colors.surfaces.surface,
            activeContent = colors.brand.primary,
            inactiveContent = colors.text.muted,
            indicator = colors.brand.primary,
        )
    }

    fun indicatorHeight(): Dp = 2.dp
    fun minTabHeight(): Dp = 40.dp
}
