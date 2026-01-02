package com.sacrament.ui.components.action

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.foundation.shape.SacramentRadiusSize
import com.sacrament.ui.foundation.shape.SacramentShapeDefaults

/**
 * Floating action button sizes.
 */
enum class SacramentFabSize {
    Small,
    Medium,
    Large,
}

/**
 * Defaults for Sacrament floating action buttons.
 */
object SacramentFabDefaults {
    @Composable
    fun height(size: SacramentFabSize): Dp = when (size) {
        SacramentFabSize.Small -> 40.dp
        SacramentFabSize.Medium -> 56.dp
        SacramentFabSize.Large -> 64.dp
    }

    @Composable
    fun iconSize(size: SacramentFabSize): Dp {
        val icons = SacramentTheme.iconSizes
        return when (size) {
            SacramentFabSize.Small -> icons.sm
            SacramentFabSize.Medium -> icons.md
            SacramentFabSize.Large -> icons.lg
        }
    }

    @Composable
    fun shape(extended: Boolean): Shape {
        return if (extended) {
            SacramentShapeDefaults.rounded(SacramentRadiusSize.XL)
        } else {
            CircleShape
        }
    }

    @Composable
    fun contentPadding(size: SacramentFabSize): PaddingValues {
        val spacing = SacramentTheme.spacing
        return when (size) {
            SacramentFabSize.Small -> PaddingValues(horizontal = spacing.md)
            SacramentFabSize.Medium -> PaddingValues(horizontal = spacing.lg)
            SacramentFabSize.Large -> PaddingValues(horizontal = spacing.xl)
        }
    }

    @Composable
    fun elevation(enabled: Boolean): Dp {
        val elevation = SacramentTheme.elevation
        return if (enabled) elevation.level3 else elevation.level0
    }
}
