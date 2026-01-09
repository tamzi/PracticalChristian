package com.practicalchristian.app.core.domain.repository

import com.practicalchristian.app.core.domain.models.Book
import com.practicalchristian.app.core.domain.models.Outcome
import kotlinx.coroutines.flow.Flow

interface BooksRepository {

    val books: Flow<List<Book>>

    suspend fun getBookById(id: Int): Outcome<Flow<Book?>>
}
