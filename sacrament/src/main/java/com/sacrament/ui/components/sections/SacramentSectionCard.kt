package com.sacrament.ui.components.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sacrament.ui.components.surface.SacramentCard
import com.sacrament.ui.foundation.Bar
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.primitives.SacramentText

/**
 * Section card with optional header/footer slots.
 */
@Composable
fun SacramentSectionCard(
    modifier: Modifier = Modifier,
    header: @Composable (() -> Unit)? = null,
    footer: @Composable (() -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    val spacing = SacramentTheme.spacing
    SacramentCard(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(spacing.lg),
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(spacing.md)) {
            if (header != null) {
                header()
            }
            content()
            if (footer != null) {
                footer()
            }
        }
    }
}

@Preview
@Composable
private fun SacramentSectionCardPreview() {
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        SacramentSectionCard(
            header = { SacramentText(text = "Header", style = SacramentTheme.typography.titleSmall) },
            content = { SacramentText(text = "Content") },
            footer = { SacramentText(text = "Footer", style = SacramentTheme.typography.bodySmall) },
            modifier = Modifier.padding(16.dp),
        )
    }
}
