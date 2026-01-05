package com.sacrament.ui.components.surface

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sacrament.ui.foundation.Bar
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.primitives.SacramentText

/**
 * Card surface for grouped content.
 */
@Composable
fun SacramentCard(
    content: @Composable () -> Unit,
    onClick: (() -> Unit)? = null,
    enabled: Boolean = true,
    colors: SacramentCardColors = SacramentCardDefaults.colors(),
    contentPadding: PaddingValues = PaddingValues(0.dp),
    modifier: Modifier = Modifier,
) {
    val shape = SacramentCardDefaults.shape()
    val border = BorderStroke(SacramentCardDefaults.borderWidth(), colors.border)
    val decoratedModifier = modifier
        .shadow(SacramentCardDefaults.elevation(enabled), shape)
        .clip(shape)
        .background(colors.container, shape)
        .border(border, shape)
        .then(
            if (onClick != null) {
                Modifier.clickable(enabled = enabled, onClick = onClick)
            } else {
                Modifier
            }
        )
        .padding(contentPadding)

    Box(modifier = decoratedModifier) {
        content()
    }
}

@Preview
@Composable
private fun SacramentCardPreview() {
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        SacramentCard(modifier = Modifier.padding(16.dp), contentPadding = PaddingValues(16.dp)) {
            SacramentText(text = "Card content")
        }
    }
}
