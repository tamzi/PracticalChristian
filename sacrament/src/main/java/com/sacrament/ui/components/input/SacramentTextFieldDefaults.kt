package com.sacrament.ui.components.input

import androidx.compose.foundation.layout.PaddingValues
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
 * Text field size variants.
 */
enum class SacramentTextFieldSize {
    Small,
    Medium,
    Large,
}

/**
 * Colors for text fields.
 */
@Immutable
data class SacramentTextFieldColors(
    val background: Color,
    val border: Color,
    val focusedBorder: Color,
    val errorBorder: Color,
    val text: Color,
    val placeholder: Color,
    val icon: Color,
    val disabledBackground: Color,
    val disabledBorder: Color,
    val disabledText: Color,
    val disabledPlaceholder: Color,
)

/**
 * Defaults for Sacrament text fields.
 */
object SacramentTextFieldDefaults {
    @Composable
    fun colors(): SacramentTextFieldColors {
        val colors = SacramentTheme.colors
        return SacramentTextFieldColors(
            background = colors.surfaces.surface,
            border = colors.text.muted.copy(alpha = 0.4f),
            focusedBorder = colors.brand.primary,
            errorBorder = colors.semantic.error,
            text = colors.text.strong,
            placeholder = colors.text.muted,
            icon = colors.text.muted,
            disabledBackground = colors.surfaces.surfaceVariant,
            disabledBorder = colors.text.muted.copy(alpha = 0.2f),
            disabledText = colors.text.muted,
            disabledPlaceholder = colors.text.muted.copy(alpha = 0.6f),
        )
    }

    @Composable
    fun contentPadding(size: SacramentTextFieldSize): PaddingValues {
        val spacing = SacramentTheme.spacing
        return when (size) {
            SacramentTextFieldSize.Small -> PaddingValues(horizontal = spacing.md, vertical = spacing.xs)
            SacramentTextFieldSize.Medium -> PaddingValues(horizontal = spacing.lg, vertical = spacing.sm)
            SacramentTextFieldSize.Large -> PaddingValues(horizontal = spacing.xl, vertical = spacing.md)
        }
    }

    @Composable
    fun minHeight(size: SacramentTextFieldSize): Dp = when (size) {
        SacramentTextFieldSize.Small -> 40.dp
        SacramentTextFieldSize.Medium -> 48.dp
        SacramentTextFieldSize.Large -> 56.dp
    }

    @Composable
    fun shape(): Shape = SacramentShapeDefaults.rounded(SacramentRadiusSize.LG)

    fun borderWidth(): Dp = 1.dp
}
