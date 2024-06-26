package com.taetae98.diary.core.database.diary

import androidx.room.RoomDatabase

internal const val DATABASE_NAME = "diary.db"

internal expect fun DiaryDatabaseModule.getDatabaseBuilder(): RoomDatabase.Builder<DiaryDatabase>