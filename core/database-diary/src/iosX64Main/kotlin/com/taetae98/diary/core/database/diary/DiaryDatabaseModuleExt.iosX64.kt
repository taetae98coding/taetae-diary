package com.taetae98.diary.core.database.diary

import androidx.room.Room
import androidx.room.RoomDatabase

internal actual fun DiaryDatabaseModule.getDatabaseBuilder(): RoomDatabase.Builder<DiaryDatabase> {
    return Room.databaseBuilder(
        name = "${fileDirectory()}/$DATABASE_NAME",
        factory = { DiaryDatabase::class.instantiateImpl() },
    )
}