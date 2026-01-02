package com.practicalchristian.app.core.data.mappers

import com.practicalchristian.app.core.domain.models.Book
import com.practicalchristian.app.core.localdatasource.entity.BookCache

fun BookCache.toDomain() = Book(id, name, chapters)

fun Book.toCache() = BookCache(id, name, chapters)
