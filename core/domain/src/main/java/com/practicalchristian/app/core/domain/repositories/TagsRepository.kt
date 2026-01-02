package com.practicalchristian.app.core.domain.repositories

import com.practicalchristian.app.core.domain.models.Outcome
import com.practicalchristian.app.core.domain.models.TagDomain
import kotlinx.coroutines.flow.Flow

interface TagsRepository {

    val tags: Flow<List<TagDomain>>

    suspend fun insert(tag: TagDomain): Outcome<Boolean>

    suspend fun update(tag: TagDomain): Outcome<Boolean>

    suspend fun delete(tag: TagDomain): Outcome<Boolean>

    suspend fun deleteAll(tag: TagDomain): Outcome<Boolean>
}
