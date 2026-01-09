package com.practicalchristian.app.feature.notes.edit

import android.graphics.Color
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.practicalchristian.app.core.domain.models.Book
import com.practicalchristian.app.core.domain.models.NoteDomain
import com.practicalchristian.app.core.domain.models.NoteType
import com.practicalchristian.app.core.domain.models.Outcome
import com.practicalchristian.app.core.domain.models.TagDomain
import com.practicalchristian.app.core.domain.repository.BooksRepository
import com.practicalchristian.app.core.domain.repository.NotesRepository
import com.practicalchristian.app.core.domain.repository.TagsRepository
import com.practicalchristian.app.core.ui.helpers.StatefulViewModel
import com.practicalchristian.app.core.ui.helpers.UiListState
import com.practicalchristian.app.core.ui.helpers.UiSuccessState
import com.practicalchristian.app.core.ui.helpers.toUserMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

enum class EditNoteValue {
    TITLE, CONTENT
}

fun getGeneratedColors(): List<String> {
    val list = mutableListOf<String>()
    repeat(9) { list.add(String.format("#%06X", 0xFFFFFF and generateRgb())) }
    return list
}

private fun generateRgb(): Int {
    val (red, green, blue) = Triple(
        first = (0 until 128).random().plus(127),
        second = (0 until 128).random().plus(127),
        third = (0 until 128).random().plus(127)
    )
    return Color.rgb(red, green, blue)
}

data class TagDetail(
    val isShowing: Boolean = false,
    val isCreating: Boolean = false,
    val colors: List<String> = getGeneratedColors(),
    val name: String = "",
    val color: String = ""
) {
    val isValidTagData: Boolean
        get() = name.isNotBlank() and color.isNotBlank()
}

data class NoteDetail(
    val title: String = "", val content: String = ""
)

data class EditNoteScreenUiState(
    val id: String? = null,
    val selectedNote: NoteDomain? = null,
    val tagsState: UiListState<List<TagDomain>> = UiListState.Idle,
    val booksState: UiListState<List<Book>> = UiListState.Idle,
    val tags: List<TagDomain> = emptyList(),
    val tagDetail: TagDetail = TagDetail(),
    val noteDetail: NoteDetail = NoteDetail(),
    val error: String? = null,
    val navigateBack: Boolean = false,
    val isLoading: Boolean = false,
)

@HiltViewModel
class EditNoteViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val notesRepository: NotesRepository,
    private val tagsRepository: TagsRepository,
    private val booksRepository: BooksRepository
) : StatefulViewModel<EditNoteScreenUiState>(initial = EditNoteScreenUiState()) {

    init {
        observeTags()
        observeBooks()
    }

    /**
     * Load an existing note for editing.
     * Loads the note data ONCE to prevent overwriting unsaved user edits.
     */
    fun loadNote(id: String) {
        update { copy(id = id, isLoading = true) }
        viewModelScope.launch {
            when (val result = notesRepository.getNoteById(id = id)) {
                is Outcome.Failure -> {
                    val errorMessage = result.error.toUserMessage()
                    Timber.e("loadNote: Failed to load note - $errorMessage")
                    update { copy(error = errorMessage, isLoading = false) }
                }
                is Outcome.Success -> {
                    // Collect only the first emission to avoid overwriting user edits
                    val note = result.value.first()
                    Timber.d("loadNote: Note loaded - ${note.title}")
                    update {
                        copy(
                            selectedNote = note,
                            noteDetail = NoteDetail(
                                title = note.title,
                                content = note.content
                            ),
                            tags = note.tags,
                            isLoading = false
                        )
                    }
                }
            }
        }
    }

    private fun observeTags() {
        update { copy(tagsState = UiListState.Loading) }
        viewModelScope.launch {
            tagsRepository.tags.collectLatest {
                if (it.isEmpty()) update { copy(tagsState = UiListState.Success(data = UiSuccessState.Empty)) }
                else update { copy(tagsState = UiListState.Success(data = UiSuccessState.Data(data = it))) }
            }
        }
    }

    private fun observeBooks() {
        update { copy(booksState = UiListState.Loading) }
        viewModelScope.launch {
            booksRepository.books.collectLatest {
                if (it.isEmpty()) update { copy(booksState = UiListState.Success(data = UiSuccessState.Empty)) }
                else update { copy(booksState = UiListState.Success(data = UiSuccessState.Data(data = it))) }
            }
        }
    }

    private fun updateValueTitle(value: String) {
        update { copy(noteDetail = noteDetail.copy(title = value)) }
    }

    private fun updateValueContent(value: String) {
        update { copy(noteDetail = noteDetail.copy(content = value)) }
    }

    fun onValueChange(value: String, type: EditNoteValue) {
        when (type) {
            EditNoteValue.TITLE -> updateValueTitle(value = value)
            EditNoteValue.CONTENT -> updateValueContent(value = value)
        }
    }

    fun onClickTag(tag: TagDomain) {
        val list = state.value.tags.toMutableList()
        if (list.contains(tag)) list.remove(tag)
        else list.add(tag)
        update { copy(tags = list) }
    }

    fun onToggleBottomSheetTag() {
        update { copy(tagDetail = tagDetail.copy(isShowing = tagDetail.isShowing.not())) }
    }

    fun onTagCreateToggle() {
        update { copy(tagDetail = tagDetail.copy(isCreating = tagDetail.isCreating.not())) }
    }

    fun onClickTagRegenerateColors() {
        update { copy(tagDetail = tagDetail.copy(colors = getGeneratedColors())) }
    }

    fun onValueChangeTagName(value: String) {
        update { copy(tagDetail = tagDetail.copy(name = value)) }
    }

    fun onValueChangeTagColor(value: String) {
        update { copy(tagDetail = tagDetail.copy(color = value)) }
    }

    fun onClickTagInsert() {
        val item = state.value.tagDetail
        val insert = TagDomain(0, item.name, item.color)
        viewModelScope.launch {
            tagsRepository.insert(tag = insert)
            onTagCreateToggle()
            update { copy(tagDetail = tagDetail.copy(name = "", color = "")) }
        }
    }

    fun onClickNoteSave(content: String) {
        Timber.d("CONTENT -> \n$content")
        updateValueContent(value = content)
        if (content.isNotBlank()) if (state.value.selectedNote == null) insertNote()
        else updateNote()
    }

    private fun insertNote() {
        Timber.d("insertNote: Starting note insertion")
        update { copy(isLoading = true) }
        viewModelScope.launch {
            val result = notesRepository.insert(
                title = state.value.noteDetail.title,
                content = state.value.noteDetail.content,
                type = NoteType.NOTE,
                tags = state.value.tags,
                startBook = 1,
                startChapter = 1,
                startVerse = 1,
                endBook = 1,
                endChapter = 1,
                endVerse = 2
            )
            Timber.d("insertNote: Repository call completed")
            when (result) {
                is Outcome.Failure -> {
                    val errorMessage = result.error.toUserMessage()
                    Timber.e("insertNote: Error - $errorMessage")
                    update { copy(error = errorMessage, isLoading = false) }
                }
                is Outcome.Success -> {
                    Timber.d("insertNote: Success - navigating back")
                    update { copy(navigateBack = true, isLoading = false) }
                }
            }
        }
    }

    private fun updateNote() {
        Timber.d("updateNote: Starting note update")
        update { copy(isLoading = true) }
        viewModelScope.launch {
            val currentNote = state.value.selectedNote
            if (currentNote == null) {
                Timber.e("updateNote: No note selected for update")
                update { copy(error = "No note selected for update", isLoading = false) }
                return@launch
            }

            val result = notesRepository.update(
                note = currentNote.copy(
                    title = state.value.noteDetail.title,
                    content = state.value.noteDetail.content,
                    tags = state.value.tags
                )
            )
            Timber.d("updateNote: Repository call completed")
            when (result) {
                is Outcome.Failure -> {
                    val errorMessage = result.error.toUserMessage()
                    Timber.e("updateNote: Error - $errorMessage")
                    update { copy(error = errorMessage, isLoading = false) }
                }
                is Outcome.Success -> {
                    Timber.d("updateNote: Success - navigating back")
                    update { copy(navigateBack = true, isLoading = false) }
                }
            }
        }
    }
}
