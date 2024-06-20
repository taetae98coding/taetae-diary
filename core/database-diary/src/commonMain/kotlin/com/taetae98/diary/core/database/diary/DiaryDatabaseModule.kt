package com.taetae98.diary.core.database.diary

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.taetae98.diary.core.database.diary.migration.ROOM_MIGRATION_1_2
import com.taetae98.diary.core.database.diary.migration.ROOM_MIGRATION_2_3
import org.koin.core.annotation.Module
import org.koin.core.annotation.Singleton
import org.koin.core.component.KoinComponent

@Module
public class DiaryDatabaseModule : KoinComponent {
    @Singleton
    internal fun providesDiaryDatabase(): DiaryDatabase {
        return getDatabaseBuilder()
            .addMigrations(ROOM_MIGRATION_1_2, ROOM_MIGRATION_2_3)
            .setDriver(BundledSQLiteDriver())
            .build()
    }
}
