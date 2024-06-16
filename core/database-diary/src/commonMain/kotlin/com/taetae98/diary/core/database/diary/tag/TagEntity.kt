package com.taetae98.diary.core.database.diary.tag

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
internal data class TagEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    val description: String,
    val isFinish: Boolean,
    val isDelete: Boolean,
    val owner: String?,
)
