package com.taetae98.diary.feature.tag.detail

import androidx.compose.runtime.Immutable

@Immutable
internal sealed class TagDetailState {
    protected abstract val isExpand: Boolean
    abstract val onNavigateUp: () -> Unit

    data class Add(
        override val isExpand: Boolean,
        override val onNavigateUp: () -> Unit,
        val onAdd: () -> Unit,
    ) : TagDetailState()

    data class Detail(
        override val isExpand: Boolean,
        override val onNavigateUp: () -> Unit,
        val onMemo: () -> Unit,
    ) : TagDetailState()

    val isTopBarVisible: Boolean
        get() = !isExpand
}