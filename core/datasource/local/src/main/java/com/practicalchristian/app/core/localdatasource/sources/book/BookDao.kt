package com.practicalchristian.app.core.localdatasource.sources.book

import androidx.room.Dao
import androidx.room.Query
import com.practicalchristian.app.core.localdatasource.entity.BookEntity
import com.practicalchristian.app.core.localdatasource.sources.BaseDao
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao : BaseDao<BookEntity> {

    @Query("SELECT * FROM books")
    fun getBooks(): Flow<List<BookEntity>>

    @Query("SELECT * FROM books WHERE id = :id")
    fun getBookById(id: Int): Flow<BookEntity?>

    @Query("SELECT * FROM books WHERE name LIKE '%' || :query || '%'")
    fun searchBooks(query: String): Flow<List<BookEntity>>

    @Query("SELECT * FROM books WHERE name = :name")
    fun getBook(name: String): Flow<BookEntity>

    @Query("DELETE FROM books")
    fun deleteAllBooks()
}
