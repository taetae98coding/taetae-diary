package com.taetae98.diary.core.database.holiday

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import org.koin.core.component.inject

internal actual fun HolidayDatabaseModule.getDatabaseBuilder(): RoomDatabase.Builder<HolidayDatabase> {
    val context by inject<Context>()
    val file = context.getDatabasePath(DATABASE_NAME)

    return Room.databaseBuilder(
        context = context,
        name = file.absolutePath,
    )
}