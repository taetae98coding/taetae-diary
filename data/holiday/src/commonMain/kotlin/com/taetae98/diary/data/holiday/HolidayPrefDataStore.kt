package com.taetae98.diary.data.holiday

import com.taetae98.diary.core.pref.holiday.HolidayPreference
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.Month
import org.koin.core.annotation.Factory

@Factory
internal class HolidayPrefDataStore(
    private val preference: HolidayPreference
) {
    fun isUpdated(year: Int, month: Month): Flow<Boolean> {
        return preference.isUpdated(year, month)
    }

    suspend fun setUpdated(year: Int, month: Month, isUpdated: Boolean) {
        preference.setUpdated(year, month, isUpdated)
    }
}
