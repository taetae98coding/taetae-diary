package com.taetae98.diary.feature.tag.list

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.taetae98.diary.feature.tag.list.detail.TagListDetailViewModel

@Composable
@NonRestartableComposable
internal fun TagListRoute(
    modifier: Modifier = Modifier,
    navigateToAdd: () -> Unit,
    navigateToDetail: (String) -> Unit,
    tagListDetailViewModel: TagListDetailViewModel,
    tagListViewModel: TagListViewModel,
    tagActionViewModel: TagActionViewModel,
) {
    val windowSize = calculateWindowSizeClass()
    val isExpand = windowSize.widthSizeClass == WindowWidthSizeClass.Expanded

    val isAdd = tagListDetailViewModel.isAdd.collectAsStateWithLifecycle()
    val tagList by tagListViewModel.tagList.collectAsStateWithLifecycle()
    val message by tagActionViewModel.message.collectAsStateWithLifecycle()

    TagListScreen(
        modifier = modifier,
        state = remember(isExpand) {
            TagListState(
                isExpand = isExpand,
                isAdd = isAdd,
                onAdd = navigateToAdd,
                onTag = { navigateToDetail(it.id) },
                onFinish = { tagActionViewModel.finish(it.id) },
                onDelete = { tagActionViewModel.delete(it.id) },
            )
        },
        tagList = { tagList },
        message = { message },
    )
}