package com.practicalchristian.app.core.localdatasource.sources.schedule

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.practicalchristian.app.core.localdatasource.entity.ScheduleEntity
import com.practicalchristian.app.core.localdatasource.entity.relations.ScheduleWithBookEntity
import com.practicalchristian.app.core.localdatasource.sources.BaseDao
import kotlinx.coroutines.flow.Flow

@Dao
interface ScheduleDao : BaseDao<ScheduleEntity> {

    @Query("SELECT * from schedules")
    fun getAllFlow(): Flow<List<ScheduleEntity>>

    @Query("SELECT * from schedules")
    suspend fun getAll(): List<ScheduleEntity>

    @Query("SELECT * FROM schedules WHERE id = :id")
    fun getFlow(id: Int): Flow<ScheduleEntity>

    @Query("SELECT * FROM schedules WHERE id = :id")
    suspend fun get(id: Int): ScheduleEntity

    @Transaction
    @Query("SELECT * from schedules")
    fun getSchedulesWithBooks(): Flow<List<ScheduleWithBookEntity>>

    @Transaction
    @Query("SELECT * from schedules WHERE id = :id LIMIT 1")
    fun getScheduleWithBook(id: Int): Flow<ScheduleWithBookEntity>

    @Query("DELETE FROM schedules")
    suspend fun deleteAll()

    @Query("DELETE FROM schedules WHERE id = :id")
    suspend fun deleteById(id: Int)
}
