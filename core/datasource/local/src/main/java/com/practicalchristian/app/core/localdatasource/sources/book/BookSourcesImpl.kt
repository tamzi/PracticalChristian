package com.practicalchristian.app.core.localdatasource.sources.book

import com.practicalchristian.app.core.localdatasource.entity.BookCache
import com.practicalchristian.app.core.localdatasource.helpers.LocalResult
import com.practicalchristian.app.core.localdatasource.helpers.safeTransaction
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

class BookSourcesImpl @Inject constructor(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val dao: BookDao
) : BookSources {

    override val books: Flow<List<BookCache>>
        get() = dao.getBooks().mapLatest { books -> books.map { book -> book.toCache() } }

    override suspend fun insert(bookCache: BookCache): LocalResult<Boolean> = safeTransaction {
        val entity = bookCache.toEntity()
        dao.insert(entity)
        true
    }

    override suspend fun insert(book: List<BookCache>): LocalResult<Boolean> = safeTransaction {
        val entities = book.map { it.toEntity() }
        dao.insert(entities)
        true
    }

    override suspend fun getBookById(id: Int): LocalResult<Flow<BookCache?>> = safeTransaction {
        dao.getBookById(id = id).mapLatest { it?.toCache() }
    }

    override suspend fun searchBooks(query: String): LocalResult<Flow<List<BookCache>>> =
        safeTransaction {
            dao.searchBooks(query).mapLatest { list -> list.map { it.toCache() } }
        }

    override suspend fun deleteAllBooks(): LocalResult<Boolean> = safeTransaction {
        dao.deleteAllBooks()
        true
    }
}
