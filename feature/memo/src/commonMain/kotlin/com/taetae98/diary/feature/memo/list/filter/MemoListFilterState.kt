package com.taetae98.diary.feature.memo.list.filter

import androidx.compose.runtime.State
import com.taetae98.diary.core.model.tag.Tag
import kotlinx.collections.immutable.ImmutableList

internal data class MemoListFilterState(
    private val tagListState: State<ImmutableList<Tag>>,
    val onSelect: (id: String, isSelect: Boolean) -> Unit,
) {
    val tagList: ImmutableList<Tag>
        get() = tagListState.value
}
