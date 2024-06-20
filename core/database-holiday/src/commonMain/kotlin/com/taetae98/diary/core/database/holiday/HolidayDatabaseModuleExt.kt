package com.taetae98.diary.core.database.holiday

import androidx.room.RoomDatabase

internal const val DATABASE_NAME = "holiday.db"

internal expect fun HolidayDatabaseModule.getDatabaseBuilder(): RoomDatabase.Builder<HolidayDatabase>