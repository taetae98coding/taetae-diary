package com.taetae98.diary.core.pref.holiday

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Named
import org.koin.core.annotation.Singleton
import org.koin.core.component.KoinComponent

@Module
@ComponentScan
public class HolidayPrefModule : KoinComponent {
    @Named(HOLIDAY_DATASTORE)
    @Singleton
    internal fun providesHolidayDataStore(): DataStore<Preferences> {
        return getDataStore()
    }

    public companion object {
        public const val HOLIDAY_DATASTORE: String = "holidayDataStore"
    }
}
