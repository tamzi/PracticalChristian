package com.sacrament.demo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.primitives.SacramentText

/**
 * Container for a catalog section with a title and content.
 * Used across all catalog screens to organize related components with a consistent header.
 */
@Composable
fun CatalogSection(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(SacramentTheme.spacing.lg)
    ) {
        SacramentText(
            text = title,
            style = SacramentTheme.typography.headlineMedium,
            color = SacramentTheme.colors.text.strong
        )
        content()
    }
}
