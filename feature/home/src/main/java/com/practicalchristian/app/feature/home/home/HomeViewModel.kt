@file:OptIn(kotlin.time.ExperimentalTime::class)

package com.practicalchristian.app.feature.home.home

import com.sacrament.ui.foundation.icon.SacramentIcons
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.viewModelScope
import com.practicalchristian.app.core.domain.repository.PreferencesRepository
import com.practicalchristian.app.core.ui.helpers.StatefulViewModel
import com.practicalchristian.app.core.ui.navigation.AppDestination
import com.practicalchristian.app.core.ui.navigation.NavigationEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.todayIn
import javax.inject.Inject

/**
 * Home tab destinations for internal navigation.
 */
enum class HomeDestination(val icon: ImageVector, val value: String) {
    SCHEDULE(icon = SacramentIcons.Home, value = "Home"),
    BOOKS(icon = SacramentIcons.LocalLibrary, value = "Books"),
    NOTES(icon = SacramentIcons.Bookmark, value = "Library"),
}

/**
 * Calendar day representation for the week view.
 */
data class DayCalendar(
    val date: LocalDate,
    val dayOfWeek: String,
    val dayOfMonth: Int
)

/**
 * Current reading progress for a book.
 */
data class ReadingProgress(
    val bookName: String,
    val chaptersCompleted: Int,
    val totalChapters: Int,
    val progressPercentage: Float
)

/**
 * Reading plan representation.
 */
data class ReadingPlan(
    val id: Int,
    val title: String,
    val description: String,
    val daysCount: Int
)

/**
 * UI state for HomeScreen.
 */
data class HomeScreenUiState(
    val destination: HomeDestination = HomeDestination.SCHEDULE,
    val userName: String = "User",
    val profilePictureUri: String? = null,
    val selectedDate: LocalDate = kotlin.time.Clock.System.todayIn(TimeZone.currentSystemDefault()),
    val weekDays: List<DayCalendar> = emptyList(),
    val daysStreak: Int = 0,
    val chaptersCompleted: Int = 0,
    val currentReading: ReadingProgress? = null,
    val availablePlans: List<ReadingPlan> = emptyList()
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val preferencesRepository: PreferencesRepository
) :
    StatefulViewModel<HomeScreenUiState>(HomeScreenUiState()) {

    private val _navigationEvents = Channel<NavigationEvent>(Channel.BUFFERED)
    val navigationEvents = _navigationEvents.receiveAsFlow()

    init {
        observeUserName()
        loadInitialData()
    }

    private fun observeUserName() {
        viewModelScope.launch {
            preferencesRepository.userName.collectLatest { name ->
                update { copy(userName = name?.ifBlank { null } ?: "User") }
            }
        }
    }

    /**
     * Load initial data for the home screen.
     * TODO: Replace with actual repository calls.
     */
    private fun loadInitialData() {
        // Generate week calendar
        val today = kotlin.time.Clock.System.todayIn(TimeZone.currentSystemDefault())
        val weekDays = generateWeekDays(today)
        
        update {
            copy(
                selectedDate = today,
                weekDays = weekDays,
                daysStreak = 7, // Placeholder
                chaptersCompleted = 12, // Placeholder
                // currentReading and availablePlans can be loaded from repositories
            )
        }
    }

    /**
     * Generate a list of days for the current week.
     */
    private fun generateWeekDays(centerDate: LocalDate): List<DayCalendar> {
        val days = mutableListOf<DayCalendar>()
        val dayNames = listOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat")
        
        // Generate 7 days centered around the given date
        for (i in -3..3) {
            val date = centerDate.plus(DatePeriod(days = i))
            days.add(
                DayCalendar(
                    date = date,
                    dayOfWeek = dayNames[date.dayOfWeek.ordinal],
                    dayOfMonth = date.day
                )
            )
        }
        
        return days
    }

    fun onDestinationClicked(destination: HomeDestination) {
        update { copy(destination = destination) }
    }

    fun onDaySelected(date: LocalDate) {
        val newWeekDays = generateWeekDays(date)
        update {
            copy(
                selectedDate = date,
                weekDays = newWeekDays
            )
        }
    }

    fun navigateToProfile() {
        viewModelScope.launch {
            _navigationEvents.send(NavigationEvent.NavigateTo(AppDestination.Profile))
        }
    }

    fun navigateToSchedules() {
        viewModelScope.launch {
            _navigationEvents.send(NavigationEvent.NavigateTo(AppDestination.Schedules))
        }
    }

    fun navigateToBooks() {
        viewModelScope.launch {
            _navigationEvents.send(NavigationEvent.NavigateTo(AppDestination.Books))
        }
    }

    fun navigateToNotes() {
        viewModelScope.launch {
            _navigationEvents.send(NavigationEvent.NavigateTo(AppDestination.Notes))
        }
    }

    fun navigateToSettings() {
        viewModelScope.launch {
            _navigationEvents.send(NavigationEvent.NavigateTo(AppDestination.Settings))
        }
    }
}
