package com.practicalchristian.app.feature.books.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.practicalchristian.app.core.domain.models.Book
import com.practicalchristian.app.core.domain.models.Outcome
import com.practicalchristian.app.core.domain.repository.BooksRepository
import com.practicalchristian.app.core.ui.helpers.ItemState
import com.practicalchristian.app.core.ui.helpers.ItemState.Success
import com.practicalchristian.app.core.ui.helpers.StatefulViewModel
import com.practicalchristian.app.core.ui.helpers.toUserMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

data class BookUiState(
    val name: String = "", val result: ItemState<Book> = ItemState.Loading
)

@HiltViewModel
class BookViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: BooksRepository
) : StatefulViewModel<BookUiState>(BookUiState()) {

    private fun updateStateError(message: String?) {
        update { copy(result = ItemState.Error(message = message)) }
    }

    fun getItem(id: Int) {
        viewModelScope.launch {
            // UI layer sets Loading state before repository call
            update { copy(result = ItemState.Loading) }
            Timber.d("getItem: Setting Loading state before repository call")

            when (val result = repository.getBookById(id = id)) {
                is Outcome.Failure -> {
                    // Map DomainError to user-friendly message
                    val errorMessage = result.error.toUserMessage()
                    Timber.e("getItem: Repository failed - $errorMessage")
                    updateStateError(message = errorMessage)
                }

                is Outcome.Success -> {
                    Timber.d("getItem: Repository succeeded, collecting flow...")
                    result.value.collectLatest { book ->
                        if (book == null) {
                            Timber.d("getItem: Book not found")
                            updateStateError(message = "Book not found")
                        } else {
                            Timber.d("getItem: Book found - ${book.name}")
                            update {
                                copy(
                                    name = book.name, result = Success(item = book)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
