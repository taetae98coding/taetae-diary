package com.taetae98.diary.feature.tag.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.taetae98.diary.core.compose.diary.info.DiaryInfo
import com.taetae98.diary.core.compose.diary.info.DiaryInfoState
import com.taetae98.diary.core.compose.dimension.DiaryDp
import com.taetae98.diary.core.compose.dimension.DpDefault
import com.taetae98.diary.core.compose.icon.AddIcon
import com.taetae98.diary.core.compose.icon.ChevronIcon
import com.taetae98.diary.core.compose.icon.NavigateUpIcon

@Composable
internal fun TagDetailScreen(
    modifier: Modifier = Modifier,
    state: TagDetailState,
    infoState: DiaryInfoState,
    message: () -> TagDetailMessage?,
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
            if (state is TagDetailState.Add) {
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
            state = state,
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
    state: TagDetailState,
    infoState: DiaryInfoState,
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(DpDefault.ItemSpaceDp),
    ) {
        DiaryInfo(
            modifier = Modifier.fillMaxWidth(),
            state = infoState,
        )

        if (state is TagDetailState.Detail) {
            MemoRow(onClick = state.onMemo)
        }
    }
}

@Composable
private fun MemoRow(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Row(
        modifier = modifier,
    ) {
        Card(
            modifier = Modifier.weight(1F),
            onClick = onClick,
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = DiaryDp.diaryHorizontalPadding),
                    text = "메모",
                    style = MaterialTheme.typography.titleMedium,
                )
                ChevronIcon(modifier = Modifier.minimumInteractiveComponentSize())
            }
        }
        Spacer(modifier = Modifier.weight(1F))
    }
}

@Composable
private fun DetailMessage(
    hostState: SnackbarHostState,
    message: () -> TagDetailMessage?,
) {
    LaunchedEffect(message()) {
        when (val msg = message()) {
            is TagDetailMessage.TitleEmpty -> {
                hostState.showSnackbar("제목을 입력해 주세요.")
                msg.dismiss()
            }

            is TagDetailMessage.Add -> {
                hostState.showSnackbar("태그 추가")
                msg.dismiss()
            }

            else -> Unit
        }
    }
}
