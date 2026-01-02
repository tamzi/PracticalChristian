package com.practicalchristian.app.core.ui.navigation

import kotlinx.serialization.Serializable

/**
 * Type-safe navigation destinations for the PracticalChristian app.
 * Uses Kotlin serialization for compile-time safety.
 *
 * RULE: Only pass IDs as arguments. Load full data in ViewModels.
 */
@Serializable
sealed interface AppDestination {

    // Entry flow
    @Serializable
    data object Landing : AppDestination

    @Serializable
    data object Onboarding : AppDestination

    @Serializable
    data object Authentication : AppDestination

    @Serializable
    data object Setup : AppDestination

    // Main app
    @Serializable
    data object Home : AppDestination

    // Notes section (top-level graph)
    @Serializable
    data object NotesGraph : AppDestination

    @Serializable
    data object Notes : AppDestination

    @Serializable
    data class NoteDetails(val noteId: String) : AppDestination

    @Serializable
    data class EditNote(val id: String? = null) : AppDestination

    // Books section (top-level graph)
    @Serializable
    data object BooksGraph : AppDestination

    @Serializable
    data object Books : AppDestination

    @Serializable
    data class BookDetails(val bookId: Int) : AppDestination

    // Schedules section (top-level graph)
    @Serializable
    data object SchedulesGraph : AppDestination

    @Serializable
    data object Schedules : AppDestination

    @Serializable
    data class ScheduleDetails(val scheduleId: Int) : AppDestination

    // Settings
    @Serializable
    data object Settings : AppDestination

    @Serializable
    data object Profile : AppDestination

    @Serializable
    data object Tags : AppDestination
}

/**
 * Top-level destinations that support multiple back stacks.
 * Used for bottom navigation or main tabs.
 */
val TopLevelDestinations = listOf(
    AppDestination.NotesGraph,
    AppDestination.BooksGraph,
    AppDestination.SchedulesGraph,
    AppDestination.Settings,
    AppDestination.Profile
)

/**
 * Extension to check if destination should use surface-colored navigation bar.
 * Maintains existing app behavior.
 */
val AppDestination.usesSurfaceBar: Boolean
    get() = when (this) {
        AppDestination.Onboarding,
        AppDestination.Authentication,
        AppDestination.Home,
        AppDestination.Notes,
        is AppDestination.EditNote -> true

        else -> false
    }
