package com.taetae98.diary.core.database.diary.memo.tag

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import com.taetae98.diary.core.database.diary.memo.MemoEntity
import com.taetae98.diary.core.database.diary.tag.TagEntity

@Entity(
    indices = [
        Index("memoId"),
        Index("tagId"),
    ],
    primaryKeys = ["memoId", "tagId"],
    foreignKeys = [
        ForeignKey(
            entity = MemoEntity::class,
            parentColumns = ["id"],
            childColumns = ["memoId"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
        ),
        ForeignKey(
            entity = TagEntity::class,
            parentColumns = ["id"],
            childColumns = ["tagId"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
        ),
    ],
)
internal data class MemoTagEntity(
    val memoId: String,
    val tagId: String,
)
