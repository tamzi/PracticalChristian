package com.sacrament.ui.primitives

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sacrament.ui.foundation.Bar
import com.sacrament.ui.foundation.SacramentTheme

/**
 * Tone variants for the divider to match surface or background contexts.
 */
enum class SacramentDividerTone {
    Surface,
    Background,
}

/**
 * Divider primitive for section separation.
 *
 * Usage:
 * `SacramentDivider(tone = SacramentDividerTone.Surface)`
 */
@Composable
fun SacramentDivider(
    modifier: Modifier = Modifier,
    tone: SacramentDividerTone = SacramentDividerTone.Surface,
    thickness: Dp = 1.dp,
) {
    val color = when (tone) {
        SacramentDividerTone.Surface -> SacramentDividerDefaults.surfaceColor()
        SacramentDividerTone.Background -> SacramentDividerDefaults.backgroundColor()
    }
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(thickness)
            .background(color),
    )
}

@Preview
@Composable
fun SacramentDividerPreview() {
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        SacramentDivider()
    }
}
