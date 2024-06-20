package com.taetae98.diary.core.pref.holiday

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.Month
import kotlinx.datetime.number
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Named

@Factory
public class HolidayPreference(
    @Named(HolidayPrefModule.HOLIDAY_DATASTORE)
    private val dataStore: DataStore<Preferences>,
) {
    public fun isUpdated(year: Int, month: Month): Flow<Boolean> {
        return dataStore.data.map { it[getKey(year, month)] }
            .map { it ?: false }
    }

    public suspend fun setUpdated(year: Int, month: Month, isUpdated: Boolean) {
        dataStore.edit { it[getKey(year, month)] = isUpdated }
    }

    private fun getKey(year: Int, month: Month): Preferences.Key<Boolean> {
        return booleanPreferencesKey("${year}_${month.number}")
    }
}
