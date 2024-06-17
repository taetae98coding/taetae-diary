@file:JvmName("MemoListActionMessageKt")

package com.taetae98.diary.core.compose.memo

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlin.jvm.JvmName

@Composable
public fun MemoListActionMessageCompose(
    hostState: SnackbarHostState,
    message: () -> MemoListActionMessage?,
) {
    LaunchedEffect(message()) {
        when (val msg = message()) {
            is MemoListActionMessage.Finish -> {
                when (hostState.showSnackbar(message = "완료", actionLabel = "취소", duration = SnackbarDuration.Short)) {
                    SnackbarResult.ActionPerformed -> msg.cancel()
                    else -> Unit
                }
                msg.dismiss()
            }

            is MemoListActionMessage.Delete -> {
                when (hostState.showSnackbar(message = "삭제", actionLabel = "취소", duration = SnackbarDuration.Short)) {
                    SnackbarResult.ActionPerformed -> msg.cancel()
                    else -> Unit
                }
                msg.dismiss()
            }

            else -> Unit
        }
    }
}