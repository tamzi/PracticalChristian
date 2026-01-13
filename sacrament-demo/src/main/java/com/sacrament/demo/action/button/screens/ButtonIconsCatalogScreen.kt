package com.sacrament.demo.action.button.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sacrament.demo.CatalogSection
import com.sacrament.demo.CatalogTopAppBar
import com.sacrament.ui.components.action.SacramentButton
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.foundation.icon.SacramentIcons
import com.sacrament.ui.patterns.SacramentScreenScaffold
import com.sacrament.ui.primitives.SacramentText

/**
 * Buttons with Icons detail screen.
 *
 * Displays examples of SacramentButton with icons in various positions.
 * Icons enhance button clarity by providing visual reinforcement of the action.
 *
 * Navigation path: Home → Action Components → Buttons → Buttons with Icons (this screen)
 *
 * Icon positions demonstrated:
 * - **Leading Icons**: Icon positioned before the button text (left side in LTR)
 * - **Trailing Icons**: Icon positioned after the button text (right side in LTR)
 * - **Disabled States**: Shows how icons appear when buttons are disabled
 *
 * Best practices:
 * - Use icons to reinforce the button's action (e.g., Add icon for "Add Item")
 * - Leading icons typically indicate the action type
 * - Trailing icons often suggest navigation or continuation
 *
 * @param onNavigateBack Callback to navigate back to the Buttons overview screen
 */
@Composable
fun ButtonIconsCatalogScreen(onNavigateBack: () -> Unit) {
    SacramentScreenScaffold(
        topBar = {
            CatalogTopAppBar(
                title = "Buttons with Icons",
                onNavigateBack = onNavigateBack
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = SacramentTheme.spacing.xl, vertical = SacramentTheme.spacing.xxl),
            verticalArrangement = Arrangement.spacedBy(SacramentTheme.spacing.xxl)
        ) {
            CatalogSection("Leading Icons") {
                CatalogRow("Add Icon") {
                    SacramentButton(
                        text = "Add Item",
                        onClick = {},
                        leadingIcon = SacramentIcons.Add
                    )
                }
                CatalogRow("Save Icon") {
                    SacramentButton(
                        text = "Save",
                        onClick = {},
                        leadingIcon = SacramentIcons.Save
                    )
                }
            }
            
            CatalogSection("Trailing Icons") {
                CatalogRow("Arrow Forward") {
                    SacramentButton(
                        text = "Continue",
                        onClick = {},
                        trailingIcon = SacramentIcons.ArrowForward
                    )
                }
                CatalogRow("Settings Icon") {
                    SacramentButton(
                        text = "Settings",
                        onClick = {},
                        trailingIcon = SacramentIcons.Settings
                    )
                }
            }
            
            CatalogSection("Disabled States") {
                CatalogRow("Disabled with Leading Icon") {
                    SacramentButton(
                        text = "Disabled",
                        onClick = {},
                        leadingIcon = SacramentIcons.Add,
                        enabled = false
                    )
                }
                CatalogRow("Disabled with Trailing Icon") {
                    SacramentButton(
                        text = "Disabled",
                        onClick = {},
                        trailingIcon = SacramentIcons.ArrowForward,
                        enabled = false
                    )
                }
            }
        }
    }
}

/**
 * Row component for displaying button icon examples.
 *
 * Used exclusively in ButtonIconsCatalogScreen to present labeled examples
 * of buttons with icons in various positions and states.
 *
 * @param label Descriptive label for the example (e.g., "Add Icon", "Arrow Forward")
 * @param content The composable button example to display below the label
 */
@Composable
private fun CatalogRow(
    label: String,
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(SacramentTheme.spacing.sm)
    ) {
        SacramentText(
            text = label,
            style = SacramentTheme.typography.bodyMedium,
            color = SacramentTheme.colors.text.muted
        )
        content()
    }
}
