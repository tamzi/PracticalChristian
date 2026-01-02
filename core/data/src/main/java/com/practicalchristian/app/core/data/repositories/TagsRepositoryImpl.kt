package com.practicalchristian.app.core.data.repositories

import com.practicalchristian.app.core.data.mappers.toCache
import com.practicalchristian.app.core.data.mappers.toDomain
import com.practicalchristian.app.core.data.mappers.toDomainError
import com.practicalchristian.app.core.domain.models.Outcome
import com.practicalchristian.app.core.domain.models.TagDomain
import com.practicalchristian.app.core.domain.repositories.TagsRepository
import com.practicalchristian.app.core.localdatasource.helpers.LocalResult
import com.practicalchristian.app.core.localdatasource.sources.tags.TagsSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

class TagsRepositoryImpl @Inject constructor(
    private val tagsSource: TagsSource
) : TagsRepository {

    override val tags: Flow<List<TagDomain>>
        get() = tagsSource.tags.mapLatest { list -> list.map { it.toDomain() } }

    override suspend fun insert(tag: TagDomain): Outcome<Boolean> {
        return when (val result = tagsSource.insert(item = tag.toCache())) {
            is LocalResult.Error -> Outcome.Failure(error = result.message.toDomainError())
            is LocalResult.Success -> Outcome.Success(value = true)
        }
    }

    override suspend fun update(tag: TagDomain): Outcome<Boolean> {
        return when (val result = tagsSource.update(item = tag.toCache())) {
            is LocalResult.Error -> Outcome.Failure(error = result.message.toDomainError())
            is LocalResult.Success -> Outcome.Success(value = true)
        }
    }

    override suspend fun delete(tag: TagDomain): Outcome<Boolean> {
        val result = tagsSource.delete(item = tag.toCache())
        return when (result) {
            is LocalResult.Error -> Outcome.Failure(error = result.message.toDomainError())
            is LocalResult.Success -> Outcome.Success(value = true)
        }
    }

    override suspend fun deleteAll(tag: TagDomain): Outcome<Boolean> {
        val result = tagsSource.deleteAll()
        return when (result) {
            is LocalResult.Error -> Outcome.Failure(error = result.message.toDomainError())
            is LocalResult.Success -> Outcome.Success(value = true)
        }
    }
}
