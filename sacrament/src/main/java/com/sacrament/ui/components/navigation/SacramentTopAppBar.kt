package com.sacrament.ui.components.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sacrament.ui.foundation.Bar
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.primitives.SacramentText

/**
 * Top app bar with optional navigation icon and actions.
 */
@Composable
fun SacramentTopAppBar(
    title: @Composable () -> Unit,
    navigationIcon: @Composable (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {},
    colors: SacramentTopAppBarColors = SacramentTopAppBarDefaults.colors(),
    modifier: Modifier = Modifier,
) {
    val spacing = SacramentTheme.spacing

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(SacramentTopAppBarDefaults.height())
            .background(colors.container)
            .padding(horizontal = spacing.lg),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(spacing.sm),
    ) {
        if (navigationIcon != null) {
            Box(modifier = Modifier.padding(end = spacing.xs)) {
                navigationIcon()
            }
        }
        Box(modifier = Modifier.weight(1f)) {
            title()
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(spacing.xs),
            verticalAlignment = Alignment.CenterVertically,
            content = actions,
        )
    }
}

@Preview
@Composable
private fun SacramentTopAppBarPreview() {
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        SacramentTopAppBar(
            title = { SacramentText(text = "Title", style = SacramentTheme.typography.titleSmall) },
        )
    }
}
