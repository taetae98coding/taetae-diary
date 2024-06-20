package com.taetae98.diary.core.database.holiday

import androidx.room.Room
import androidx.room.RoomDatabase
import java.io.File

internal actual fun HolidayDatabaseModule.getDatabaseBuilder(): RoomDatabase.Builder<HolidayDatabase> {
    val file = File(System.getProperty("java.io.tmpdir"), DATABASE_NAME)

    return Room.databaseBuilder(name = file.absolutePath)
}