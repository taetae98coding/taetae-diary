package com.taetae98.diary.feature.calendar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.taetae98.diary.feature.calendar.internal.rememberCalendarState
import kotlinx.collections.immutable.persistentListOf
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn

@Composable
@NonRestartableComposable
internal fun CalendarRoute(
    modifier: Modifier = Modifier,
    calendarHolidayViewModel: CalendarHolidayViewModel,
) {
    val primaryDate = remember { mutableStateOf(persistentListOf(Clock.System.todayIn(TimeZone.currentSystemDefault()))) }
    val holiday = calendarHolidayViewModel.holiday.collectAsStateWithLifecycle()
    val state = rememberCalendarState(
        primaryDate = primaryDate,
        holiday = holiday,
    )

    CalendarScreen(
        modifier = modifier,
        state = state,
    )

    LaunchedEffect(state.year, state.month) {
        calendarHolidayViewModel.updateYearAndMonth(state.year, state.month)
    }
}
