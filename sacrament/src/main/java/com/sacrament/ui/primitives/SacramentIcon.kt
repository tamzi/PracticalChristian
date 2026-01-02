package com.sacrament.ui.primitives

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sacrament.ui.foundation.Bar
import com.sacrament.ui.foundation.SacramentTheme

/**
 * Icon primitive with token-based tint defaults.
 *
 * Usage:
 * `SacramentIcon(imageVector = Icons.Rounded.Star, contentDescription = "Star")`
 */
@Composable
fun SacramentIcon(
    imageVector: ImageVector,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    tint: Color = SacramentTheme.colors.text.strong,
    size: Dp = SacramentTheme.iconSizes.md,
) {
    val colorFilter = if (tint == Color.Unspecified) null else ColorFilter.tint(tint)
    Image(
        painter = rememberVectorPainter(imageVector),
        contentDescription = contentDescription,
        modifier = modifier.size(size),
        colorFilter = colorFilter,
    )
}

@Preview
@Composable
fun SacramentIconPreview() {
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        SacramentIcon(
            imageVector = Icons.Rounded.Star,
            contentDescription = "Preview icon",
        )
    }
}
