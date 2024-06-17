package com.taetae98.diary.feature.tag.memo

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.IconButton
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
import com.taetae98.diary.core.compose.icon.NavigateUpIcon
import com.taetae98.diary.core.compose.memo.MemoColumn
import com.taetae98.diary.core.compose.memo.MemoListActionMessage
import com.taetae98.diary.core.compose.memo.MemoListActionMessageCompose
import com.taetae98.diary.core.model.memo.Memo

@Composable
internal fun TagMemoListScreen(
    modifier: Modifier = Modifier,
    state: TagMemoListState,
    topBarState: TagMemoListTopBarState,
    pagingItems: LazyPagingItems<Memo>,
    message: () -> MemoListActionMessage?,
) {
    val hostState = remember { SnackbarHostState() }
    Scaffold(
        modifier = modifier,
        topBar = { TopBar(state = topBarState) },
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
        message = message,
        hostState = hostState,
    )
}

@Composable
private fun TopBar(
    modifier: Modifier = Modifier,
    state: TagMemoListTopBarState,
) {
    TopAppBar(
        modifier = modifier,
        title = { Text(text = state.title) },
        navigationIcon = {
            IconButton(onClick = state.onNavigateUp) {
                NavigateUpIcon()
            }
        },
    )
}
