package com.taetae98.diary.feature.calendar.internal.month

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import com.taetae98.diary.feature.calendar.internal.item.CalendarItemUiState
import com.taetae98.diary.feature.calendar.internal.week.WeekState
import kotlinx.collections.immutable.ImmutableList
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month

@Stable
internal data class MonthState(
    val year: Int,
    val month: Month,
    private val primaryDate: State<ImmutableList<LocalDate>>,
    private val holiday: State<ImmutableList<CalendarItemUiState.Holiday>>,
) {
    @Composable
    fun getWeekState(weekOfMonth: Int): WeekState {
        return remember {
            WeekState(
                year = year,
                month = month,
                weekOfMonth = weekOfMonth,
                primaryDate = primaryDate,
                holiday = holiday,
            )
        }
    }
}
