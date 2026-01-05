package com.sacrament.demo.catalog

import androidx.compose.runtime.Composable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import com.sacrament.ui.components.action.SacramentIconButton
import com.sacrament.ui.components.navigation.SacramentTopAppBar
import com.sacrament.ui.primitives.SacramentText
import com.sacrament.ui.foundation.SacramentTheme

@Composable
fun CatalogTopAppBar(
    title: String,
    onNavigateBack: (() -> Unit)? = null
) {
    SacramentTopAppBar(
        title = {
            SacramentText(
                text = title,
                style = SacramentTheme.typography.titleSmall
            )
        },
        navigationIcon = if (onNavigateBack != null) {
            {
                SacramentIconButton(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                    contentDescription = "Back",
                    onClick = onNavigateBack
                )
            }
        } else null
    )
}

