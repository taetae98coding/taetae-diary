package com.taetae98.diary.feature.memo.list

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.cash.paging.compose.collectAsLazyPagingItems
import com.taetae98.diary.feature.memo.list.detail.MemoListDetailViewModel

@Composable
@NonRestartableComposable
internal fun MemoListRoute(
    modifier: Modifier = Modifier,
    navigateToAdd: () -> Unit,
    navigateToDetail: (String) -> Unit,
    memoListDetailViewModel: MemoListDetailViewModel,
    memoListViewModel: MemoListViewModel,
    memoActionViewModel: MemoActionViewModel,
) {
    val windowSize = calculateWindowSizeClass()
    val isExpand = remember {
        derivedStateOf { windowSize.widthSizeClass == WindowWidthSizeClass.Expanded }
    }
    val isAdd = memoListDetailViewModel.isAdd.collectAsStateWithLifecycle()
    val message by memoActionViewModel.message.collectAsStateWithLifecycle()

    MemoListScreen(
        modifier = modifier,
        state = remember {
            MemoListState(
                isExpand = isExpand,
                isAdd = isAdd,
                onAdd = navigateToAdd,
                onMemo = { navigateToDetail(it.id) },
                onFinish = { memoActionViewModel.finish(it.id) },
                onDelete = { memoActionViewModel.delete(it.id) },
            )
        },
        pagingItems = memoListViewModel.pagingData.collectAsLazyPagingItems(),
        message = { message },
    )
}