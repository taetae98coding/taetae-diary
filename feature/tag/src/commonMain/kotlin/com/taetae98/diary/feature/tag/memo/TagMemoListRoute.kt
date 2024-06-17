package com.taetae98.diary.feature.tag.memo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.cash.paging.compose.collectAsLazyPagingItems

@Composable
internal fun TagMemoListRoute(
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit,
    navigateToMemoDetail: (String) -> Unit,
    tagMemoListViewModel: TagMemoListViewModel,
    tagMemoListDetailViewModel: TagMemoListDetailViewModel,
) {
    val title = tagMemoListDetailViewModel.title.collectAsStateWithLifecycle()
    val message by tagMemoListViewModel.message.collectAsStateWithLifecycle()

    TagMemoListScreen(
        modifier = modifier,
        state = remember {
            TagMemoListState(
                onMemo = { navigateToMemoDetail(it.id) },
                onFinish = { tagMemoListViewModel.finish(it.id) },
                onDelete = { tagMemoListViewModel.delete(it.id) },
            )
        },
        topBarState = remember {
            TagMemoListTopBarState(
                titleState = title,
                onNavigateUp = navigateUp,
            )
        },
        pagingItems = tagMemoListViewModel.pagingData.collectAsLazyPagingItems(),
        message = { message },
    )
}
