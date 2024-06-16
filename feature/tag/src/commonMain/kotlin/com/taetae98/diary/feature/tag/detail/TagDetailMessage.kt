package com.taetae98.diary.feature.tag.detail

import androidx.compose.runtime.Immutable

@Immutable
internal sealed class TagDetailMessage {
    abstract val dismiss: () -> Unit

    data class TitleEmpty(
        override val dismiss: () -> Unit,
    ) : TagDetailMessage()

    data class Add(
        override val dismiss: () -> Unit,
    ) : TagDetailMessage()
}
