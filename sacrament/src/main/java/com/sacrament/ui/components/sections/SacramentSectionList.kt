package com.sacrament.ui.components.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sacrament.ui.foundation.Bar
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.primitives.SacramentDivider
import com.sacrament.ui.primitives.SacramentText

/**
 * Section list wrapper with optional header.
 */
@Composable
fun SacramentSectionList(
    modifier: Modifier = Modifier,
    header: @Composable (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    val spacing = SacramentTheme.spacing
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = spacing.sm),
        verticalArrangement = Arrangement.spacedBy(spacing.xs),
    ) {
        if (header != null) {
            header()
        }
        content()
    }
}

@Preview
@Composable
private fun SacramentSectionListPreview() {
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        SacramentSectionList(
            header = { SacramentText(text = "Section", style = SacramentTheme.typography.titleSmall) },
            modifier = Modifier.padding(16.dp),
        ) {
            SacramentText(text = "Item 1")
            SacramentDivider()
            SacramentText(text = "Item 2")
        }
    }
}
