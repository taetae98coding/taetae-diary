package com.taetae98.diary.feature.memo.detail

import androidx.compose.runtime.Immutable

@Immutable
internal sealed class MemoDetailState {
    protected abstract val isExpand: Boolean
    abstract val onNavigateUp: () -> Unit

    data class Add(
        override val isExpand: Boolean,
        override val onNavigateUp: () -> Unit,
        val onAdd: () -> Unit,
    ) : MemoDetailState()

    data class Detail(
        override val isExpand: Boolean,
        override val onNavigateUp: () -> Unit,
    ) : MemoDetailState()

    val isTopBarVisible: Boolean
        get() = !isExpand
}
