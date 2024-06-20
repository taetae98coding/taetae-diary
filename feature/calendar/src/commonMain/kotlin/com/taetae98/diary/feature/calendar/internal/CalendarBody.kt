package com.taetae98.diary.feature.calendar.internal

import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.taetae98.diary.feature.calendar.internal.month.Month

@Composable
internal fun CalendarBody(
    modifier: Modifier = Modifier,
    state: CalendarState,
) {
    HorizontalPager(
        modifier = modifier,
        state = state.pagerState,
    ) {
        Month(
            state = state.getMonthState(it)
        )
    }
}
