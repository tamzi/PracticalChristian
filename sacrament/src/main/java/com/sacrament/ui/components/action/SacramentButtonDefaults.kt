package com.sacrament.ui.components.action

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.foundation.shape.SacramentRadiusSize
import com.sacrament.ui.foundation.shape.SacramentShapeDefaults

/**
 * Button size variants.
 */
enum class SacramentButtonSize {
    Small,
    Medium,
    Large,
}

/**
 * Colors used by Sacrament action components.
 */
@Immutable
data class SacramentButtonColors(
    val container: Color,
    val content: Color,
    val border: Color,
    val pressedContainer: Color,
    val pressedBorder: Color,
    val disabledContainer: Color,
    val disabledContent: Color,
    val disabledBorder: Color,
)

/**
 * Defaults and helpers for Sacrament buttons.
 */
object SacramentButtonDefaults {
    @Composable
    fun colors(
        variant: SacramentButtonVariant = SacramentButtonVariant.Filled,
        tone: SacramentButtonTone = SacramentButtonTone.Brand,
    ): SacramentButtonColors {
        val colors = SacramentTheme.colors
        val base = SacramentButtonTokens.toneColor(tone, colors)
        val onBase = SacramentButtonTokens.onToneColor(colors)
        val disabledContainer = colors.surfaces.surfaceVariant
        val disabledContent = colors.text.muted
        val disabledBorder = colors.text.muted.copy(alpha = SacramentButtonTokens.DisabledBorderAlpha)

        return when (variant) {
            SacramentButtonVariant.Filled -> SacramentButtonColors(
                container = base,
                content = onBase,
                border = Color.Unspecified,
                pressedContainer = base.copy(alpha = SacramentButtonTokens.FilledPressedAlpha),
                pressedBorder = Color.Unspecified,
                disabledContainer = disabledContainer,
                disabledContent = disabledContent,
                disabledBorder = Color.Unspecified,
            )
            SacramentButtonVariant.Outlined -> SacramentButtonColors(
                container = Color.Transparent,
                content = base,
                border = base,
                pressedContainer = base.copy(alpha = SacramentButtonTokens.GhostPressedAlpha),
                pressedBorder = base,
                disabledContainer = Color.Transparent,
                disabledContent = disabledContent,
                disabledBorder = disabledBorder,
            )
            SacramentButtonVariant.Ghost -> SacramentButtonColors(
                container = Color.Transparent,
                content = base,
                border = Color.Unspecified,
                pressedContainer = base.copy(alpha = SacramentButtonTokens.GhostPressedAlpha),
                pressedBorder = Color.Unspecified,
                disabledContainer = Color.Transparent,
                disabledContent = disabledContent,
                disabledBorder = Color.Unspecified,
            )
        }
    }

    @Composable
    fun height(size: SacramentButtonSize): Dp = when (size) {
        SacramentButtonSize.Small -> 36.dp
        SacramentButtonSize.Medium -> 44.dp
        SacramentButtonSize.Large -> 52.dp
    }

    @Composable
    fun contentPadding(size: SacramentButtonSize): PaddingValues {
        val spacing = SacramentTheme.spacing
        return when (size) {
            SacramentButtonSize.Small -> PaddingValues(horizontal = spacing.md, vertical = spacing.xs)
            SacramentButtonSize.Medium -> PaddingValues(horizontal = spacing.lg, vertical = spacing.sm)
            SacramentButtonSize.Large -> PaddingValues(horizontal = spacing.xl, vertical = spacing.md)
        }
    }

    @Composable
    fun textStyle(size: SacramentButtonSize): TextStyle {
        val typography = SacramentTheme.typography
        return when (size) {
            SacramentButtonSize.Small -> typography.labelMedium
            SacramentButtonSize.Medium -> typography.labelLarge
            SacramentButtonSize.Large -> typography.labelLarge
        }
    }

    @Composable
    fun shape(size: SacramentButtonSize): Shape {
        return when (size) {
            SacramentButtonSize.Small -> SacramentShapeDefaults.rounded(SacramentRadiusSize.MD)
            SacramentButtonSize.Medium -> SacramentShapeDefaults.rounded(SacramentRadiusSize.LG)
            SacramentButtonSize.Large -> SacramentShapeDefaults.rounded(SacramentRadiusSize.LG)
        }
    }

    @Composable
    fun iconSpacing(size: SacramentButtonSize): Dp {
        val spacing = SacramentTheme.spacing
        return when (size) {
            SacramentButtonSize.Small -> spacing.xs
            SacramentButtonSize.Medium -> spacing.sm
            SacramentButtonSize.Large -> spacing.sm
        }
    }

    @Composable
    fun iconSize(size: SacramentButtonSize): Dp {
        val icons = SacramentTheme.iconSizes
        return when (size) {
            SacramentButtonSize.Small -> icons.sm
            SacramentButtonSize.Medium -> icons.md
            SacramentButtonSize.Large -> icons.md
        }
    }

    fun borderWidth(): Dp = 1.dp

    @Composable
    fun elevation(enabled: Boolean, pressed: Boolean = false): Dp {
        val elevation = SacramentTheme.elevation
        return when {
            !enabled -> elevation.level0
            pressed -> elevation.level1
            else -> elevation.level2
        }
    }
}
