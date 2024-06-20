package com.taetae98.diary.feature.tag.list

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
import com.taetae98.diary.core.compose.dimension.DpDefault
import com.taetae98.diary.core.compose.icon.AddIcon
import com.taetae98.diary.core.model.tag.Tag
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.launch

@Composable
internal fun TagListScreen(
    modifier: Modifier = Modifier,
    state: TagListState,
    tagList: () -> ImmutableList<Tag>?,
    message: () -> TagListActionMessage?,
) {
    val hostState = remember { SnackbarHostState() }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text(text = "태그") },
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
        TagColumn(
            modifier = Modifier.fillMaxSize()
                .padding(it)
                .padding(horizontal = DpDefault.ScreenHorizontalDp),
            tagList = tagList,
            onTag = state.onTag,
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
    message: () -> TagListActionMessage?,
) {
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(message()) {
        when (val msg = message()) {
            is TagListActionMessage.Finish -> {
                coroutineScope.launch {
                    msg.dismiss()
                    when (hostState.showSnackbar(message = "완료", actionLabel = "취소", duration = SnackbarDuration.Short)) {
                        SnackbarResult.ActionPerformed -> msg.cancel()
                        else -> Unit
                    }
                }
            }

            is TagListActionMessage.Delete -> {
                coroutineScope.launch {
                    msg.dismiss()
                    when (hostState.showSnackbar(message = "삭제", actionLabel = "취소", duration = SnackbarDuration.Short)) {
                        SnackbarResult.ActionPerformed -> msg.cancel()
                        else -> Unit
                    }
                }
            }

            else -> Unit
        }
    }
}
