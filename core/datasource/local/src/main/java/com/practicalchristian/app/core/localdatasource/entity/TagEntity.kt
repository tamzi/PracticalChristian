package com.practicalchristian.app.core.localdatasource.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.practicalchristian.app.core.localdatasource.helpers.BaseCache
import com.practicalchristian.app.core.localdatasource.helpers.BaseEntity

@Entity(tableName = "tags")
data class TagEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val color: String
) : BaseEntity<TagCache> {

    override fun toCache() = TagCache(id = id, name = name, color = color)
}

data class TagCache(
    val id: Int,
    val name: String,
    val color: String
) : BaseCache<TagEntity> {

    override fun toEntity() = TagEntity(id = id, name = name, color = color)
}
