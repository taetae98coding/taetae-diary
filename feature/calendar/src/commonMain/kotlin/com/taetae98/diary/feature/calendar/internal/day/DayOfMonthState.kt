package com.taetae98.diary.feature.calendar.internal.day

import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import com.taetae98.diary.feature.calendar.internal.item.CalendarItemUiState
import kotlinx.collections.immutable.ImmutableList
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month

@Stable
internal data class DayOfMonthState(
    private val year: Int,
    private val month: Month,
    private val localDate: LocalDate,
    private val primaryDate: State<ImmutableList<LocalDate>>,
    private val holiday: State<ImmutableList<CalendarItemUiState.Holiday>>,
) {
    val isPrimaryDate: Boolean
        get() = primaryDate.value.contains(localDate)

    val isHoliday: Boolean
        get() = holiday.value.any { localDate in it }

    val isInMonth: Boolean
        get() = localDate.year == year && localDate.month == month

    val dayOfMonth: Int
        get() = localDate.dayOfMonth

    val dayOfWeek: DayOfWeek
        get() = localDate.dayOfWeek
}
