package com.practicalchristian.app.core.localdatasource.sources.base

import com.practicalchristian.app.core.localdatasource.helpers.BaseCache
import com.practicalchristian.app.core.localdatasource.helpers.LocalResult
import kotlinx.coroutines.flow.Flow

interface BaseSource<T : BaseCache<*>, R, S> {

    suspend fun get(id: R): LocalResult<T>

    suspend fun getFlow(id: R): LocalResult<Flow<T>>

    suspend fun getAll(): LocalResult<List<T>>

    suspend fun getAllFlow(): LocalResult<Flow<List<T>>>

    suspend fun search(query: S): LocalResult<Flow<List<T>>>

    suspend fun insert(item: T): LocalResult<Long>

    suspend fun insert(vararg item: T): LocalResult<Boolean>

    suspend fun update(item: T): LocalResult<Boolean>

    suspend fun update(vararg item: T): LocalResult<Boolean>

    suspend fun delete(item: T): LocalResult<Boolean>

    suspend fun delete(id: R): LocalResult<Boolean>

    suspend fun deleteAll(): LocalResult<Boolean>
}
