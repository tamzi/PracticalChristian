package com.sacrament.ui.components.input

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sacrament.ui.foundation.SacramentTheme

/**
 * Sizes for radio controls.
 */
enum class SacramentRadioSize {
    Small,
    Medium,
}

/**
 * Colors for radio states.
 */
@Immutable
data class SacramentRadioColors(
    val selected: Color,
    val unselected: Color,
    val disabledSelected: Color,
    val disabledUnselected: Color,
)

/**
 * Defaults for Sacrament radio buttons.
 */
object SacramentRadioDefaults {
    @Composable
    fun colors(): SacramentRadioColors {
        val colors = SacramentTheme.colors
        return SacramentRadioColors(
            selected = colors.brand.primary,
            unselected = colors.text.muted,
            disabledSelected = colors.text.muted,
            disabledUnselected = colors.text.muted.copy(alpha = 0.4f),
        )
    }

    @Composable
    fun size(size: SacramentRadioSize): Dp = when (size) {
        SacramentRadioSize.Small -> 18.dp
        SacramentRadioSize.Medium -> 22.dp
    }

    @Composable
    fun dotSize(size: SacramentRadioSize): Dp = when (size) {
        SacramentRadioSize.Small -> 8.dp
        SacramentRadioSize.Medium -> 10.dp
    }

    fun borderWidth(): Dp = 1.5.dp
}
