package com.taetae98.diary.core.database.diary

import androidx.room.RoomDatabase

internal expect fun DiaryDatabaseModule.getDatabaseBuilder(): RoomDatabase.Builder<DiaryDatabase>