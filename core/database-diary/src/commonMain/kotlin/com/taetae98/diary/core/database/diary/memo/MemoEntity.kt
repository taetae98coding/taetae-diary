package com.taetae98.diary.core.database.diary.memo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
public data class MemoEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    val description: String,
    val isFinish: Boolean,
    val isDelete: Boolean,
    val owner: String?,
)
