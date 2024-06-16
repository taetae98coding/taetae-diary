package com.taetae98.diary.core.model.tag

public data class Tag(
    val id: String,
    val title: String,
    val description: String,
    val isFinish: Boolean,
    val isDelete: Boolean,
    val owner: String?,
)
