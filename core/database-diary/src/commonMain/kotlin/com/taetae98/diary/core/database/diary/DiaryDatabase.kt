package com.taetae98.diary.core.database.diary

import androidx.room.Database
import androidx.room.RoomDatabase
import com.taetae98.diary.core.database.diary.memo.MemoDao
import com.taetae98.diary.core.database.diary.memo.MemoEntity
import com.taetae98.diary.core.database.diary.memo.tag.MemoTagDao
import com.taetae98.diary.core.database.diary.memo.tag.MemoTagEntity
import com.taetae98.diary.core.database.diary.tag.TagDao
import com.taetae98.diary.core.database.diary.tag.TagEntity

@Database(
    entities = [
        MemoEntity::class,
        TagEntity::class,
        MemoTagEntity::class,
    ],
    version = 3,
)
public abstract class DiaryDatabase : RoomDatabase() {
    public abstract fun memo(): MemoDao
    public abstract fun memoTag(): MemoTagDao
    public abstract fun tag(): TagDao
}
