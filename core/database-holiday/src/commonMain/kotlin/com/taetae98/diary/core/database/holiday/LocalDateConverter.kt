package com.taetae98.diary.core.database.holiday

import androidx.room.TypeConverter
import kotlinx.datetime.LocalDate

internal class LocalDateConverter {
    @TypeConverter
    fun stringToLocalDate(string: String?): LocalDate? {
        return string?.let { LocalDate.parse(it) }
    }

    @TypeConverter
    fun localDateToString(localDate: LocalDate?): String? {
        return localDate?.toString()
    }
}
