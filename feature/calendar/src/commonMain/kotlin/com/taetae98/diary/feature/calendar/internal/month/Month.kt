package com.taetae98.diary.feature.calendar.internal.month

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.taetae98.diary.feature.calendar.internal.week.Week

@Composable
internal fun Month(
    modifier: Modifier = Modifier,
    state: MonthState,
) {
    Column(
        modifier = modifier,
    ) {
        repeat(6) {
            Week(
                modifier = Modifier.weight(1F),
                state = state.getWeekState(it)
            )
        }
    }
}
