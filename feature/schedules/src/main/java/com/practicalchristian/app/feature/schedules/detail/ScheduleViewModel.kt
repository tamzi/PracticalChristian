package com.practicalchristian.app.feature.schedules.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.practicalchristian.app.core.domain.models.ScheduleDomain
import com.practicalchristian.app.core.domain.repository.ScheduleRepository
import com.practicalchristian.app.core.ui.helpers.ItemState
import com.practicalchristian.app.core.ui.helpers.StatefulViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDateTime
import timber.log.Timber
import javax.inject.Inject

data class ScheduleScreenUiState(
    val id: Int? = null,
    val scheduleState: ItemState<ScheduleDomain> = ItemState.Loading,
    val completedDateTime: LocalDateTime? = null,
    val isDatePickerOpen: Boolean = false,
)

@HiltViewModel
class ScheduleViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: ScheduleRepository,
) : StatefulViewModel<ScheduleScreenUiState>(ScheduleScreenUiState()) {

    fun updateScheduleId(id: Int) {
        update { copy(id = id) }
        getScheduleItems(id = id)
    }

    private fun getScheduleItems(id: Int) {
        viewModelScope.launch {
            repository.getScheduleEntry(scheduleId = id)
                .catch { e ->
                    Timber.e(e, "Error fetching schedule entry")
                    update { copy(scheduleState = ItemState.Error(e.message ?: "Unknown error")) }
                }
                .collectLatest {
                    update {
                        copy(
                            scheduleState = ItemState.Success(it),
                            completedDateTime = it.entry?.date
                        )
                    }
                }
        }
    }

    fun onValueChangeDatePickerOpen(value: Boolean) {
        update { copy(isDatePickerOpen = value) }
    }

    fun onValueChangeCompletedAt(dateTime: LocalDateTime?) {
        update {
            copy(
                completedDateTime = dateTime, isDatePickerOpen = false
            )
        }
        if (dateTime != null) completeScheduleItem()
    }

    private fun completeScheduleItem() {
        val id = state.value.id ?: return
        val time = state.value.completedDateTime ?: return
        viewModelScope.launch {
            repository.addScheduleEntry(scheduleId = id, completedAt = time)
        }
    }

    fun onClickToggleDatePicker() {
        update { copy(isDatePickerOpen = isDatePickerOpen.not()) }
    }
}
