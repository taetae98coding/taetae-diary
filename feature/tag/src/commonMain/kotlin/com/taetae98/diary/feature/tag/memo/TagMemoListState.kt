package com.taetae98.diary.feature.tag.memo

import com.taetae98.diary.core.model.memo.Memo

internal data class TagMemoListState(
    val onMemo: (Memo) -> Unit,
    val onFinish: (Memo) -> Unit,
    val onDelete: (Memo) -> Unit,
)
