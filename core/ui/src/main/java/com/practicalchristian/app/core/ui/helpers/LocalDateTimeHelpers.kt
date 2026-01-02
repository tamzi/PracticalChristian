package com.practicalchristian.app.core.ui.helpers

import com.practicalchristian.app.core.domain.extensions.sentence
import kotlinx.datetime.LocalDateTime
import java.util.Calendar
import java.util.Date

fun LocalDateTime.asFullDayString(): String = buildString {
    append(dayOfWeek.name.lowercase().sentence)
    append(" , ")
    append(date.day)
    append(" ")
    append(date.month.name.lowercase().sentence)
    append(" ")
    append(year)
}

fun Long?.asLocalDateTime(): LocalDateTime? {
    if (this == null) return null
    val cal = Calendar.getInstance()
    cal.time = Date(this)
    return LocalDateTime(year = cal[Calendar.YEAR],
        month = cal[Calendar.MONTH] + 1,
        day = cal[Calendar.DAY_OF_MONTH],
        hour = cal[Calendar.HOUR_OF_DAY],
        minute = cal[Calendar.MINUTE],
        second = cal[Calendar.SECOND],
        nanosecond = 0
    )
}
