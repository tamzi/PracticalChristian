package com.practicalchristian.app.core.data.mappers

import com.practicalchristian.app.core.domain.models.TagDomain
import com.practicalchristian.app.core.localdatasource.entity.TagCache

fun TagCache.toDomain() = TagDomain(id = id, name = name, color = color)

fun TagDomain.toCache() = TagCache(id = id, name = name, color = color)
