package com.taetae98.diary.core.pref.holiday

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

internal const val DATA_STORE_NAME = "holiday.preferences_pb"

internal expect fun HolidayPrefModule.getDataStore(): DataStore<Preferences>
