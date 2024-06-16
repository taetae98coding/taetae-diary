package com.taetae98.diary.feature.tag.list

import androidx.compose.runtime.Immutable

@Immutable
internal sealed class TagListActionMessage {
    abstract val dismiss: () -> Unit

    data class Finish(
        override val dismiss: () -> Unit,
        val cancel: () -> Unit,
    ) : TagListActionMessage()

    data class Delete(
        override val dismiss: () -> Unit,
        val cancel: () -> Unit,
    ) : TagListActionMessage()
}
