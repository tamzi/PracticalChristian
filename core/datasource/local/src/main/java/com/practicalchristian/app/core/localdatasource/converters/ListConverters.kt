package com.practicalchristian.app.core.localdatasource.converters

import androidx.room.TypeConverter
import kotlinx.serialization.json.Json

class ListConverters {
    @TypeConverter
    fun fromList(value: List<String>): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun toList(value: String): List<String> {
        return Json.decodeFromString(value)
    }
}
