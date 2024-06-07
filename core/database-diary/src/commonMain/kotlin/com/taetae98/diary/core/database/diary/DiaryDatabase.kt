package com.taetae98.diary.core.database.diary

import androidx.room.Database
import androidx.room.RoomDatabase
import com.taetae98.diary.core.database.diary.memo.MemoDao
import com.taetae98.diary.core.database.diary.memo.MemoEntity

@Database(
    entities = [MemoEntity::class],
    version = 1,
)
public abstract class DiaryDatabase : RoomDatabase() {
    public abstract fun memo(): MemoDao
}
