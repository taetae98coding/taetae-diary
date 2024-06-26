package com.taetae98.diary.core.pref.holiday

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import org.koin.core.component.inject

internal actual fun HolidayPrefModule.getDataStore(): DataStore<Preferences> {
    val context by inject<Context>()

    return createDataStore(
        producePath = { context.filesDir.resolve(DATA_STORE_NAME).absolutePath },
    )
}
