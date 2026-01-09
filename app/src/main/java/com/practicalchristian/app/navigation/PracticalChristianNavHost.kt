package com.practicalchristian.app.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.practicalchristian.app.core.ui.navigation.AppDestination
import com.practicalchristian.app.core.ui.navigation.AppNavigator
import com.practicalchristian.app.feature.profile.ProfileScreen
import com.practicalchristian.app.feature.auth.AuthenticationScreen
import com.practicalchristian.app.feature.books.detail.BookScreen
import com.practicalchristian.app.feature.books.list.BooksScreen
import com.practicalchristian.app.feature.notes.edit.EditNoteScreen
import com.practicalchristian.app.feature.home.home.HomeScreen
import com.practicalchristian.app.feature.landing.LandingScreen
import com.practicalchristian.app.feature.notes.detail.NoteScreen
import com.practicalchristian.app.feature.notes.list.NotesScreen
import com.practicalchristian.app.feature.onboarding.OnboardingScreen
import com.practicalchristian.app.feature.schedules.ScheduleScreen
import com.practicalchristian.app.feature.schedules.SchedulesScreen
import com.practicalchristian.app.feature.setup.SetupScreen
import com.practicalchristian.app.feature.settings.SettingsScreen
import com.practicalchristian.app.feature.tags.TagsScreen
import com.practicalchristian.app.feature.notifications.reminder.NotificationReminderScreen
import com.practicalchristian.app.feature.notifications.hub.NotificationsHubScreen

/**
 * Navigation host for the PracticalChristian app.
 *
 * @param navController The NavHostController managing navigation state
 * @param navigator The AppNavigator abstraction for navigation operations
 * @param startDestination The initial destination when NavHost is displayed
 */
@Composable
fun PracticalChristianNavHost(
    navController: NavHostController,
    navigator: AppNavigator,
    modifier: Modifier = Modifier,
    startDestination: AppDestination = AppDestination.Landing
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier.fillMaxSize(),
        // Default to no transitions (predictive back friendly)
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None }
    ) {
        // Entry flow screens
        composable<AppDestination.Landing> {
            LandingScreen(navigator = navigator)
        }

        composable<AppDestination.Onboarding> {
            OnboardingScreen(navigator = navigator)
        }

        composable<AppDestination.Authentication> {
            AuthenticationScreen(navigator = navigator)
        }

        composable<AppDestination.Setup> {
            SetupScreen(navigator = navigator)
        }

        composable<AppDestination.NotificationReminder> {
            NotificationReminderScreen(navigator = navigator)
        }

        composable<AppDestination.Home> {
            HomeScreen(navigator = navigator)
        }

        // Notes section (top-level graph with multiple back stack)
        notesGraph(navigator)

        // Books section (top-level graph with multiple back stack)
        booksGraph(navigator)

        // Schedules section (top-level graph with multiple back stack)
        schedulesGraph(navigator)

        // Settings screens
        composable<AppDestination.Settings> {
            SettingsScreen(navigator = navigator)
        }

        composable<AppDestination.Profile> {
            ProfileScreen(navigator = navigator)
        }

        composable<AppDestination.Tags> {
            TagsScreen(navigator = navigator)
        }

        composable<AppDestination.NotificationsHub> {
            NotificationsHubScreen(navigator = navigator)
        }
    }
}

/**
 * Notes section navigation graph.
 * Supports multiple back stacks when switching between top-level tabs.
 */
private fun NavGraphBuilder.notesGraph(navigator: AppNavigator) {
    navigation<AppDestination.NotesGraph>(
        startDestination = AppDestination.Notes
    ) {
        composable<AppDestination.Notes> {
            NotesScreen(navigator = navigator)
        }

        composable<AppDestination.NoteDetails> { backStackEntry ->
            val args = backStackEntry.toRoute<AppDestination.NoteDetails>()
            NoteScreen(
                noteId = args.noteId,
                navigator = navigator
            )
        }

        composable<AppDestination.EditNote> { backStackEntry ->
            val args = backStackEntry.toRoute<AppDestination.EditNote>()
            EditNoteScreen(
                id = args.id,
                navigator = navigator
            )
        }
    }
}

/**
 * Books section navigation graph.
 * Supports multiple back stacks when switching between top-level tabs.
 */
private fun NavGraphBuilder.booksGraph(navigator: AppNavigator) {
    navigation<AppDestination.BooksGraph>(
        startDestination = AppDestination.Books
    ) {
        composable<AppDestination.Books> {
            BooksScreen(navigator = navigator)
        }

        composable<AppDestination.BookDetails> { backStackEntry ->
            val args = backStackEntry.toRoute<AppDestination.BookDetails>()
            BookScreen(
                id = args.bookId,
                navigator = navigator
            )
        }
    }
}

/**
 * Schedules section navigation graph.
 * Supports multiple back stacks when switching between top-level tabs.
 */
private fun NavGraphBuilder.schedulesGraph(navigator: AppNavigator) {
    navigation<AppDestination.SchedulesGraph>(
        startDestination = AppDestination.Schedules
    ) {
        composable<AppDestination.Schedules> {
            SchedulesScreen(navigator = navigator)
        }

        composable<AppDestination.ScheduleDetails> { backStackEntry ->
            val args = backStackEntry.toRoute<AppDestination.ScheduleDetails>()
            ScheduleScreen(
                id = args.scheduleId,
                navigator = navigator
            )
        }
    }
}
