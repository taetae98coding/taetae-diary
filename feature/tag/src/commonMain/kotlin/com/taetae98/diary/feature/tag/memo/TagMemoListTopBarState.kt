package com.taetae98.diary.feature.tag.memo

import androidx.compose.runtime.State

internal data class TagMemoListTopBarState(
    private val titleState: State<String>,
    val onNavigateUp: () -> Unit,
) {
    val title: String
        get() = titleState.value
}
