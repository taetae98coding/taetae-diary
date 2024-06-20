package com.taetae98.diary.core.compose.diary.tag

import androidx.compose.runtime.State
import com.taetae98.diary.core.model.tag.SelectTag
import kotlinx.collections.immutable.ImmutableList

public data class DiaryTagState(
    private val tagListState: State<ImmutableList<SelectTag>>,
    val onUpdateMemoTag: (id: String, isSelected: Boolean) -> Unit,
) {
    val isEmpty: Boolean
        get() = tagListState.value.isEmpty()

    val tagList: ImmutableList<SelectTag>
        get() = tagListState.value
}
