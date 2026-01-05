package com.sacrament.demo.catalog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sacrament.ui.components.action.SacramentButton
import com.sacrament.ui.components.action.SacramentButtonSize
import com.sacrament.ui.components.action.SacramentButtonVariant
import com.sacrament.demo.catalog.CatalogTopAppBar
import com.sacrament.ui.patterns.SacramentScreenScaffold
import com.sacrament.ui.primitives.SacramentText
import com.sacrament.ui.foundation.SacramentTheme

@Composable
fun CatalogHomeScreen(
    onNavigateToAction: () -> Unit,
    onNavigateToInput: () -> Unit,
    onNavigateToNavigation: () -> Unit,
    onNavigateToSurface: () -> Unit,
    onNavigateToContent: () -> Unit,
    onNavigateToFeedback: () -> Unit,
    onNavigateToPatterns: () -> Unit,
) {
    SacramentScreenScaffold(
        topBar = {
            CatalogTopAppBar(title = "Sacrament Design System")
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(SacramentTheme.spacing.lg),
            verticalArrangement = Arrangement.spacedBy(SacramentTheme.spacing.md)
        ) {
            SacramentText(
                text = "Component Catalog",
                style = SacramentTheme.typography.headlineLarge,
                modifier = Modifier.padding(bottom = SacramentTheme.spacing.sm)
            )
            
            SacramentText(
                text = "Browse all components, variants, sizes, and intents in the Sacrament design system.",
                style = SacramentTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = SacramentTheme.spacing.lg)
            )
            
            CatalogSectionButton(
                title = "Action Components",
                description = "Buttons, FABs, Icon Buttons",
                onClick = onNavigateToAction
            )
            
            CatalogSectionButton(
                title = "Input Components",
                description = "Text Fields, Checkboxes, Switches, Radio Buttons",
                onClick = onNavigateToInput
            )
            
            CatalogSectionButton(
                title = "Navigation Components",
                description = "Top App Bar, Bottom Bar, Tabs, Navigation Rail",
                onClick = onNavigateToNavigation
            )
            
            CatalogSectionButton(
                title = "Surface Components",
                description = "Cards, Dialogs, Sheets",
                onClick = onNavigateToSurface
            )
            
            CatalogSectionButton(
                title = "Content Components",
                description = "Avatars, Badges, Chips, Tags, List Items",
                onClick = onNavigateToContent
            )
            
            CatalogSectionButton(
                title = "Feedback Components",
                description = "Snackbars, Progress Indicators, Inline Messages",
                onClick = onNavigateToFeedback
            )
            
            CatalogSectionButton(
                title = "Patterns",
                description = "Screen Scaffold, Empty State, Error State, Loading State",
                onClick = onNavigateToPatterns
            )
        }
    }
}

@Composable
private fun CatalogSectionButton(
    title: String,
    description: String,
    onClick: () -> Unit,
) {
    SacramentButton(
        text = title,
        onClick = onClick,
        variant = SacramentButtonVariant.Outlined,
        size = SacramentButtonSize.Large,
        modifier = Modifier.fillMaxWidth()
    )
    
    SacramentText(
        text = description,
        style = SacramentTheme.typography.bodySmall,
        modifier = Modifier.padding(
            start = SacramentTheme.spacing.md,
            bottom = SacramentTheme.spacing.md
        )
    )
}

