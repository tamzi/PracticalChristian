@file:OptIn(ExperimentalTime::class)

package com.practicalchristian.app.feature.schedules.hub

import androidx.lifecycle.viewModelScope
import com.practicalchristian.app.core.domain.models.Outcome
import com.practicalchristian.app.core.domain.models.ScheduleDomain
import com.practicalchristian.app.core.domain.repository.PreferencesRepository
import com.practicalchristian.app.core.domain.repository.ScheduleRepository
import com.practicalchristian.app.core.ui.helpers.StatefulViewModel
import com.practicalchristian.app.core.ui.helpers.UiListState
import com.practicalchristian.app.core.ui.helpers.UiSuccessState
import com.practicalchristian.app.core.ui.helpers.toUserMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import timber.log.Timber
import javax.inject.Inject
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

data class SchedulesScreenUiState(
    val error: String? = null,
    val success: String? = null,
    val isLoading: Boolean = false,
    val listState: UiListState<List<ScheduleDomain>> = UiListState.Idle,
    val profilePictureUri: String? = null
)

@HiltViewModel
class SchedulesViewModel @Inject constructor(
    private val repository: ScheduleRepository,
    private val preferencesRepository: PreferencesRepository
) : StatefulViewModel<SchedulesScreenUiState>(SchedulesScreenUiState()) {

    init {
        observeSchedule()
        observeProfilePicture()
    }

    private fun observeSchedule() {
        update { copy(listState = UiListState.Loading) }
        viewModelScope.launch {
            update { copy(listState = UiListState.Loading) }
            repository.schedules.collectLatest {
                if (it.isNotEmpty()) {
                    update {
                        copy(listState = UiListState.Success(data = UiSuccessState.Data(data = it)))
                    }
                } else {
                    update {
                        copy(listState = UiListState.Success(data = UiSuccessState.Empty))
                    }
                }
            }
        }
    }

    private fun observeProfilePicture() {
        viewModelScope.launch {
            preferencesRepository.profilePictureUri.collectLatest { uri ->
                update { copy(profilePictureUri = uri) }
            }
        }
    }

    private fun markItemAsFinished(schedule: ScheduleDomain) {
        viewModelScope.launch {
            Timber.d("MARKING ITEM AS FINISHED \nSCHEDULE : $schedule")
            update { copy(isLoading = true) }
            val result = repository.addScheduleEntry(
                scheduleId = schedule.id,
                completedAt = Clock.System.now().toLocalDateTime(TimeZone.UTC)
            )
            Timber.d("MARKING ITEM AS FINISHED WITH RESULT \nSCHEDULE : $schedule")
            update { copy(isLoading = false) }
            when (result) {
                is Outcome.Failure -> {
                    val errorMessage = result.error.toUserMessage()
                    Timber.e("markItemAsFinished: Error - $errorMessage")
                    updateError(message = errorMessage)
                }
                is Outcome.Success -> {
                    Timber.d("markItemAsFinished: Success")
                    updateSuccess(message = null)
                }
            }
        }
    }

    private fun markItemAsUnfinished(schedule: ScheduleDomain) {
        viewModelScope.launch {
            Timber.d("MARKING ITEM AS UNIFINISHED \nSCHEDULE : $schedule")
            update { copy(isLoading = true) }
            val result = repository.removeScheduleEntry(scheduleId = schedule.id)
            update { copy(isLoading = false) }
            when (result) {
                is Outcome.Failure -> {
                    val errorMessage = result.error.toUserMessage()
                    Timber.e("markItemAsUnfinished: Error - $errorMessage")
                    updateError(message = errorMessage)
                }
                is Outcome.Success -> {
                    Timber.d("markItemAsUnfinished: Success")
                    updateSuccess(message = "")
                }
            }
        }
    }

    fun onItemSwiped(schedule: ScheduleDomain) {
        if (schedule.isComplete) markItemAsUnfinished(schedule = schedule)
        else markItemAsFinished(schedule = schedule)
    }

    fun updateError(message: String? = null) {
        update { copy(error = message) }
    }

    fun updateSuccess(message: String? = null) {
        update { copy(success = message) }
    }
}
