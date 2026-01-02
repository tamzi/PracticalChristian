package com.sacrament.ui.foundation.layout

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Spacing scale used for layout and padding.
 *
 * Usage:
 * `Modifier.padding(SacramentTheme.spacing.padding16)`
 */
@Immutable
data class SacramentSpacing(
    val padding1: Dp = 1.dp,
    val padding2: Dp = 2.dp,
    val padding4: Dp = 4.dp,
    val padding6: Dp = 6.dp,
    val padding8: Dp = 8.dp,
    val padding10: Dp = 10.dp,
    val padding12: Dp = 12.dp,
    val padding14: Dp = 14.dp,
    val padding16: Dp = 16.dp,
    val padding18: Dp = 18.dp,
    val padding20: Dp = 20.dp,
    val padding22: Dp = 22.dp,
    val padding24: Dp = 24.dp,
    val padding32: Dp = 32.dp,
    val padding36: Dp = 36.dp,
    val padding40: Dp = 40.dp,
    val padding48: Dp = 48.dp,
    val padding64: Dp = 64.dp,
    val padding200: Dp = 200.dp,
) {
    val xxs: Dp get() = padding2
    val xs: Dp get() = padding4
    val sm: Dp get() = padding8
    val md: Dp get() = padding12
    val lg: Dp get() = padding16
    val xl: Dp get() = padding24
    val xxl: Dp get() = padding32
    val xxxl: Dp get() = padding40
}
