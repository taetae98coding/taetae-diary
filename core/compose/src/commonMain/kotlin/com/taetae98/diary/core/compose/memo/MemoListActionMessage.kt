package com.taetae98.diary.core.compose.memo

import androidx.compose.runtime.Immutable

@Immutable
public sealed class MemoListActionMessage {
    public abstract val dismiss: () -> Unit

    public data class Finish(
        override val dismiss: () -> Unit,
        val cancel: () -> Unit,
    ) : MemoListActionMessage()

    public data class Delete(
        override val dismiss: () -> Unit,
        val cancel: () -> Unit,
    ) : MemoListActionMessage()
}
