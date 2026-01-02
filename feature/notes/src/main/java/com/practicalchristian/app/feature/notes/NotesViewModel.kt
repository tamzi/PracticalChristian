package com.practicalchristian.app.feature.notes

import androidx.lifecycle.viewModelScope
import com.practicalchristian.app.core.domain.models.NoteDomain
import com.practicalchristian.app.core.domain.repositories.NotesRepository
import com.practicalchristian.app.core.ui.helpers.StatefulViewModel
import com.practicalchristian.app.core.ui.helpers.UiListState
import com.practicalchristian.app.core.ui.helpers.UiSuccessState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

data class NotesScreenUiState(val listState: UiListState<List<NoteDomain>> = UiListState.Idle)

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val repository: NotesRepository
) : StatefulViewModel<NotesScreenUiState>(NotesScreenUiState()) {

    init {
        observerNotes()
    }

    private fun observerNotes() {
        viewModelScope.launch {
            repository.notes.collectLatest {
                if (it.isEmpty()) update { copy(listState = UiListState.Success(data = UiSuccessState.Empty)) }
                else update { copy(listState = UiListState.Success(data = UiSuccessState.Data(data = it))) }
            }
        }
    }
}
