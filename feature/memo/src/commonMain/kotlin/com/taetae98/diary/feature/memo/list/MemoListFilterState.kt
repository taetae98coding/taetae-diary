package com.taetae98.diary.feature.memo.list

import androidx.compose.runtime.State

internal data class MemoListFilterState(
    private val hasMemoFilterState: State<Boolean>,
    val onFilter: () -> Unit,
) {
    val hasMemoFilter: Boolean
        get() = hasMemoFilterState.value
}
