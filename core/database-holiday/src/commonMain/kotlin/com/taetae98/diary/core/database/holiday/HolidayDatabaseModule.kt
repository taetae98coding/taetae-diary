package com.taetae98.diary.core.database.holiday

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import org.koin.core.annotation.Module
import org.koin.core.annotation.Singleton
import org.koin.core.component.KoinComponent

@Module
public class HolidayDatabaseModule : KoinComponent {
    @Singleton
    internal fun providesDiaryDatabase(): HolidayDatabase {
        return getDatabaseBuilder()
            .fallbackToDestructiveMigration(dropAllTables = true)
            .setDriver(BundledSQLiteDriver())
            .build()
    }
}
