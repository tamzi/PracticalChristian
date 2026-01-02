package com.practicalchristian.app.core.ui.navigation

import androidx.compose.runtime.staticCompositionLocalOf

/**
 * Navigation façade interface.
 * Provides clean API that can be mocked in tests.
 *
 * IMPORTANT: This wraps NavHostController but is NOT the controller itself.
 * Never inject NavHostController via DI—it has a Compose lifecycle.
 */
interface AppNavigator {
    /**
     * Navigate to a destination.
     * @param popToRoot If true, pops to graph's start destination
     * @param inclusive If true with popToRoot, includes start destination in pop
     */
    fun navigate(to: AppDestination, popToRoot: Boolean = false, inclusive: Boolean = false)

    /**
     * Replace current destination with new one.
     */
    fun replaceWith(to: AppDestination)

    /**
     * Clear entire back stack and navigate to destination.
     */
    fun clearAndNavigateTo(to: AppDestination)

    /**
     * Navigate back. Returns true if successful, false if at root.
     */
    fun back(): Boolean

    /**
     * Navigate up in the hierarchy.
     */
    fun up(): Boolean

    // Legacy methods for backward compatibility during migration
    @Deprecated("Use navigate() instead", ReplaceWith("navigate(destination)"))
    fun navigateTo(destination: AppDestination) = navigate(destination)

    @Deprecated("Use back() instead", ReplaceWith("back()"))
    fun navigateBack() {
        back()
    }

    @Deprecated("Use up() instead", ReplaceWith("up()"))
    fun navigateUp(): Boolean = up()

    @Deprecated("Use clearAndNavigateTo() instead", ReplaceWith("clearAndNavigateTo(destination)"))
    fun clearBackStackAndNavigateTo(destination: AppDestination) = clearAndNavigateTo(destination)
}

/**
 * CompositionLocal for accessing Navigator in Composables.
 * Prefer passing Navigator as a parameter when possible for better testability.
 */
val LocalNavigator = staticCompositionLocalOf<AppNavigator> {
    error("Navigator not provided. Ensure PracticalChristianApp provides LocalNavigator.")
}

/**
 * Convenience function to navigate to top-level destinations with proper back stack management.
 * Implements multiple back stacks pattern per Material Design guidelines.
 */
fun AppNavigator.navigateToTopLevel(destination: AppDestination) {
    navigate(destination, popToRoot = true, inclusive = false)
}
