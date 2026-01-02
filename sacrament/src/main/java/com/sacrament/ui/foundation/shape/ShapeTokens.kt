package com.sacrament.ui.foundation.shape

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Corner radius scale.
 *
 * Usage:
 * `shape = RoundedCornerShape(SacramentTheme.radii.md)`
 */
@Immutable
data class SacramentRadii(
    val none: Dp = 0.dp,
    val xs: Dp = 4.dp,
    val sm: Dp = 8.dp,
    val md: Dp = 12.dp,
    val lg: Dp = 16.dp,
    val xl: Dp = 24.dp,
)
