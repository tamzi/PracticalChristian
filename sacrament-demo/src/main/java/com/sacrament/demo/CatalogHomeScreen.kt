package com.sacrament.demo

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import com.sacrament.ui.components.input.SacramentSwitch
import com.sacrament.ui.components.input.SacramentSwitchSize
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.primitives.SacramentText

@Composable
fun CatalogHomeScreen(
    isDarkTheme: Boolean,
    onThemeToggle: () -> Unit,
    onNavigateToAction: () -> Unit,
    onNavigateToInput: () -> Unit,
    onNavigateToNavigation: () -> Unit,
    onNavigateToSurface: () -> Unit,
    onNavigateToContent: () -> Unit,
    onNavigateToFeedback: () -> Unit,
    onNavigateToPatterns: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(SacramentTheme.colors.surfaces.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = SacramentTheme.spacing.xl, vertical = SacramentTheme.spacing.xxl),
            verticalArrangement = Arrangement.spacedBy(SacramentTheme.spacing.lg)
        ) {
            // Theme switcher section
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = SacramentTheme.spacing.md),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    SacramentText(
                        text = "Dark Mode",
                        style = SacramentTheme.typography.headlineSmall,
                        color = SacramentTheme.colors.text.strong
                    )
                    Spacer(modifier = Modifier.height(SacramentTheme.spacing.xs))
                    SacramentText(
                        text = if (isDarkTheme) "Currently in dark theme" else "Currently in light theme",
                        style = SacramentTheme.typography.bodyMedium,
                        color = SacramentTheme.colors.text.muted
                    )
                }
                SacramentSwitch(
                    checked = isDarkTheme,
                    onCheckedChange = { onThemeToggle() },
                    size = SacramentSwitchSize.Medium
                )
            }
            
            Spacer(modifier = Modifier.height(SacramentTheme.spacing.md))
            
            // Catalog items
            CatalogListItem(
                title = "ACTION COMPONENTS",
                description = "Buttons, FABs, Icon Buttons",
                onClick = onNavigateToAction
            )
            
            CatalogListItem(
                title = "INPUT COMPONENTS",
                description = "Text Fields, Checkboxes, Switches, Radio Buttons",
                onClick = onNavigateToInput
            )
            
            CatalogListItem(
                title = "NAVIGATION COMPONENTS",
                description = "Top App Bar, Bottom Bar, Tabs, Navigation Rail",
                onClick = onNavigateToNavigation
            )
            
            CatalogListItem(
                title = "SURFACE COMPONENTS",
                description = "Cards, Dialogs, Sheets",
                onClick = onNavigateToSurface
            )
            
            CatalogListItem(
                title = "CONTENT COMPONENTS",
                description = "Avatars, Badges, Chips, Tags, List Items",
                onClick = onNavigateToContent
            )
            
            CatalogListItem(
                title = "FEEDBACK COMPONENTS",
                description = "Snackbars, Progress Indicators, Inline Messages",
                onClick = onNavigateToFeedback
            )
            
            CatalogListItem(
                title = "PATTERNS",
                description = "Screen Scaffold, Empty State, Error State, Loading State",
                onClick = onNavigateToPatterns
            )
        }
    }
}

@Composable
private fun CatalogListItem(
    title: String,
    description: String,
    onClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                role = Role.Button,
                onClick = onClick
            )
            .padding(vertical = SacramentTheme.spacing.md)
    ) {
        SacramentText(
            text = title,
            style = SacramentTheme.typography.headlineSmall,
            color = SacramentTheme.colors.text.strong
        )
        
        Spacer(modifier = Modifier.height(SacramentTheme.spacing.xs))
        
        SacramentText(
            text = description,
            style = SacramentTheme.typography.bodyMedium,
            color = SacramentTheme.colors.text.muted
        )
    }
}

