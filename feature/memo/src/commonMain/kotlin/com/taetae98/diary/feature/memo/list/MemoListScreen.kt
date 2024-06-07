package com.taetae98.diary.feature.memo.list

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.cash.paging.compose.LazyPagingItems
import com.taetae98.diary.core.compose.icon.AddIcon
import com.taetae98.diary.core.model.memo.Memo
import kotlinx.coroutines.launch

@Composable
internal fun MemoListScreen(
    modifier: Modifier = Modifier,
    state: MemoListState,
    pagingItems: LazyPagingItems<Memo>,
    message: () -> MemoListActionMessage?,
) {
    val hostState = remember { SnackbarHostState() }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text(text = "메모") },
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
                .padding(horizontal = 6.dp),
            pagingItems = pagingItems,
            onMemo = state.onMemo,
            onFinish = state.onFinish,
            onDelete = state.onDelete,
        )
    }

    ActionMessage(
        hostState = hostState,
        message = message,
    )
}

@Composable
private fun ActionMessage(
    hostState: SnackbarHostState,
    message: () -> MemoListActionMessage?,
) {
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(message()) {
        when (val msg = message()) {
            is MemoListActionMessage.Finish -> {
                coroutineScope.launch {
                    msg.dismiss()
                    when (hostState.showSnackbar(message = "완료", actionLabel = "취소", duration = SnackbarDuration.Short)) {
                        SnackbarResult.ActionPerformed -> msg.cancel()
                        else -> Unit
                    }
                }
            }

            is MemoListActionMessage.Delete -> {
                coroutineScope.launch {
                    msg.dismiss()
                    when (hostState.showSnackbar(message = "취소", actionLabel = "취소", duration = SnackbarDuration.Short)) {
                        SnackbarResult.ActionPerformed -> msg.cancel()
                        else -> Unit
                    }
                }
            }

            else -> Unit
        }
    }
}