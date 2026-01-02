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
import com.sacrament.ui.foundation.Bar
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.primitives.SacramentText

/**
 * Section header with optional subtitle and action.
 */
@Composable
fun SacramentSectionHeader(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    subtitle: @Composable (() -> Unit)? = null,
    action: @Composable (() -> Unit)? = null,
) {
    val spacing = SacramentTheme.spacing
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = spacing.lg, vertical = spacing.sm),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(spacing.xs)) {
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
private fun SacramentSectionHeaderPreview() {
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        SacramentSectionHeader(
            title = { SacramentText(text = "Section", style = SacramentTheme.typography.titleSmall) },
            subtitle = { SacramentText(text = "Subtitle", style = SacramentTheme.typography.bodySmall) },
            action = { SacramentText(text = "See all", style = SacramentTheme.typography.labelSmall) },
        )
    }
}
