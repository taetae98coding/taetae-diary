package com.taetae98.diary.core.database.diary

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import org.koin.core.annotation.Module
import org.koin.core.annotation.Singleton
import org.koin.core.component.KoinComponent

@Module
public class DiaryDatabaseModule : KoinComponent {
    @Singleton
    internal fun providesDiaryDatabase(): DiaryDatabase {
        return getDatabaseBuilder()
            .setDriver(BundledSQLiteDriver())
            .build()
    }
}
