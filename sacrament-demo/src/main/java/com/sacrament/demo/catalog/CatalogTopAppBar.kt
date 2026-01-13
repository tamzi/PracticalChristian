package com.sacrament.demo.catalog

import androidx.compose.runtime.Composable
import com.sacrament.ui.components.action.SacramentIconButton
import com.sacrament.ui.components.navigation.SacramentTopAppBar
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.foundation.icon.SacramentIcons
import com.sacrament.ui.primitives.SacramentText

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
                    imageVector = SacramentIcons.ArrowBack,
                    contentDescription = "Back",
                    onClick = onNavigateBack
                )
            }
        } else null
    )
}

