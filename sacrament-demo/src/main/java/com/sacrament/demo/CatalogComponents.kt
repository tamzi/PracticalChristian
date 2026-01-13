package com.sacrament.demo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.primitives.SacramentText

/**
 * Reusable section title component for catalog screens.
 * Provides consistent styling for section headers across the demo app.
 */
@Composable
fun CatalogSectionTitle(
    text: String,
    modifier: Modifier = Modifier
) {
    SacramentText(
        text = text,
        style = SacramentTheme.typography.headlineMedium.copy(
            fontWeight = FontWeight.Bold,
            letterSpacing = SacramentTheme.letterSpacing.wider
        ),
        color = SacramentTheme.colors.text.strong,
        modifier = modifier.padding(bottom = SacramentTheme.spacing.sm)
    )
}

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
        CatalogSectionTitle(text = title)
        content()
    }
}
