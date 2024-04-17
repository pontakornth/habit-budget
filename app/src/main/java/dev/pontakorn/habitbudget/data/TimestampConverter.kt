package dev.pontakorn.habitbudget.data

import androidx.room.TypeConverter
import java.util.Date

class TimestampConverter {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }
}