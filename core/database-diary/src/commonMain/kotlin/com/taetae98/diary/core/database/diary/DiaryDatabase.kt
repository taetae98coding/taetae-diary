package com.taetae98.diary.core.database.diary

import androidx.room.Database
import androidx.room.RoomDatabase
import com.taetae98.diary.core.database.diary.memo.MemoDao
import com.taetae98.diary.core.database.diary.memo.MemoEntity
import com.taetae98.diary.core.database.diary.tag.TagDao
import com.taetae98.diary.core.database.diary.tag.TagEntity

@Database(
    entities = [
        MemoEntity::class,
        TagEntity::class,
    ],
    version = 2,
)
public abstract class DiaryDatabase : RoomDatabase() {
    public abstract fun memo(): MemoDao
    public abstract fun tag(): TagDao
}
