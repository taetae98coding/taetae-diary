package com.taetae98.diary.core.pref.holiday

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import java.io.File

internal actual fun HolidayPrefModule.getDataStore(): DataStore<Preferences> {
    val file = File(System.getProperty("java.io.tmpdir"), DATA_STORE_NAME)

    return createDataStore { file.absolutePath }
}