package com.taetae98.diary.feature.calendar.internal.item

import androidx.compose.runtime.Immutable
import kotlinx.datetime.LocalDate

@Immutable
internal sealed class CalendarItemUiState : ClosedRange<LocalDate>, Comparable<CalendarItemUiState> {
    data class Holiday(
        val name: String,
        override val start: LocalDate,
        override val endInclusive: LocalDate,
    ) : CalendarItemUiState()

    override fun compareTo(other: CalendarItemUiState): Int {
        if (start != other.start) {
            return compareValues(start, other.start)
        }

        return compareValues(endInclusive, other.endInclusive)
    }
}
