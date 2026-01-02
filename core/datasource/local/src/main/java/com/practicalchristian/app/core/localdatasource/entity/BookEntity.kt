package com.practicalchristian.app.core.localdatasource.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.practicalchristian.app.core.localdatasource.helpers.BaseCache
import com.practicalchristian.app.core.localdatasource.helpers.BaseEntity

@Entity(tableName = "books")
data class BookEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val chapters: Int,
) : BaseEntity<BookCache> {

    override fun toCache() = BookCache(id, name, chapters)
}

data class BookCache(
    val id: Int,
    val name: String,
    val chapters: Int,
) : BaseCache<BookEntity> {

    override fun toEntity() = BookEntity(id, name, chapters)
}
