package com.sacrament.ui.components.input

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sacrament.ui.foundation.SacramentTheme

/**
 * Sizes for switch control.
 */
enum class SacramentSwitchSize {
    Small,
    Medium,
}

/**
 * Colors for switch states.
 */
@Immutable
data class SacramentSwitchColors(
    val checkedTrack: Color,
    val uncheckedTrack: Color,
    val checkedThumb: Color,
    val uncheckedThumb: Color,
    val disabledTrack: Color,
    val disabledThumb: Color,
)

/**
 * Defaults for Sacrament switches.
 */
object SacramentSwitchDefaults {
    @Composable
    fun colors(): SacramentSwitchColors {
        val colors = SacramentTheme.colors
        return SacramentSwitchColors(
            checkedTrack = colors.interactive.switchTrackOn,
            uncheckedTrack = colors.interactive.switchTrackOff,
            checkedThumb = colors.interactive.switchThumbOn,
            uncheckedThumb = colors.interactive.switchThumbOff,
            disabledTrack = colors.text.muted.copy(alpha = 0.15f),
            disabledThumb = colors.text.muted.copy(alpha = 0.4f),
        )
    }

    @Composable
    fun width(size: SacramentSwitchSize): Dp = when (size) {
        SacramentSwitchSize.Small -> 36.dp
        SacramentSwitchSize.Medium -> 44.dp
    }

    @Composable
    fun height(size: SacramentSwitchSize): Dp = when (size) {
        SacramentSwitchSize.Small -> 20.dp
        SacramentSwitchSize.Medium -> 24.dp
    }

    @Composable
    fun thumbSize(size: SacramentSwitchSize): Dp = when (size) {
        SacramentSwitchSize.Small -> 14.dp
        SacramentSwitchSize.Medium -> 18.dp
    }

    fun trackPadding(): Dp = 3.dp
}
