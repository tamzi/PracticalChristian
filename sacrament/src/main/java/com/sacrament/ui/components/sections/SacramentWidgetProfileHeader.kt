package com.sacrament.ui.components.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sacrament.ui.components.content.SacramentAvatar
import com.sacrament.ui.foundation.Bar
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.primitives.SacramentText

/**
 * Profile header widget with avatar, title, and subtitle.
 */
@Composable
fun SacramentWidgetProfileHeader(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    subtitle: @Composable (() -> Unit)? = null,
    leading: @Composable (() -> Unit)? = null,
    action: @Composable (() -> Unit)? = null,
) {
    val spacing = SacramentTheme.spacing
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = spacing.lg, vertical = spacing.md),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(spacing.md),
    ) {
        if (leading != null) {
            leading()
        }
        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(spacing.xs)) {
            title()
            if (subtitle != null) {
                subtitle()
            }
        }
        if (action != null) {
            action()
        }
    }
}

@Preview
@Composable
private fun SacramentWidgetProfileHeaderPreview() {
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        SacramentWidgetProfileHeader(
            title = { SacramentText(text = "Frank", style = SacramentTheme.typography.titleSmall) },
            subtitle = { SacramentText(text = "View profile", style = SacramentTheme.typography.bodySmall) },
            leading = { SacramentAvatar(initials = "FT") },
            modifier = Modifier.padding(16.dp),
        )
    }
}
