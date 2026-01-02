package com.practicalchristian.app.core.localdatasource.sources.tags

import androidx.room.Dao
import androidx.room.Query
import com.practicalchristian.app.core.localdatasource.entity.TagEntity
import com.practicalchristian.app.core.localdatasource.sources.BaseDao
import kotlinx.coroutines.flow.Flow

@Dao
interface TagsDao : BaseDao<TagEntity> {

    @Query("SELECT * FROM tags")
    fun getAllFlow(): Flow<List<TagEntity>>

    @Query("SELECT * FROM tags")
    fun getAllList(): List<TagEntity>

    @Query("SELECT * FROM tags WHERE name = :query")
    fun search(query: String): Flow<List<TagEntity>>

    @Query("DELETE FROM tags WHERE id = :id")
    suspend fun deleteById(id: Int)

    @Query("DELETE FROM tags")
    suspend fun deleteAll()
}
