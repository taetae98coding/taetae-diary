package com.taetae98.diary.core.database.diary.tag

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
internal data class TagEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    val description: String,
    @ColumnInfo(defaultValue = "0")
    val isMemoFilter: Boolean,
    val isFinish: Boolean,
    val isDelete: Boolean,
    val owner: String?,
)
