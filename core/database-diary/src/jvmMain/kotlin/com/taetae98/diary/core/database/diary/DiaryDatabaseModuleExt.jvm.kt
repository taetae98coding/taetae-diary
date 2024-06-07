package com.taetae98.diary.core.database.diary

import androidx.room.Room
import androidx.room.RoomDatabase
import java.io.File

internal actual fun DiaryDatabaseModule.getDatabaseBuilder(): RoomDatabase.Builder<DiaryDatabase> {
    val file = File(System.getProperty("java.io.tmpdir"), "diary.db")

    return Room.databaseBuilder<DiaryDatabase>(name = file.absolutePath)
}
