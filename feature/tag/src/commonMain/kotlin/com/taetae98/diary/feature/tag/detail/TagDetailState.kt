package com.taetae98.diary.feature.tag.detail

import androidx.compose.runtime.State

internal data class TagDetailState(
    private val isExpand: State<Boolean>,
    private val isAdd: Boolean,
    val onNavigateUp: () -> Unit,
    val onAdd: () -> Unit = {},
) {
    val isTopBarVisible: Boolean
        get() = !isExpand.value

    val isFloatingVisible: Boolean
        get() = isAdd
}