package com.practicalchristian.app.core.localdatasource.sources.notes

import androidx.room.Dao
import androidx.room.Query
import com.practicalchristian.app.core.localdatasource.entity.NoteEntity
import com.practicalchristian.app.core.localdatasource.sources.BaseDao
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao : BaseDao<NoteEntity> {

    @Query("SELECT * FROM notes")
    fun getAllFlow(): Flow<List<NoteEntity>>

    @Query("SELECT * FROM notes")
    fun getAll(): List<NoteEntity>

    @Query("SELECT * FROM notes WHERE id = :id")
    fun getFlow(id: String): Flow<NoteEntity>

    @Query("SELECT * FROM notes WHERE id = :id")
    fun get(id: String): NoteEntity

    @Query("SELECT * FROM notes WHERE title LIKE '%' || :query || '%' OR content LIKE '%' || :query || '%'")
    fun search(query: String): Flow<List<NoteEntity>>

    @Query("DELETE FROM notes WHERE id = :id")
    suspend fun deleteById(id: String)

    @Query("DELETE FROM notes")
    suspend fun deleteAll()
}
