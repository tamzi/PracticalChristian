package com.sacrament.ui.components.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.Home
import com.sacrament.ui.foundation.Bar
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.primitives.SacramentIcon
import com.sacrament.ui.primitives.SacramentText

/**
 * Model for navigation rail items.
 */
data class SacramentNavigationRailItem(
    val label: String,
    val icon: ImageVector,
)

/**
 * Navigation rail for larger layouts.
 */
@Composable
fun SacramentNavigationRail(
    items: List<SacramentNavigationRailItem>,
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
    header: @Composable (() -> Unit)? = null,
    colors: SacramentNavigationRailColors = SacramentNavigationRailDefaults.colors(),
) {
    val spacing = SacramentTheme.spacing
    Column(
        modifier = modifier
            .width(SacramentNavigationRailDefaults.width())
            .fillMaxHeight()
            .background(colors.container)
            .padding(vertical = spacing.lg),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(spacing.lg),
    ) {
        if (header != null) {
            header()
            Spacer(modifier = Modifier.height(spacing.sm))
        }
        items.forEachIndexed { index, item ->
            val selected = index == selectedIndex
            val contentColor = if (selected) colors.activeContent else colors.inactiveContent
            Column(
                modifier = Modifier
                    .clickable(role = Role.Tab) { onItemSelected(index) },
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(spacing.xs),
            ) {
                SacramentIcon(
                    imageVector = item.icon,
                    contentDescription = item.label,
                    tint = contentColor,
                    size = SacramentTheme.iconSizes.md,
                )
                SacramentText(
                    text = item.label,
                    style = SacramentTheme.typography.labelSmall,
                    color = contentColor,
                )
            }
        }
    }
}

@Preview
@Composable
private fun SacramentNavigationRailPreview() {
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        SacramentNavigationRail(
            items = listOf(
                SacramentNavigationRailItem("Home", Icons.Rounded.Home),
                SacramentNavigationRailItem("Notes", Icons.Rounded.Edit),
            ),
            selectedIndex = 0,
            onItemSelected = {},
            modifier = Modifier.height(200.dp),
        )
    }
}
