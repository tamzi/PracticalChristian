package com.sacrament.ui.components.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sacrament.ui.foundation.Bar
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.primitives.SacramentText

/**
 * Bottom navigation bar container.
 */
@Composable
fun SacramentBottomBar(
    modifier: Modifier = Modifier,
    colors: SacramentBottomBarColors = SacramentBottomBarDefaults.colors(),
    content: @Composable RowScope.() -> Unit,
) {
    val spacing = SacramentTheme.spacing
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(SacramentBottomBarDefaults.height())
            .navigationBarsPadding()
            .background(colors.container)
            .padding(horizontal = spacing.lg),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        content = content,
    )
}

@Preview(name = "Light Theme")
@Composable
private fun SacramentBottomBarLightPreview() {
    SacramentTheme(darkTheme = false, navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        SacramentBottomBar {
            SacramentText(text = "Home", style = SacramentTheme.typography.labelSmall)
            SacramentText(text = "Notes", style = SacramentTheme.typography.labelSmall)
            SacramentText(text = "Profile", style = SacramentTheme.typography.labelSmall)
        }
    }
}

@Preview(name = "Dark Theme")
@Composable
private fun SacramentBottomBarDarkPreview() {
    SacramentTheme(darkTheme = true, navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        SacramentBottomBar {
            SacramentText(text = "Home", style = SacramentTheme.typography.labelSmall)
            SacramentText(text = "Notes", style = SacramentTheme.typography.labelSmall)
            SacramentText(text = "Profile", style = SacramentTheme.typography.labelSmall)
        }
    }
}
