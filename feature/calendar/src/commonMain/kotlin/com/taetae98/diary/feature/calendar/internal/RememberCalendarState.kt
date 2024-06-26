package com.taetae98.diary.feature.calendar.internal

import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import com.taetae98.diary.feature.calendar.internal.item.CalendarItemUiState
import kotlinx.collections.immutable.ImmutableList
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn

@Composable
internal fun rememberCalendarState(
    primaryDate: State<ImmutableList<LocalDate>>,
    holiday: State<ImmutableList<CalendarItemUiState.Holiday>>,
): CalendarState {
    val initialPage = remember {
        val localDate = Clock.System.todayIn(TimeZone.currentSystemDefault())
        (localDate.year - CalendarState.FIRST_YEAR) * 12 + localDate.month.ordinal
    }
    val pagerState = rememberPagerState(initialPage) { CalendarState.PAGE_SIZE }

    return remember {
        CalendarState(
            pagerState = pagerState,
            primaryDate = primaryDate,
            holiday = holiday,
        )
    }
}