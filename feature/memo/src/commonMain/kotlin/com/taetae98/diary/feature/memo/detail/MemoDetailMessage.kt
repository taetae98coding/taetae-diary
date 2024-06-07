package com.taetae98.diary.feature.memo.detail

import androidx.compose.runtime.Immutable

@Immutable
internal sealed class MemoDetailMessage {
    abstract val dismiss: () -> Unit

    data class TitleEmpty(
        override val dismiss: () -> Unit,
    ) : MemoDetailMessage()

    data class Add(
        override val dismiss: () -> Unit,
    ) : MemoDetailMessage()
}
