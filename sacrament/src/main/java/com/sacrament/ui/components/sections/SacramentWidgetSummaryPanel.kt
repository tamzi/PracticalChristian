package com.sacrament.ui.components.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sacrament.ui.components.surface.SacramentCard
import com.sacrament.ui.foundation.Bar
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.primitives.SacramentText

/**
 * Summary panel widget for high-level metrics.
 */
@Composable
fun SacramentWidgetSummaryPanel(
    title: @Composable () -> Unit,
    value: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    supporting: @Composable (() -> Unit)? = null,
    action: @Composable (() -> Unit)? = null,
) {
    val spacing = SacramentTheme.spacing
    SacramentCard(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(spacing.lg),
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(spacing.sm)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                title()
                if (action != null) {
                    action()
                }
            }
            value()
            if (supporting != null) {
                supporting()
            }
        }
    }
}

@Preview
@Composable
private fun SacramentWidgetSummaryPanelPreview() {
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        SacramentWidgetSummaryPanel(
            title = { SacramentText(text = "Weekly", style = SacramentTheme.typography.labelMedium) },
            value = { SacramentText(text = "4 days", style = SacramentTheme.typography.titleLarge) },
            supporting = { SacramentText(text = "+12% vs last week", style = SacramentTheme.typography.bodySmall) },
            modifier = Modifier.padding(16.dp),
        )
    }
}
