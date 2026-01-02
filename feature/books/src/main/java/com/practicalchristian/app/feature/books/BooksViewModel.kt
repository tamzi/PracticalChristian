package com.practicalchristian.app.feature.books

import androidx.lifecycle.viewModelScope
import com.practicalchristian.app.core.domain.models.Book
import com.practicalchristian.app.core.domain.repositories.BooksRepository
import com.practicalchristian.app.core.ui.helpers.StatefulViewModel
import com.practicalchristian.app.core.ui.helpers.UiListState
import com.practicalchristian.app.core.ui.helpers.UiSuccessState
import com.practicalchristian.app.core.ui.helpers.ViewType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

data class BooksScreenUiState(
    val view: ViewType = ViewType.LIST, val listState: UiListState<List<Book>> = UiListState.Idle
)

@HiltViewModel
class BooksViewModel @Inject constructor(
    private val repository: BooksRepository
) : StatefulViewModel<BooksScreenUiState>(BooksScreenUiState()) {

    init {
        observeBooksList()
    }

    private fun observeBooksList() {
        viewModelScope.launch {
            update { copy(listState = UiListState.Loading) }
            repository.books.collectLatest {
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

    fun toggleViewType() {
        val update = when (state.value.view) {
            ViewType.GRID -> ViewType.LIST
            ViewType.LIST -> ViewType.GRID
        }
        update { copy(view = update) }
    }
}
