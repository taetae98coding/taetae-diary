package com.taetae98.diary.feature.memo.list.filter

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
internal fun MemoListFilterRoute(
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit,
    memoListFilterTagViewModel: MemoListFilterTagViewModel,
) {
    val tagListState = memoListFilterTagViewModel.tagList.collectAsStateWithLifecycle()

    MemoListFilterDialog(
        modifier = modifier,
        onDismissRequest = navigateUp,
        state = remember {
            MemoListFilterState(
                tagListState = tagListState,
                onSelect = memoListFilterTagViewModel::updateSelect,
            )
        },
    )
}
