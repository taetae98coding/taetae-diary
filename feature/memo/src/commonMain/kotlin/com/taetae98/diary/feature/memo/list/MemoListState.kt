package com.taetae98.diary.feature.memo.list

import androidx.compose.runtime.State
import com.taetae98.diary.core.model.memo.Memo

internal data class MemoListState(
    private val isExpand: Boolean,
    private val isAdd: State<Boolean>,
    val onAdd: () -> Unit,
    val onFilter: () -> Unit,
    val onMemo: (Memo) -> Unit,
    val onFinish: (Memo) -> Unit,
    val onDelete: (Memo) -> Unit,
) {
    val isFloatingVisible: Boolean
        get() = !isExpand || !isAdd.value
}