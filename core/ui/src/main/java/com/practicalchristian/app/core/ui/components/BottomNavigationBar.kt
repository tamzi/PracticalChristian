package com.practicalchristian.app.core.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import com.sacrament.ui.components.navigation.SacramentBottomBar
import com.sacrament.ui.components.navigation.SacramentBottomBarDefaults
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.foundation.icon.SacramentIcons
import com.sacrament.ui.primitives.SacramentIcon
import com.sacrament.ui.primitives.SacramentText

/**
 * Enum representing the bottom navigation screens.
 */
enum class BottomNavScreen(val title: String, val icon: ImageVector) {
    HOME("Home", SacramentIcons.SacramentIconHome),
    BOOKS("Books", SacramentIcons.SacramentIconBook),
    NOTES("Notes", SacramentIcons.SacramentIconNote)
}

/**
 * Shared bottom navigation bar used across main app screens.
 * Supports navigation between Home, Books, and Notes sections.
 */
@Composable
fun SharedBottomNavigationBar(
    selectedScreen: BottomNavScreen,
    onHomeClick: () -> Unit,
    onBooksClick: () -> Unit,
    onNotesClick: () -> Unit
) {
    SacramentBottomBar(colors = SacramentBottomBarDefaults.colors()) {
        BottomNavItem(
            screen = BottomNavScreen.HOME,
            selected = selectedScreen == BottomNavScreen.HOME,
            onClick = onHomeClick,
        )
        BottomNavItem(
            screen = BottomNavScreen.BOOKS,
            selected = selectedScreen == BottomNavScreen.BOOKS,
            onClick = onBooksClick,
        )
        BottomNavItem(
            screen = BottomNavScreen.NOTES,
            selected = selectedScreen == BottomNavScreen.NOTES,
            onClick = onNotesClick,
        )
    }
}

/**
 * Basic bottom navigation bar for HomeScreen (without selection state).
 * Used when the home screen manages its own internal navigation.
 */
@Composable
fun BottomNavigationBar(
    onHomeClick: () -> Unit,
    onBooksClick: () -> Unit,
    onNotesClick: () -> Unit
) {
    SacramentBottomBar(colors = SacramentBottomBarDefaults.colors()) {
        BottomNavItem(
            screen = BottomNavScreen.HOME,
            selected = true,
            onClick = onHomeClick,
        )
        BottomNavItem(
            screen = BottomNavScreen.BOOKS,
            selected = false,
            onClick = onBooksClick,
        )
        BottomNavItem(
            screen = BottomNavScreen.NOTES,
            selected = false,
            onClick = onNotesClick,
        )
    }
}

@Composable
private fun RowScope.BottomNavItem(
    screen: BottomNavScreen,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val spacing = SacramentTheme.spacing
    val colors = SacramentTheme.colors
    val contentColor = if (selected) colors.navigation.selectedIcon else colors.navigation.unselectedIcon
    Column(
        modifier = modifier
            .weight(1f)
            .fillMaxHeight()
            .clickable(role = Role.Tab, onClick = onClick)
            .padding(vertical = spacing.xs),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(spacing.xs, Alignment.CenterVertically),
    ) {
        SacramentIcon(
            imageVector = screen.icon,
            contentDescription = screen.title,
            tint = contentColor,
            size = SacramentTheme.iconSizes.md,
        )
        SacramentText(
            text = screen.title,
            style = SacramentTheme.typography.labelSmall,
            color = contentColor,
        )
    }
}
