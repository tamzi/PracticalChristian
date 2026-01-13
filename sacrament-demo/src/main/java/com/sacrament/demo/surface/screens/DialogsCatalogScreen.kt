package com.sacrament.demo.surface.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.sacrament.demo.CatalogSection
import com.sacrament.demo.CatalogTopAppBar
import com.sacrament.ui.components.action.SacramentButton
import com.sacrament.ui.components.action.SacramentButtonTone
import com.sacrament.ui.components.action.SacramentButtonVariant
import com.sacrament.ui.components.surface.SacramentDialog
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.patterns.SacramentScreenScaffold
import com.sacrament.ui.primitives.SacramentText

/**
 * Catalog screen demonstrating dialogs.
 *
 * Showcases SacramentDialog with different configurations including:
 * - Basic dialogs with title, text, and actions
 * - Dialogs with single action button
 * - Dialogs with confirm/dismiss buttons
 * - Different tones for actions
 *
 * Dialogs are modal overlays that interrupt the user flow to
 * request decisions or display critical information.
 *
 * @param onNavigateBack Callback to navigate back to the Surface catalog screen
 */
@Composable
fun DialogsCatalogScreen(
    onNavigateBack: () -> Unit
) {
    var showBasicDialog by remember { mutableStateOf(false) }
    var showConfirmDialog by remember { mutableStateOf(false) }
    var showWarningDialog by remember { mutableStateOf(false) }

    SacramentScreenScaffold(
        topBar = {
            CatalogTopAppBar(
                title = "Dialogs",
                onNavigateBack = onNavigateBack
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(SacramentTheme.colors.surfaces.background)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(
                        horizontal = SacramentTheme.spacing.xl,
                        vertical = SacramentTheme.spacing.xxl
                    ),
                verticalArrangement = Arrangement.spacedBy(SacramentTheme.spacing.xxl)
            ) {
                // Basic dialog
                CatalogSection("Basic Dialog") {
                    SacramentText(
                        text = "Simple dialog with title, message, and a single action button.",
                        style = SacramentTheme.typography.bodyMedium,
                        color = SacramentTheme.colors.text.muted,
                        modifier = Modifier.padding(bottom = SacramentTheme.spacing.md)
                    )

                    SacramentButton(
                        text = "Show Basic Dialog",
                        onClick = { showBasicDialog = true },
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                // Confirm dialog
                CatalogSection("Confirm Dialog") {
                    SacramentText(
                        text = "Dialog with confirm and dismiss actions for user decisions.",
                        style = SacramentTheme.typography.bodyMedium,
                        color = SacramentTheme.colors.text.muted,
                        modifier = Modifier.padding(bottom = SacramentTheme.spacing.md)
                    )

                    SacramentButton(
                        text = "Show Confirm Dialog",
                        onClick = { showConfirmDialog = true },
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                // Warning dialog
                CatalogSection("Warning Dialog") {
                    SacramentText(
                        text = "Dialog with warning tone for destructive or critical actions.",
                        style = SacramentTheme.typography.bodyMedium,
                        color = SacramentTheme.colors.text.muted,
                        modifier = Modifier.padding(bottom = SacramentTheme.spacing.md)
                    )

                    SacramentButton(
                        text = "Show Warning Dialog",
                        onClick = { showWarningDialog = true },
                        tone = SacramentButtonTone.Warning,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                // Usage guidelines
                CatalogSection("Usage") {
                    SacramentText(
                        text = "Dialogs interrupt the user flow to request decisions or display critical information.",
                        style = SacramentTheme.typography.bodyMedium
                    )

                    SacramentText(
                        text = "Best practices:",
                        style = SacramentTheme.typography.labelMedium,
                        modifier = Modifier.padding(top = SacramentTheme.spacing.md)
                    )

                    Column(
                        modifier = Modifier.padding(start = SacramentTheme.spacing.md),
                        verticalArrangement = Arrangement.spacedBy(SacramentTheme.spacing.xs)
                    ) {
                        SacramentText(
                            text = "• Use sparingly to avoid interrupting users",
                            style = SacramentTheme.typography.bodySmall,
                            color = SacramentTheme.colors.text.muted
                        )
                        SacramentText(
                            text = "• Keep titles and messages concise",
                            style = SacramentTheme.typography.bodySmall,
                            color = SacramentTheme.colors.text.muted
                        )
                        SacramentText(
                            text = "• Provide clear action buttons",
                            style = SacramentTheme.typography.bodySmall,
                            color = SacramentTheme.colors.text.muted
                        )
                        SacramentText(
                            text = "• Use warning tone for destructive actions",
                            style = SacramentTheme.typography.bodySmall,
                            color = SacramentTheme.colors.text.muted
                        )
                    }
                }
            }

            // Dialog instances
            if (showBasicDialog) {
                SacramentDialog(
                    onDismissRequest = { showBasicDialog = false },
                    title = {
                        SacramentText(
                            text = "Information",
                            style = SacramentTheme.typography.titleSmall
                        )
                    },
                    text = {
                        SacramentText(
                            text = "This is a basic dialog with a single action button.",
                            style = SacramentTheme.typography.bodyMedium
                        )
                    },
                    confirmButton = {
                        SacramentButton(
                            text = "OK",
                            onClick = { showBasicDialog = false },
                            variant = SacramentButtonVariant.Filled
                        )
                    }
                )
            }

            if (showConfirmDialog) {
                SacramentDialog(
                    onDismissRequest = { showConfirmDialog = false },
                    title = {
                        SacramentText(
                            text = "Confirm Action",
                            style = SacramentTheme.typography.titleSmall
                        )
                    },
                    text = {
                        SacramentText(
                            text = "Are you sure you want to proceed with this action?",
                            style = SacramentTheme.typography.bodyMedium
                        )
                    },
                    confirmButton = {
                        SacramentButton(
                            text = "Confirm",
                            onClick = { showConfirmDialog = false },
                            variant = SacramentButtonVariant.Filled
                        )
                    },
                    dismissButton = {
                        SacramentButton(
                            text = "Cancel",
                            onClick = { showConfirmDialog = false },
                            variant = SacramentButtonVariant.Ghost
                        )
                    }
                )
            }

            if (showWarningDialog) {
                SacramentDialog(
                    onDismissRequest = { showWarningDialog = false },
                    title = {
                        SacramentText(
                            text = "Warning",
                            style = SacramentTheme.typography.titleSmall
                        )
                    },
                    text = {
                        SacramentText(
                            text = "This action cannot be undone. Are you sure you want to delete this item?",
                            style = SacramentTheme.typography.bodyMedium
                        )
                    },
                    confirmButton = {
                        SacramentButton(
                            text = "Delete",
                            onClick = { showWarningDialog = false },
                            variant = SacramentButtonVariant.Filled,
                            tone = SacramentButtonTone.Error
                        )
                    },
                    dismissButton = {
                        SacramentButton(
                            text = "Cancel",
                            onClick = { showWarningDialog = false },
                            variant = SacramentButtonVariant.Ghost
                        )
                    }
                )
            }
        }
    }
}
