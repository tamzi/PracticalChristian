package com.sacrament.ui.components.surface

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sacrament.ui.foundation.Bar
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.primitives.SacramentText
import androidx.compose.foundation.layout.padding

/**
 * Sheet surface for modal and bottom sheet content.
 */
@Composable
fun SacramentSheet(
    colors: SacramentSheetColors = SacramentSheetDefaults.colors(),
    shape: Shape = SacramentSheetDefaults.shape(),
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Box(
        modifier = modifier
            .clip(shape)
            .background(colors.container, shape),
    ) {
        content()
    }
}

@Preview
@Composable
private fun SacramentSheetPreview() {
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        SacramentSheet(modifier = Modifier.padding(16.dp)) {
            SacramentText(text = "Sheet content", modifier = Modifier.padding(16.dp))
        }
    }
}
