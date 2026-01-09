package com.practicalchristian.app.feature.notes.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.practicalchristian.app.core.domain.models.NoteDomain
import com.practicalchristian.app.core.domain.models.Outcome
import com.practicalchristian.app.core.domain.repository.NotesRepository
import com.practicalchristian.app.core.ui.helpers.StatefulViewModel
import com.practicalchristian.app.core.ui.helpers.toUserMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

// Ensure this ScreenModel is bound in the Hilt DI graph using a @Binds in ScreensModule:
// @Binds
// @IntoMap
// @ScreenModelKey(NoteScreenModel::class)
// abstract fun bindNoteScreenModel(impl: NoteScreenModel): ScreenModel

data class NoteScreenUiState(
    val id: String? = null,
    val note: NoteDomain? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
)

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: NotesRepository
) : StatefulViewModel<NoteScreenUiState>(NoteScreenUiState()) {

    init {
        observeState()
    }

    private fun observeState() {
        viewModelScope.launch {
            state.map { it.id }.distinctUntilChanged().collectLatest { id ->
                id?.let {
                    getNoteById(id = it)
                }
            }
        }
    }

    private fun getNoteById(id: String) {
        update { copy(isLoading = true, error = null) }
        viewModelScope.launch {
            when (val result = repository.getNoteById(id = id)) {
                is Outcome.Failure -> {
                    val errorMessage = result.error.toUserMessage()
                    update { copy(isLoading = false, error = errorMessage) }
                    Timber.e("getNoteById: Failed - $errorMessage")
                }

                is Outcome.Success -> {
                    result.value.collectLatest { note ->
                        update {
                            copy(
                                note = note,
                                isLoading = false,
                                error = null
                            )
                        }
                        Timber.d("getNoteById: Note received - ${note.title}")
                    }
                }
            }
        }
    }

    fun updateNoteId(id: String) {
        update { copy(id = id) }
    }
}
