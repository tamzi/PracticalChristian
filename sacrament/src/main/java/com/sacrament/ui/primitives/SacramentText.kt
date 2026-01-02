package com.sacrament.ui.primitives

import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.sacrament.ui.foundation.Bar
import com.sacrament.ui.foundation.SacramentTheme

/**
 * Typography-aware text primitive backed by Sacrament tokens.
 *
 * Usage:
 * `SacramentText(text = "Label")`
 */
@Composable
fun SacramentText(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = SacramentTheme.typography.bodyMedium,
    color: Color = SacramentTheme.colors.text.strong,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
) {
    BasicText(
        text = text,
        modifier = modifier,
        style = style.copy(color = color),
        maxLines = maxLines,
        overflow = overflow,
        softWrap = softWrap,
    )
}

@Preview
@Composable
fun SacramentTextPreview() {
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        SacramentText(text = "Sacrament text")
    }
}
