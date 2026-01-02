package com.practicalchristian.app.core.localdatasource.sources.book

import com.practicalchristian.app.core.localdatasource.entity.BookCache
import com.practicalchristian.app.core.localdatasource.helpers.LocalResult
import kotlinx.coroutines.flow.Flow

interface BookSources {

    val books: Flow<List<BookCache>>

    suspend fun insert(bookCache: BookCache): LocalResult<Boolean>

    suspend fun insert(book: List<BookCache>): LocalResult<Boolean>

    suspend fun getBookById(id: Int): LocalResult<Flow<BookCache?>>

    suspend fun searchBooks(query: String): LocalResult<Flow<List<BookCache>>>

    suspend fun deleteAllBooks(): LocalResult<Boolean>
}
