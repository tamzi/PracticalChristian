package com.sacrament.demo.input.screens

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
import com.sacrament.ui.components.input.SacramentTextField
import com.sacrament.ui.components.input.SacramentTextFieldSize
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.patterns.SacramentScreenScaffold
import com.sacrament.ui.primitives.SacramentText

/**
 * Text Fields detail screen.
 *
 * Displays interactive examples of SacramentTextField in various sizes and states.
 */
@Composable
fun TextFieldsCatalogScreen(onNavigateBack: () -> Unit) {
    var textFieldValue by remember { mutableStateOf("") }
    
    SacramentScreenScaffold(
        topBar = {
            CatalogTopAppBar(
                title = "Text Fields",
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
            CatalogSection("Sizes") {
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
            }
            
            CatalogSection("States") {
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
        }
    }
}

@Composable
private fun CatalogRow(label: String, content: @Composable () -> Unit) {
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
