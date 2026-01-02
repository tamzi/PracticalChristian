package com.sacrament.ui.components.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sacrament.ui.foundation.Bar
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.primitives.SacramentIcon
import com.sacrament.ui.primitives.SacramentText

/**
 * Model for a tab item.
 */
data class SacramentTabItem(
    val label: String,
    val icon: ImageVector? = null,
)

/**
 * Tab row for primary navigation.
 */
@Composable
fun SacramentTabRow(
    tabs: List<SacramentTabItem>,
    selectedIndex: Int,
    onTabSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
    colors: SacramentTabRowColors = SacramentTabRowDefaults.colors(),
) {
    val spacing = SacramentTheme.spacing
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(colors.container),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        tabs.forEachIndexed { index, tab ->
            val selected = index == selectedIndex
            val contentColor = if (selected) colors.activeContent else colors.inactiveContent
            Column(
                modifier = Modifier
                    .weight(1f)
                    .defaultMinSize(minHeight = SacramentTabRowDefaults.minTabHeight())
                    .clickable(role = Role.Tab) { onTabSelected(index) }
                    .padding(vertical = spacing.xs),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(spacing.xs),
            ) {
                if (tab.icon != null) {
                    SacramentIcon(
                        imageVector = tab.icon,
                        contentDescription = tab.label,
                        tint = contentColor,
                        size = SacramentTheme.iconSizes.sm,
                    )
                }
                SacramentText(
                    text = tab.label,
                    style = SacramentTheme.typography.labelSmall,
                    color = contentColor,
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(SacramentTabRowDefaults.indicatorHeight())
                        .background(if (selected) colors.indicator else androidx.compose.ui.graphics.Color.Transparent),
                )
            }
        }
    }
}

@Preview
@Composable
private fun SacramentTabRowPreview() {
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        SacramentTabRow(
            tabs = listOf(
                SacramentTabItem("Home"),
                SacramentTabItem("Notes"),
                SacramentTabItem("Profile"),
            ),
            selectedIndex = 0,
            onTabSelected = {},
            modifier = Modifier.padding(8.dp),
        )
    }
}
