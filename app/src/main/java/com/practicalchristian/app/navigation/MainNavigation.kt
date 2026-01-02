package com.practicalchristian.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.toRoute
import com.practicalchristian.app.core.ui.navigation.AppDestination
import com.practicalchristian.app.core.ui.navigation.usesSurfaceBar
import com.sacrament.ui.foundation.Bar

/**
 * Main navigation setup for the PracticalChristian app.
 * Uses Navigation Compose 2.9.x with type-safe routing.
 */
@Composable
fun PracticalChristianNavigation(onUpdateNavigationBar: (Bar) -> Unit) {
    val navController = rememberNavController()
    val navigator = remember(navController) {
        NavigationImpl(navController)
    }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.let { entry ->
        runCatching { entry.toRoute<AppDestination>() }.getOrNull()
    }
    val usesSurfaceBar = currentDestination?.usesSurfaceBar
        ?: navBackStackEntry?.destination?.route?.let(::routeUsesSurfaceBar)
        ?: false

    LaunchedEffect(usesSurfaceBar) {
        val bar = if (usesSurfaceBar) {
            Bar.SURFACE
        } else {
            Bar.BACKGROUND
        }
        onUpdateNavigationBar(bar)
    }

    PracticalChristianNavHost(
        navController = navController,
        navigator = navigator,
        startDestination = AppDestination.Landing
    )
}

private val SurfaceBarRoutePrefixes = listOfNotNull(
    AppDestination.Onboarding::class.qualifiedName,
    AppDestination.Authentication::class.qualifiedName,
    AppDestination.Home::class.qualifiedName,
    AppDestination.Notes::class.qualifiedName,
    AppDestination.EditNote::class.qualifiedName
)

private fun routeUsesSurfaceBar(route: String): Boolean {
    return SurfaceBarRoutePrefixes.any { prefix -> route.startsWith(prefix) }
}
