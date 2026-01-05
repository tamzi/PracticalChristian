package com.sacrament.demo.catalog.input

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
import com.sacrament.ui.components.input.SacramentCheckbox
import com.sacrament.ui.components.input.SacramentCheckboxSize
import com.sacrament.ui.components.input.SacramentRadio
import com.sacrament.ui.components.input.SacramentRadioSize
import com.sacrament.ui.components.input.SacramentSwitch
import com.sacrament.ui.components.input.SacramentSwitchSize
import com.sacrament.ui.components.input.SacramentTextField
import com.sacrament.ui.components.input.SacramentTextFieldSize
import com.sacrament.demo.catalog.CatalogTopAppBar
import com.sacrament.ui.patterns.SacramentScreenScaffold
import com.sacrament.ui.primitives.SacramentText
import com.sacrament.ui.foundation.SacramentTheme

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
                .padding(SacramentTheme.spacing.lg),
            verticalArrangement = Arrangement.spacedBy(SacramentTheme.spacing.xl)
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
                CatalogRow("Large") {
                    SacramentTextField(
                        value = textFieldValue,
                        onValueChange = { textFieldValue = it },
                        size = SacramentTextFieldSize.Large,
                        placeholder = "Enter text"
                    )
                }
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
                CatalogRow("Large") {
                    SacramentCheckbox(
                        checked = checkboxChecked,
                        onCheckedChange = { checkboxChecked = it },
                        size = SacramentCheckboxSize.Large
                    )
                }
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
                CatalogRow("Large") {
                    SacramentRadio(
                        selected = radioSelected,
                        onClick = { radioSelected = !radioSelected },
                        size = SacramentRadioSize.Large
                    )
                }
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
                CatalogRow("Large") {
                    SacramentSwitch(
                        checked = switchChecked,
                        onCheckedChange = { switchChecked = it },
                        size = SacramentSwitchSize.Large
                    )
                }
            }
        }
    }
}

@Composable
private fun CatalogSection(
    title: String,
    content: @Composable () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(SacramentTheme.spacing.md)
    ) {
        SacramentText(
            text = title,
            style = SacramentTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = SacramentTheme.spacing.xs)
        )
        content()
    }
}

@Composable
private fun CatalogRow(
    label: String,
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(SacramentTheme.spacing.xs)
    ) {
        SacramentText(
            text = label,
            style = SacramentTheme.typography.labelMedium,
            modifier = Modifier.padding(start = SacramentTheme.spacing.xs)
        )
        content()
    }
}

