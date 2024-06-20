package com.taetae98.diary.feature.tag.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.taetae98.diary.feature.tag.add.TagAddRoute
import com.taetae98.diary.feature.tag.add.TagAddViewModel
import com.taetae98.diary.feature.tag.list.detail.TagListDetailViewModel

@Composable
@NonRestartableComposable
internal fun TagDetailPane(
    navigateUp: () -> Unit,
    navigateToMemo: () -> Unit,
    tagListDetailViewModel: TagListDetailViewModel,
    tagAddViewModel: TagAddViewModel,
    tagDetailViewModel: TagDetailViewModel,
) {
    val isAdd by tagListDetailViewModel.isAdd.collectAsStateWithLifecycle()

    if (isAdd) {
        TagAddRoute(
            navigateUp = navigateUp,
            tagAddViewModel = tagAddViewModel,
        )
    } else {
        TagDetailRoute(
            navigateUp = navigateUp,
            navigateToMemo = navigateToMemo,
            tagDetailViewModel = tagDetailViewModel,
        )
    }
}