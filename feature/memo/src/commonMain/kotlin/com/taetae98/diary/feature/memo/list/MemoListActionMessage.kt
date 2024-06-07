package com.taetae98.diary.feature.memo.list

import androidx.compose.runtime.Immutable

@Immutable
internal sealed class MemoListActionMessage {
    abstract val dismiss: () -> Unit

    data class Finish(
        override val dismiss: () -> Unit,
        val cancel: () -> Unit,
    ) : MemoListActionMessage()

    data class Delete(
        override val dismiss: () -> Unit,
        val cancel: () -> Unit,
    ) : MemoListActionMessage()
}
