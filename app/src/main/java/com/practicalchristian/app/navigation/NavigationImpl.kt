package com.practicalchristian.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.practicalchristian.app.core.ui.navigation.AppDestination
import com.practicalchristian.app.core.ui.navigation.AppNavigator

/**
 * Composable function to remember NavController and AppNavigator together.
 */
@Composable
fun rememberAppNavigator(): Pair<NavHostController, AppNavigator> {
    val navController = rememberNavController()
    val navigator = remember(navController) {
        NavigationImpl(navController)
    }
    return navController to navigator
}

/**
 * Implementation of Navigator using NavHostController.
 * Created in composition, never via DI.
 */
internal class NavigationImpl(
    private val navController: NavHostController
) : AppNavigator {

    override fun navigate(to: AppDestination, popToRoot: Boolean, inclusive: Boolean) {
        navController.navigate(to) {
            launchSingleTop = true
            restoreState = true

            if (popToRoot) {
                popUpTo(navController.graph.startDestinationId) {
                    saveState = true
                    this.inclusive = inclusive
                }
            }
        }
    }

    override fun replaceWith(to: AppDestination) {
        navController.navigate(to) {
            val currentDestination = navController.currentDestination ?: return@navigate
            popUpTo(currentDestination.id) {
                inclusive = true
            }
            launchSingleTop = true
        }
    }

    override fun clearAndNavigateTo(to: AppDestination) {
        navController.navigate(to) {
            popUpTo(navController.graph.startDestinationId) {
                inclusive = false
            }
            launchSingleTop = true
            restoreState = false
        }
    }

    override fun back(): Boolean {
        return navController.popBackStack()
    }

    override fun up(): Boolean {
        return navController.navigateUp()
    }
}
