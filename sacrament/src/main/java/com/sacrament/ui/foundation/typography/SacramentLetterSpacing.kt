package com.sacrament.ui.foundation.typography

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

/**
 * Letter spacing tokens for typography.
 *
 * These values control the horizontal spacing between characters.
 * Used to adjust readability and visual hierarchy.
 */
@Immutable
data class SacramentLetterSpacing(
    val none: TextUnit = 0.sp,
    val tight: TextUnit = (-0.5).sp,
    val normal: TextUnit = 0.sp,
    val wide: TextUnit = 0.5.sp,
    val wider: TextUnit = 1.sp,
    val widest: TextUnit = 1.5.sp,
)
