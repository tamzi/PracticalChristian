package com.sacrament.demo.input

import androidx.compose.foundation.layout.Arrangement
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
import com.sacrament.ui.components.input.SacramentCheckbox
import com.sacrament.ui.components.input.SacramentCheckboxSize
import com.sacrament.ui.components.input.SacramentRadio
import com.sacrament.ui.components.input.SacramentRadioSize
import com.sacrament.ui.components.input.SacramentSwitch
import com.sacrament.ui.components.input.SacramentSwitchSize
import com.sacrament.ui.components.input.SacramentTextField
import com.sacrament.ui.components.input.SacramentTextFieldSize
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.patterns.SacramentScreenScaffold
import com.sacrament.ui.primitives.SacramentText

/**
 * Input Components catalog screen.
 *
 * Displays interactive examples of input components including text fields, checkboxes,
 * radio buttons, and switches. All components are fully functional to demonstrate behavior.
 *
 * Navigation path: Home → Input Components (this screen)
 *
 * Components demonstrated:
 * - **Text Fields**: Small, Medium sizes, with label, error state, and disabled state
 * - **Checkboxes**: Small, Medium sizes
 * - **Radio Buttons**: Small, Medium sizes
 * - **Switches**: Small, Medium sizes
 *
 * @param onNavigateBack Callback to navigate back to the catalog home screen
 */
@Composable
fun InputCatalogScreen(onNavigateBack: () -> Unit) {
    var textFieldValue by remember { mutableStateOf("") }
    var checkboxChecked by remember { mutableStateOf(false) }
    var radioSelected by remember { mutableStateOf(false) }
    var switchChecked by remember { mutableStateOf(false) }
    
    SacramentScreenScaffold(
        topBar = {
            CatalogTopAppBar(
                title = "Input Components",
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
            // Text Fields - Sizes
            CatalogSection("Text Fields - Sizes") {
                CatalogRow("Small") {
                    SacramentTextField(
                        value = textFieldValue,
                        onValueChange = { textFieldValue = it },
                        size = SacramentTextFieldSize.Small,
                        placeholder = "Enter text"
                    )
                }
                CatalogRow("Medium") {
                    SacramentTextField(
                        value = textFieldValue,
                        onValueChange = { textFieldValue = it },
                        size = SacramentTextFieldSize.Medium,
                        placeholder = "Enter text"
                    )
                }
                // Large size not currently available; using Medium for demonstration.
                CatalogRow("With Label") {
                    SacramentTextField(
                        value = textFieldValue,
                        onValueChange = { textFieldValue = it },
                        label = "Email"
                    )
                }
                CatalogRow("Error State") {
                    SacramentTextField(
                        value = textFieldValue,
                        onValueChange = { textFieldValue = it },
                        isError = true,
                        label = "Email",
                        placeholder = "Invalid email"
                    )
                }
                CatalogRow("Disabled") {
                    SacramentTextField(
                        value = "Disabled field",
                        onValueChange = {},
                        enabled = false
                    )
                }
            }
            
            // Checkboxes
            CatalogSection("Checkboxes") {
                CatalogRow("Small") {
                    SacramentCheckbox(
                        checked = checkboxChecked,
                        onCheckedChange = { checkboxChecked = it },
                        size = SacramentCheckboxSize.Small
                    )
                }
                CatalogRow("Medium") {
                    SacramentCheckbox(
                        checked = checkboxChecked,
                        onCheckedChange = { checkboxChecked = it },
                        size = SacramentCheckboxSize.Medium
                    )
                }
                // Large size not currently available in SacramentCheckboxSize.
            }
            
            // Radio Buttons
            CatalogSection("Radio Buttons") {
                CatalogRow("Small") {
                    SacramentRadio(
                        selected = radioSelected,
                        onClick = { radioSelected = !radioSelected },
                        size = SacramentRadioSize.Small
                    )
                }
                CatalogRow("Medium") {
                    SacramentRadio(
                        selected = radioSelected,
                        onClick = { radioSelected = !radioSelected },
                        size = SacramentRadioSize.Medium
                    )
                }
                // Large size not currently available in SacramentRadioSize.
            }
            
            // Switches
            CatalogSection("Switches") {
                CatalogRow("Small") {
                    SacramentSwitch(
                        checked = switchChecked,
                        onCheckedChange = { switchChecked = it },
                        size = SacramentSwitchSize.Small
                    )
                }
                CatalogRow("Medium") {
                    SacramentSwitch(
                        checked = switchChecked,
                        onCheckedChange = { switchChecked = it },
                        size = SacramentSwitchSize.Medium
                    )
                }
                // Large size not currently available in SacramentSwitchSize.
            }
        }
    }
}

/**
 * Row component for displaying input component examples.
 *
 * Used exclusively in InputCatalogScreen to present labeled examples of
 * input components (text fields, checkboxes, radio buttons, switches).
 *
 * @param label Descriptive label for the example (e.g., "Small", "With Label")
 * @param content The composable content to display below the label
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

