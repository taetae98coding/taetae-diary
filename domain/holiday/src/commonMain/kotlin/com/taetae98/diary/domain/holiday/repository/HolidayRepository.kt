package com.taetae98.diary.domain.holiday.repository

import com.taetae98.diary.core.model.holiday.Holiday
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.Month

public interface HolidayRepository {
    public fun findHoliday(range: List<Pair<Int, Month>>): Flow<List<Holiday>>
}
