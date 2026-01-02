package com.practicalchristian.app.core.localdatasource.sources.schedule_entry

import androidx.room.Dao
import androidx.room.Query
import com.practicalchristian.app.core.localdatasource.entity.ScheduleEntryEntity
import com.practicalchristian.app.core.localdatasource.sources.BaseDao
import kotlinx.coroutines.flow.Flow

@Dao
interface ScheduleEntryDao : BaseDao<ScheduleEntryEntity> {

    @Query("SELECT * from schedule_entries")
    fun getAllFlow(): Flow<List<ScheduleEntryEntity>>

    @Query("SELECT * from schedule_entries")
    suspend fun getAll(): List<ScheduleEntryEntity>

    @Query("SELECT * FROM schedule_entries WHERE entryId = :id")
    fun getFlow(id: String): Flow<ScheduleEntryEntity>

    @Query("SELECT * FROM schedule_entries WHERE entryId = :id")
    suspend fun get(id: String): ScheduleEntryEntity

    @Query("SELECT * FROM schedule_entries WHERE scheduleId = :id")
    suspend fun getEntriesBySchedule(id: Int): List<ScheduleEntryEntity>

    @Query("DELETE FROM schedule_entries")
    suspend fun deleteAll()

    @Query("DELETE FROM schedule_entries WHERE entryId = :id")
    suspend fun deleteById(id: String)

    @Query("DELETE FROM schedule_entries WHERE scheduleId = :id")
    suspend fun deleteByScheduleId(id: Int)
}
