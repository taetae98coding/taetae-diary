package com.taetae98.diary.feature.memo.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.taetae98.diary.core.compose.diary.DiaryInfo
import com.taetae98.diary.core.compose.diary.DiaryInfoState
import com.taetae98.diary.core.compose.icon.AddIcon
import com.taetae98.diary.core.compose.icon.NavigateUpIcon
import kotlinx.coroutines.launch

@Composable
internal fun MemoDetailScreen(
    modifier: Modifier = Modifier,
    state: MemoDetailState,
    infoState: DiaryInfoState,
    message: () -> MemoDetailMessage?,
) {
    val hostState = remember { SnackbarHostState() }

    Scaffold(
        modifier = modifier,
        topBar = {
            if (state.isTopBarVisible) {
                TopAppBar(
                    title = {},
                    navigationIcon = {
                        IconButton(onClick = state.onNavigateUp) {
                            NavigateUpIcon()
                        }
                    },
                )
            }
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
        Content(
            modifier = Modifier.fillMaxSize()
                .padding(it)
                .padding(horizontal = 6.dp),
            infoState = infoState,
        )
    }

    DetailMessage(
        hostState = hostState,
        message = message,
    )
}

@Composable
private fun Content(
    modifier: Modifier = Modifier,
    infoState: DiaryInfoState,
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState()),
    ) {
        DiaryInfo(
            modifier = Modifier.fillMaxWidth(),
            state = infoState,
        )
    }
}

@Composable
private fun DetailMessage(
    hostState: SnackbarHostState,
    message: () -> MemoDetailMessage?,
) {
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(message()) {
        when (val msg = message()) {
            is MemoDetailMessage.TitleEmpty -> {
                coroutineScope.launch {
                    msg.dismiss()
                    hostState.showSnackbar("제목을 입력해 주세요.")
                }
            }

            is MemoDetailMessage.Add -> {
                coroutineScope.launch {
                    msg.dismiss()
                    hostState.showSnackbar("메모 추가")
                }
            }

            else -> Unit
        }
    }
}
