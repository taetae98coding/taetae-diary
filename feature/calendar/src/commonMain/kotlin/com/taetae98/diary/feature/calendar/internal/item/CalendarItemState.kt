package com.taetae98.diary.feature.calendar.internal.item

import androidx.compose.runtime.Immutable

@Immutable
internal sealed class CalendarItemState {
    abstract val weight: Float

    data class Space(
        override val weight: Float,
    ) : CalendarItemState()

    data class Holiday(
        val key: Any,
        val name: String,
        override val weight: Float,
    ) : CalendarItemState()
}
