package com.sacrament.ui.components.input

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.sacrament.ui.components.action.SacramentButton
import com.sacrament.ui.components.action.SacramentButtonVariant
import java.util.Calendar
import java.util.Date as JavaDate
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.atTime

/**
 * Bridge component for date and time picking using Material3 DatePicker/TimePicker.
 * 
 * This is a temporary bridge until we can implement Material-free pickers.
 * Wraps Material3 DatePicker and TimePicker with Sacrament styling.
 */
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

    var showTimePickerDialog by remember { mutableStateOf(false) }
    var selectedDateMillis by remember { mutableStateOf<Long?>(null) }

    // Step 1: Date Selection Dialog
    if (isDialogOpen && !showTimePickerDialog) {
        DatePickerDialog(
            onDismissRequest = {
                onValueChangeCompletedAt.invoke(null)
            },
            confirmButton = {
                SacramentButton(
                    text = "Next: Select Time",
                    onClick = {
                        val dateMillis = dateState.selectedDateMillis
                        if (dateMillis != null) {
                            selectedDateMillis = dateMillis
                            showTimePickerDialog = true
                        } else {
                            onValueChangeCompletedAt.invoke(null)
                        }
                    }
                )
            },
            dismissButton = {
                SacramentButton(
                    text = "Cancel",
                    onClick = { onValueChangeCompletedAt.invoke(null) },
                    variant = SacramentButtonVariant.Outlined
                )
            }
        ) {
            DatePicker(state = dateState)
        }
    }

    // Step 2: Time Selection Dialog
    if (showTimePickerDialog) {
        TimePickerDialog(
            onDismissRequest = {
                showTimePickerDialog = false
                onValueChangeCompletedAt.invoke(null)
            },
            confirmButton = {
                SacramentButton(
                    text = "Complete",
                    onClick = {
                        val dateMillis = selectedDateMillis
                        if (dateMillis != null) {
                            val cal = Calendar.getInstance()
                            cal.time = JavaDate(dateMillis)
                            val selectedDate = LocalDate(
                                year = cal[Calendar.YEAR],
                                monthNumber = cal[Calendar.MONTH] + 1,
                                dayOfMonth = cal[Calendar.DAY_OF_MONTH]
                            )
                            val selectedDateTime =
                                selectedDate.atTime(timeState.hour, timeState.minute)
                            onValueChangeCompletedAt.invoke(selectedDateTime)
                        } else {
                            onValueChangeCompletedAt.invoke(null)
                        }
                        showTimePickerDialog = false
                    }
                )
            },
            dismissButton = {
                SacramentButton(
                    text = "Cancel",
                    onClick = {
                        showTimePickerDialog = false
                        onValueChangeCompletedAt.invoke(null)
                    },
                    variant = SacramentButtonVariant.Outlined
                )
            },
            title = "Select Completion Time"
        ) {
            TimePicker(state = timeState)
        }
    }
}

/**
 * Custom TimePickerDialog component using Material3 design.
 * Bridge component until we can implement Material-free pickers.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerDialog(
    onDismissRequest: () -> Unit,
    confirmButton: @Composable () -> Unit,
    dismissButton: @Composable (() -> Unit)? = null,
    title: String? = null,
    content: @Composable () -> Unit,
) {
    Dialog(onDismissRequest = onDismissRequest) {
        androidx.compose.material3.DatePickerDialog(
            onDismissRequest = onDismissRequest,
            confirmButton = confirmButton,
            dismissButton = dismissButton
        ) {
            if (title != null) {
                androidx.compose.material3.Text(
                    text = title,
                    style = androidx.compose.material3.MaterialTheme.typography.headlineSmall,
                    modifier = androidx.compose.ui.Modifier.padding(bottom = 16.dp)
                )
            }
            content()
        }
    }
}
