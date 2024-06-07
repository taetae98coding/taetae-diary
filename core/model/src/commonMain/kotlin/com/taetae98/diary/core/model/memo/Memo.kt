package com.taetae98.diary.core.model.memo

public data class Memo(
    val id: String,
    val title: String,
    val description: String,
    val isFinish: Boolean,
    val isDelete: Boolean,
    val owner: String?,
)
