package com.sacrament.ui.components.input

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.foundation.shape.SacramentRadiusSize
import com.sacrament.ui.foundation.shape.SacramentShapeDefaults

/**
 * Sizes for checkbox controls.
 */
enum class SacramentCheckboxSize {
    Small,
    Medium,
}

/**
 * Colors for checkbox states.
 */
@Immutable
data class SacramentCheckboxColors(
    val checkedBackground: Color,
    val checkedBorder: Color,
    val uncheckedBorder: Color,
    val checkmark: Color,
    val disabledBackground: Color,
    val disabledBorder: Color,
    val disabledCheckmark: Color,
)

/**
 * Defaults for Sacrament checkboxes.
 */
object SacramentCheckboxDefaults {
    @Composable
    fun colors(): SacramentCheckboxColors {
        val colors = SacramentTheme.colors
        return SacramentCheckboxColors(
            checkedBackground = colors.brand.primary,
            checkedBorder = colors.brand.primary,
            uncheckedBorder = colors.text.muted,
            checkmark = colors.text.onBrand,
            disabledBackground = colors.surfaces.surfaceVariant,
            disabledBorder = colors.text.muted.copy(alpha = 0.4f),
            disabledCheckmark = colors.text.muted,
        )
    }

    fun borderWidth(): Dp = 1.5.dp

    @Composable
    fun size(size: SacramentCheckboxSize): Dp = when (size) {
        SacramentCheckboxSize.Small -> 18.dp
        SacramentCheckboxSize.Medium -> 22.dp
    }

    @Composable
    fun shape(): Shape = SacramentShapeDefaults.rounded(SacramentRadiusSize.SM)
}
