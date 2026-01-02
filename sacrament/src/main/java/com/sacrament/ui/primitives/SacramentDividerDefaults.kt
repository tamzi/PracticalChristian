package com.sacrament.ui.primitives

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.sacrament.ui.foundation.SacramentTheme

/**
 * Default colors for dividers in different contexts.
 */
object SacramentDividerDefaults {
    /**
     * Divider color intended for surfaces.
     */
    @Composable
    fun surfaceColor(): Color = SacramentTheme.colors.text.strong.copy(alpha = 0.2f)

    /**
     * Divider color intended for backgrounds.
     */
    @Composable
    fun backgroundColor(): Color = SacramentTheme.colors.text.strong.copy(alpha = 0.2f)
}
