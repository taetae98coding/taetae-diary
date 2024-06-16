package com.taetae98.diary.feature.tag.list

import androidx.compose.runtime.State
import com.taetae98.diary.core.model.tag.Tag

internal data class TagListState(
    private val isExpand: State<Boolean>,
    private val isAdd: State<Boolean>,
    val onAdd: () -> Unit,
    val onTag: (Tag) -> Unit,
    val onFinish: (Tag) -> Unit,
    val onDelete: (Tag) -> Unit,
) {
    val isFloatingVisible: Boolean
        get() = !isExpand.value || !isAdd.value
}