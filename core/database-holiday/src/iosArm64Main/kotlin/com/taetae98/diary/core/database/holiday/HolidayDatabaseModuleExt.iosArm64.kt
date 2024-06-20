package com.taetae98.diary.core.database.holiday

import androidx.room.Room
import androidx.room.RoomDatabase

internal actual fun HolidayDatabaseModule.getDatabaseBuilder(): RoomDatabase.Builder<HolidayDatabase> {
    return Room.databaseBuilder(
        name = "${fileDirectory()}/$DATABASE_NAME",
        factory = { HolidayDatabase::class.instantiateImpl() },
    )
}
