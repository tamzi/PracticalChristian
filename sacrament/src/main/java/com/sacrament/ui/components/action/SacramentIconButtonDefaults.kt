package com.sacrament.ui.components.action

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sacrament.ui.foundation.SacramentTheme

/**
 * Icon button size variants.
 */
enum class SacramentIconButtonSize {
    Small,
    Medium,
    Large,
}

/**
 * Defaults for Sacrament icon buttons.
 */
object SacramentIconButtonDefaults {
    @Composable
    fun containerSize(size: SacramentIconButtonSize): Dp = when (size) {
        SacramentIconButtonSize.Small -> 32.dp
        SacramentIconButtonSize.Medium -> 40.dp
        SacramentIconButtonSize.Large -> 48.dp
    }

    @Composable
    fun iconSize(size: SacramentIconButtonSize): Dp {
        val icons = SacramentTheme.iconSizes
        return when (size) {
            SacramentIconButtonSize.Small -> icons.sm
            SacramentIconButtonSize.Medium -> icons.md
            SacramentIconButtonSize.Large -> icons.lg
        }
    }

    fun shape(): Shape = CircleShape
}
