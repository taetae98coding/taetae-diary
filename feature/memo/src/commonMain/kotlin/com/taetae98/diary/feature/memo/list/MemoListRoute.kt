package com.taetae98.diary.feature.memo.list

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
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
    navigateToListFilter: () -> Unit,
    memoListDetailViewModel: MemoListDetailViewModel,
    memoListViewModel: MemoListViewModel,
    memoActionViewModel: MemoActionViewModel,
    memoFilterViewModel: MemoFilterViewModel,
) {
    val windowSize = calculateWindowSizeClass()
    val isExpand = windowSize.widthSizeClass == WindowWidthSizeClass.Expanded
    val isAdd = memoListDetailViewModel.isAdd.collectAsStateWithLifecycle()
    val hasMemoFilter = memoFilterViewModel.hasMemoFilter.collectAsStateWithLifecycle()
    val message by memoActionViewModel.message.collectAsStateWithLifecycle()

    MemoListScreen(
        modifier = modifier,
        state = remember(isExpand) {
            MemoListState(
                isExpand = isExpand,
                isAdd = isAdd,
                onAdd = navigateToAdd,
                onMemo = { navigateToDetail(it.id) },
                onFinish = { memoActionViewModel.finish(it.id) },
                onDelete = { memoActionViewModel.delete(it.id) },
            )
        },
        filterState = remember {
            MemoListFilterState(
                hasMemoFilterState = hasMemoFilter,
                onFilter = navigateToListFilter,
            )
        },
        pagingItems = memoListViewModel.pagingData.collectAsLazyPagingItems(),
        message = { message },
    )
}