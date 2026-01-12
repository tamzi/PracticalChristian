@file:OptIn(kotlin.time.ExperimentalTime::class)

package com.practicalchristian.app.feature.schedules

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import com.sacrament.ui.components.action.SacramentButton
import com.sacrament.ui.foundation.icon.SacramentIcons
import com.sacrament.ui.components.action.SacramentButtonVariant
import com.sacrament.ui.foundation.icon.SacramentIcons
import com.sacrament.ui.components.action.SacramentIconButton
import com.sacrament.ui.foundation.icon.SacramentIcons
import com.sacrament.ui.components.feedback.SacramentProgressIndicator
import com.sacrament.ui.foundation.icon.SacramentIcons
import com.sacrament.ui.components.feedback.SacramentProgressVariant
import com.sacrament.ui.foundation.icon.SacramentIcons
import com.sacrament.ui.components.navigation.SacramentTopAppBar
import com.sacrament.ui.foundation.icon.SacramentIcons
import com.sacrament.ui.components.surface.SacramentCard
import com.sacrament.ui.foundation.icon.SacramentIcons
import com.sacrament.ui.components.surface.SacramentCardColors
import com.sacrament.ui.foundation.icon.SacramentIcons
import com.sacrament.ui.components.surface.SacramentCardDefaults
import com.sacrament.ui.foundation.icon.SacramentIcons
import com.sacrament.ui.patterns.SacramentScreenScaffold
import com.sacrament.ui.foundation.icon.SacramentIcons
import com.sacrament.ui.primitives.SacramentIcon
import com.sacrament.ui.foundation.icon.SacramentIcons
import com.sacrament.ui.primitives.SacramentText
import com.sacrament.ui.foundation.icon.SacramentIcons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
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
import com.practicalchristian.app.core.ui.helpers.asLocalDateTime
import com.practicalchristian.app.core.ui.navigation.AppNavigator
import com.sacrament.ui.foundation.Bar
import com.sacrament.ui.foundation.icon.SacramentIcons
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.foundation.icon.SacramentIcons
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atTime
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock

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
                        text = buildAnnotatedString {
                            append("STATUS :")
                            append(schedule.status.label)
                        }.text,
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PracticalChristianDatePicker(
    isDialogOpen: Boolean,
    onValueChangeCompletedAt: (LocalDateTime?) -> Unit,
) {
    val dateState = rememberDatePickerState()
    val timeState = rememberTimePickerState(
        initialHour = 12, initialMinute = 0, is24Hour = false
    )

    val (showTimePickerDialog, setShowTimePickerDialog) = remember { mutableStateOf(false) }
    val (selectedDateMillis, setSelectedDateMillis) = remember { mutableStateOf<Long?>(null) }

    // Step 1: Date Selection Dialog
    if (isDialogOpen && !showTimePickerDialog) {
        DatePickerDialog(onDismissRequest = {
            onValueChangeCompletedAt.invoke(null)
        }, confirmButton = {
            SacramentButton(
                text = "Next: Select Time",
                onClick = {
                    val dateMillis = dateState.selectedDateMillis
                    if (dateMillis != null) {
                        setSelectedDateMillis(dateMillis)
                        setShowTimePickerDialog(true)
                    } else {
                        onValueChangeCompletedAt.invoke(null)
                    }
                }
            )
        }, dismissButton = {
            SacramentButton(
                text = "Cancel",
                onClick = { onValueChangeCompletedAt.invoke(null) },
                variant = SacramentButtonVariant.Outlined
            )
        }) {
            DatePicker(state = dateState)
        }
    }

    // Step 2: Time Selection Dialog
    if (showTimePickerDialog) {
        TimePickerDialog(
            onDismissRequest = {
            setShowTimePickerDialog(false)
            onValueChangeCompletedAt.invoke(null)
        }, confirmButton = {
            SacramentButton(
                text = "Complete",
                onClick = {
                    val dateMillis = selectedDateMillis
                    if (dateMillis != null) {
                        val selectedDate = dateMillis.asLocalDateTime()?.date
                        val selectedDateTime =
                            selectedDate?.atTime(timeState.hour, timeState.minute)
                        onValueChangeCompletedAt.invoke(selectedDateTime)
                    } else {
                        onValueChangeCompletedAt.invoke(null)
                    }
                    setShowTimePickerDialog(false)
                }
            )
        }, dismissButton = {
            SacramentButton(
                text = "Cancel",
                onClick = {
                    setShowTimePickerDialog(false)
                    onValueChangeCompletedAt.invoke(null)
                },
                variant = SacramentButtonVariant.Outlined
            )
        }, title = "Select Completion Time"
        ) {
            TimePicker(state = timeState)
        }
    }
}

// Custom TimePickerDialog component using Material3 design
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerDialog(
    onDismissRequest: () -> Unit,
    confirmButton: @Composable () -> Unit,
    dismissButton: @Composable (() -> Unit)? = null,
    title: String = "Select Time",
    content: @Composable () -> Unit
) {
    val spacing = SacramentTheme.spacing
    androidx.compose.ui.window.Dialog(
        onDismissRequest = onDismissRequest
    ) {
        SacramentCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(spacing.padding16),
            contentPadding = androidx.compose.foundation.layout.PaddingValues(spacing.padding24)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SacramentText(
                    text = title,
                    style = SacramentTheme.typography.headlineSmall,
                    modifier = Modifier.padding(bottom = spacing.padding20)
                )

                content()

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = spacing.padding24),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    dismissButton?.invoke()
                    Spacer(modifier = Modifier.width(spacing.padding8))
                    confirmButton()
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

@Preview(showBackground = true, name = "TimePicker - Clock Style")
@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun TimePickerClockPreview() {
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        val timeState = rememberTimePickerState(
            initialHour = 14, initialMinute = 30, is24Hour = false
        )
        TimePicker(state = timeState)
    }
}

@Preview(showBackground = true, name = "TimeInput - Keyboard Style")
@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun TimeInputKeyboardPreview() {
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        val timeState = rememberTimePickerState(
            initialHour = 14, initialMinute = 30, is24Hour = false
        )
        TimeInput(state = timeState)
    }
}

@Preview(showBackground = true, name = "TimePicker - 24 Hour Format")
@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun TimePicker24HourPreview() {
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        val timeState = rememberTimePickerState(
            initialHour = 14, initialMinute = 30, is24Hour = true
        )
        TimePicker(state = timeState)
    }
}

@Preview(showBackground = true, name = "TimePickerDialog - Step 2")
@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun TimePickerDialogPreview() {
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        val timeState = rememberTimePickerState(
            initialHour = 14, initialMinute = 30, is24Hour = false
        )

        TimePickerDialog(
            onDismissRequest = {}, confirmButton = {
            TextButton(onClick = {}) {
                Text("Complete")
            }
        }, dismissButton = {
            TextButton(onClick = {}) {
                Text("Cancel")
            }
        }, title = "Select Completion Time"
        ) {
            TimePicker(state = timeState)
        }
    }
}
