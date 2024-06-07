package com.taetae98.diary.core.database.diary

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import org.koin.core.component.inject

internal actual fun DiaryDatabaseModule.getDatabaseBuilder(): RoomDatabase.Builder<DiaryDatabase> {
    val context by inject<Context>()
    val file = context.getDatabasePath("diary.db")

    return Room.databaseBuilder(
        context = context,
        name = file.absolutePath,
    )
}