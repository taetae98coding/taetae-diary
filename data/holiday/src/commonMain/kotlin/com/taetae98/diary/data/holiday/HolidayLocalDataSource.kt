package com.taetae98.diary.data.holiday

import com.taetae98.diary.core.database.holiday.HolidayDatabase
import com.taetae98.diary.core.database.holiday.HolidayEntity
import com.taetae98.diary.core.database.holiday.toEntity
import com.taetae98.diary.core.database.holiday.toModel
import com.taetae98.diary.core.model.holiday.Holiday
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.Month
import kotlinx.datetime.number
import org.koin.core.annotation.Singleton

@Singleton
internal class HolidayLocalDataSource(
    private val holidayDatabase: HolidayDatabase,
) {
    fun findHoliday(year: Int, month: Month): Flow<List<Holiday>> {
        return holidayDatabase.holiday().findHoliday(year, month.number)
            .map { it.map(HolidayEntity::toModel) }
    }

    suspend fun upsert(year: Int, month: Month, holiday: List<Holiday>) {
        holidayDatabase.holiday().upsert(year, month.number, holiday.map(Holiday::toEntity))
    }
}
