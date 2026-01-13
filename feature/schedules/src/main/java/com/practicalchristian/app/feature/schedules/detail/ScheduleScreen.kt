@file:OptIn(ExperimentalTime::class)

package com.practicalchristian.app.feature.schedules.detail

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.practicalchristian.app.core.domain.models.Book
import com.practicalchristian.app.core.domain.models.ScheduleDomain
import com.practicalchristian.app.core.domain.models.ScheduleEntry
import com.practicalchristian.app.core.domain.models.ScheduleItem
import com.practicalchristian.app.core.ui.helpers.ItemState
import com.practicalchristian.app.core.ui.helpers.asFullDayString
import com.practicalchristian.app.core.ui.navigation.AppNavigator
import com.sacrament.ui.components.action.SacramentButton
import com.sacrament.ui.components.action.SacramentIconButton
import com.sacrament.ui.components.feedback.SacramentProgressIndicator
import com.sacrament.ui.components.feedback.SacramentProgressVariant
import com.sacrament.ui.components.input.PracticalChristianDatePicker
import com.sacrament.ui.components.navigation.SacramentTopAppBar
import com.sacrament.ui.foundation.Bar
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.foundation.icon.SacramentIcons
import com.sacrament.ui.patterns.SacramentScreenScaffold
import com.sacrament.ui.primitives.SacramentIcon
import com.sacrament.ui.primitives.SacramentText
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atTime
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

/**
 * Schedule details screen.

 */
@Composable
fun ScheduleScreen(
    id: Int,
    navigator: AppNavigator,
    viewModel: ScheduleViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(id) {
        viewModel.updateScheduleId(id = id)
    }

    ScheduleScreenContent(
        state = state,
        onNavigateBackClicked = { navigator.back() },
        onValueChangeCompletedAt = viewModel::onValueChangeCompletedAt,
        onClickToggleDatePicker = viewModel::onClickToggleDatePicker
    )
}

@Composable
fun ScheduleScreenContent(
    state: ScheduleScreenUiState,
    onNavigateBackClicked: () -> Unit,
    onValueChangeCompletedAt: (LocalDateTime?) -> Unit,
    onClickToggleDatePicker: () -> Unit,
) {
    val spacing = SacramentTheme.spacing
    PracticalChristianDatePicker(
        isDialogOpen = state.isDatePickerOpen, onValueChangeCompletedAt = onValueChangeCompletedAt
    )

    SacramentScreenScaffold(
        topBar = {
            SacramentTopAppBar(
                navigationIcon = {
                    SacramentIconButton(
                        imageVector = SacramentIcons.ArrowBack,
                        contentDescription = "",
                        onClick = onNavigateBackClicked
                    )
                },
                title = {
                    val scheduleState = state.scheduleState
                    if (scheduleState is ItemState.Success) {
                        SacramentText(
                            text = scheduleState.item.date.asFullDayString(),
                            style = SacramentTheme.typography.titleSmall
                        )
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .scrollable(
                    state = rememberScrollableState { 0f }, orientation = Orientation.Vertical
                )
                .fillMaxSize()
        ) {
            when (val result = state.scheduleState) {
                is ItemState.Error -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        SacramentIcon(
                            imageVector = SacramentIcons.Warning,
                            contentDescription = "error",
                            tint = SacramentTheme.colors.semantic.error,
                            modifier = Modifier
                                .padding(bottom = spacing.padding12)
                                .width(48.dp)
                                .height(48.dp)
                        )
                        SacramentText(
                            text = "Error loading schedule data",
                            color = SacramentTheme.colors.semantic.error,
                            style = SacramentTheme.typography.titleLarge,
                            modifier = Modifier.fillMaxWidth()
                        )
                        SacramentText(
                            text = result.message
                                ?: "Failed to load schedule. Please try again later.",
                            color = SacramentTheme.colors.semantic.error,
                            style = SacramentTheme.typography.bodyLarge,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    top = spacing.padding8,
                                    start = spacing.padding32,
                                    end = spacing.padding32
                                )
                        )
                    }
                }

                ItemState.Loading -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        SacramentProgressIndicator(variant = SacramentProgressVariant.Circular)
                    }
                }

                is ItemState.Success -> {
                    val schedule = result.item
                    SacramentText(
                        text = "STATUS : ${schedule.status.label}",
                        style = SacramentTheme.typography.bodyMedium
                    )
                    AnimatedVisibility(visible = schedule.isComplete.not()) {
                        SacramentButton(
                            text = "Complete",
                            onClick = onClickToggleDatePicker,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(spacing.padding16)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, name = "ScheduleScreen - Loading")
@Composable
private fun ScheduleScreenLoadingPreview() {
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        ScheduleScreenContent(
            state = ScheduleScreenUiState(
                scheduleState = ItemState.Loading
            ), onNavigateBackClicked = {}, onValueChangeCompletedAt = {}, onClickToggleDatePicker = {})
    }
}

@Preview(showBackground = true, name = "ScheduleScreen - Completed Schedule")
@Composable
private fun ScheduleScreenCompletedPreview() {
    val now = Clock.System.now().toLocalDateTime(TimeZone.UTC)
    val completedSchedule = ScheduleDomain(
        id = 1,
        date = now.date.minus(1, DateTimeUnit.DAY).atTime(12, 0),
        start = ScheduleItem(Book(1, "Genesis", 50), 1),
        end = ScheduleItem(Book(1, "Genesis", 50), 3),
        entry = ScheduleEntry("entry1", 1, now.date.minus(1, DateTimeUnit.DAY).atTime(12, 0))
    )

    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        ScheduleScreenContent(
            state = ScheduleScreenUiState(
                scheduleState = ItemState.Success(completedSchedule)
            ), onNavigateBackClicked = {}, onValueChangeCompletedAt = {}, onClickToggleDatePicker = {})
    }
}

@Preview(showBackground = true, name = "ScheduleScreen - Pending Schedule")
@Composable
private fun ScheduleScreenPendingPreview() {
    val now = Clock.System.now().toLocalDateTime(TimeZone.UTC)
    val pendingSchedule = ScheduleDomain(
        id = 2,
        date = now,
        start = ScheduleItem(Book(1, "Genesis", 50), 4),
        end = ScheduleItem(Book(1, "Genesis", 50), 6),
        entry = null
    )

    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        ScheduleScreenContent(
            state = ScheduleScreenUiState(
                scheduleState = ItemState.Success(pendingSchedule)
            ), onNavigateBackClicked = {}, onValueChangeCompletedAt = {}, onClickToggleDatePicker = {})
    }
}

@Preview(showBackground = true, name = "ScheduleScreen - Future Schedule")
@Composable
private fun ScheduleScreenFuturePreview() {
    val now = Clock.System.now().toLocalDateTime(TimeZone.UTC)
    val futureSchedule = ScheduleDomain(
        id = 3,
        date = now.date.plus(1, DateTimeUnit.DAY).atTime(12, 0),
        start = ScheduleItem(Book(2, "Exodus", 40), 1),
        end = ScheduleItem(Book(2, "Exodus", 40), 2),
        entry = null
    )

    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        ScheduleScreenContent(
            state = ScheduleScreenUiState(
                scheduleState = ItemState.Success(futureSchedule)
            ), onNavigateBackClicked = {}, onValueChangeCompletedAt = {}, onClickToggleDatePicker = {})
    }
}

@Preview(showBackground = true, name = "ScheduleScreen - With Date Picker Open")
@Composable
private fun ScheduleScreenWithDatePickerPreview() {
    val now = Clock.System.now().toLocalDateTime(TimeZone.UTC)
    val pendingSchedule = ScheduleDomain(
        id = 2,
        date = now,
        start = ScheduleItem(Book(1, "Genesis", 50), 4),
        end = ScheduleItem(Book(1, "Genesis", 50), 6),
        entry = null
    )

    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        ScheduleScreenContent(
            state = ScheduleScreenUiState(
                scheduleState = ItemState.Success(pendingSchedule), isDatePickerOpen = true
            ), onNavigateBackClicked = {}, onValueChangeCompletedAt = {}, onClickToggleDatePicker = {})
    }
}

@Preview(showBackground = true, name = "ScheduleScreen - Error State")
@Composable
private fun ScheduleScreenErrorPreview() {
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        ScheduleScreenContent(
            state = ScheduleScreenUiState(
                scheduleState = ItemState.Error("Unable to load schedule data")
            ),
            onNavigateBackClicked = {},
            onValueChangeCompletedAt = {},
            onClickToggleDatePicker = {})
    }
}

@Preview(showBackground = true, name = "PracticalChristianDatePicker - Date and Time Selection")
@Composable
private fun PracticalChristianDatePickerWithTimePreview() {
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        PracticalChristianDatePicker(
            isDialogOpen = true, onValueChangeCompletedAt = {})
    }
}
