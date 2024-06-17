package com.taetae98.diary.feature.memo.detail

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.Modifier
import com.taetae98.diary.core.compose.diary.info.DiaryInfo
import com.taetae98.diary.core.compose.diary.info.DiaryInfoState
import com.taetae98.diary.core.compose.diary.tag.DiaryTag
import com.taetae98.diary.core.compose.diary.tag.DiaryTagState
import com.taetae98.diary.core.compose.dimension.DpDefault
import com.taetae98.diary.core.compose.icon.AddIcon
import com.taetae98.diary.core.compose.icon.NavigateUpIcon

@Composable
internal fun MemoDetailScreen(
    modifier: Modifier = Modifier,
    state: MemoDetailState,
    infoState: DiaryInfoState,
    tagState: DiaryTagState,
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
            if (state is MemoDetailState.Add) {
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
                .padding(horizontal = DpDefault.ScreenHorizontalDp),
            infoState = infoState,
            tagState = tagState,
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
    tagState: DiaryTagState,
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(DpDefault.ItemSpaceDp),
    ) {
        DiaryInfo(
            modifier = Modifier.fillMaxWidth(),
            state = infoState,
        )

        if (!tagState.isEmpty) {
            DiaryTag(
                modifier = Modifier.fillMaxWidth(),
                state = tagState,
            )
        }
    }
}

@Composable
private fun DetailMessage(
    hostState: SnackbarHostState,
    message: () -> MemoDetailMessage?,
) {
    LaunchedEffect(message()) {
        when (val msg = message()) {
            is MemoDetailMessage.TitleEmpty -> {
                hostState.showSnackbar("제목을 입력해 주세요.")
                msg.dismiss()
            }

            is MemoDetailMessage.Add -> {
                hostState.showSnackbar("메모 추가")
                msg.dismiss()
            }

            else -> Unit
        }
    }
}
