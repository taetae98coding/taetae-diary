package com.taetae98.diary.data.holiday

import com.taetae98.diary.core.api.holiday.HolidayService
import com.taetae98.diary.core.model.holiday.Holiday
import kotlinx.datetime.Month
import org.koin.core.annotation.Factory

@Factory
internal class HolidayRemoteDataSource(
    private val holidayService: HolidayService,
) {
    suspend fun findHoliday(year: Int, month: Month): List<Holiday> {
        return holidayService.findHoliday(year, month)
    }
}
