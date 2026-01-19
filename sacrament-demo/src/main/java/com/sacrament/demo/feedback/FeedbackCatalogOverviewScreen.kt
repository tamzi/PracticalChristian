package com.sacrament.demo.feedback

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sacrament.demo.CatalogTopAppBar
import com.sacrament.ui.components.content.list.SacramentListItem
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.patterns.SacramentScreenScaffold

/**
 * Feedback Components catalog overview screen.
 */
@Composable
fun FeedbackCatalogOverviewScreen(
    onNavigateBack: () -> Unit,
    onNavigateToProgressIndicators: () -> Unit,
    onNavigateToInlineMessages: () -> Unit
) {
    SacramentScreenScaffold(
        topBar = {
            CatalogTopAppBar(
                title = "Feedback Components",
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
                    .padding(horizontal = SacramentTheme.spacing.xl, vertical = SacramentTheme.spacing.xxl),
                verticalArrangement = Arrangement.spacedBy(SacramentTheme.spacing.lg)
            ) {
                SacramentListItem(
                    headline = "PROGRESS INDICATORS",
                    supporting = "Linear, Circular variants",
                    onClick = onNavigateToProgressIndicators
                )

                SacramentListItem(
                    headline = "INLINE MESSAGES",
                    supporting = "Neutral, Success, Warning, Error, Info tones",
                    onClick = onNavigateToInlineMessages
                )
            }
        }
    }
}
