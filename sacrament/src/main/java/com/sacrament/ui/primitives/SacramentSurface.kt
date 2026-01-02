package com.sacrament.ui.primitives

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sacrament.ui.foundation.Bar
import com.sacrament.ui.foundation.SacramentTheme

/**
 * Surface primitive for background containers using Sacrament tokens.
 *
 * Usage:
 * `SacramentSurface { SacramentText(text = "Content") }`
 */
@Composable
fun SacramentSurface(
    modifier: Modifier = Modifier,
    color: Color = SacramentTheme.colors.surfaces.surface,
    shape: Shape = RoundedCornerShape(SacramentTheme.radii.md),
    border: BorderStroke? = null,
    content: @Composable () -> Unit,
) {
    val baseModifier = modifier
        .clip(shape)
        .background(color)

    val decoratedModifier = if (border != null) {
        baseModifier.border(border, shape)
    } else {
        baseModifier
    }

    Box(modifier = decoratedModifier) {
        content()
    }
}

@Preview
@Composable
fun SacramentSurfacePreview() {
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        SacramentSurface(
            modifier = Modifier.padding(16.dp),
            border = BorderStroke(1.dp, SacramentTheme.colors.text.muted),
        ) {
            SacramentText(
                text = "Surface content",
                modifier = Modifier.padding(12.dp),
            )
        }
    }
}
