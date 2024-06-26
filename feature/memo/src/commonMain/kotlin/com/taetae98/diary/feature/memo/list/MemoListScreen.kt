package com.taetae98.diary.feature.memo.list

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import app.cash.paging.compose.LazyPagingItems
import com.taetae98.diary.core.compose.dimension.DpDefault
import com.taetae98.diary.core.compose.icon.AddIcon
import com.taetae98.diary.core.compose.icon.FilterIcon
import com.taetae98.diary.core.compose.memo.MemoColumn
import com.taetae98.diary.core.compose.memo.MemoListActionMessage
import com.taetae98.diary.core.compose.memo.MemoListActionMessageCompose
import com.taetae98.diary.core.model.memo.Memo

@Composable
internal fun MemoListScreen(
    modifier: Modifier = Modifier,
    state: MemoListState,
    filterState: MemoListFilterState,
    pagingItems: LazyPagingItems<Memo>,
    message: () -> MemoListActionMessage?,
) {
    val hostState = remember { SnackbarHostState() }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text(text = "메모") },
                actions = {
                    IconToggleButton(
                        checked = filterState.hasMemoFilter,
                        onCheckedChange = { filterState.onFilter() },
                    ) {
                        FilterIcon()
                    }
                },
            )
        },
        floatingActionButton = {
            if (state.isFloatingVisible) {
                FloatingActionButton(onClick = state.onAdd) {
                    AddIcon()
                }
            }
        },
        snackbarHost = { SnackbarHost(hostState = hostState) },
    ) {
        MemoColumn(
            modifier = Modifier.fillMaxSize()
                .padding(it)
                .padding(horizontal = DpDefault.ScreenHorizontalDp),
            pagingItems = pagingItems,
            onMemo = state.onMemo,
            onFinish = state.onFinish,
            onDelete = state.onDelete,
        )
    }

    MemoListActionMessageCompose(
        hostState = hostState,
        message = message,
    )
}
