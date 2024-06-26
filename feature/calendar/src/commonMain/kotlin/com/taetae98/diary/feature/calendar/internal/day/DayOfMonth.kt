package com.taetae98.diary.feature.calendar.internal.day

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.taetae98.diary.feature.calendar.internal.color.CalendarDefault
import kotlinx.datetime.DayOfWeek

@Composable
internal fun DayOfMonth(
    modifier: Modifier = Modifier,
    state: DayOfMonthState,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        val color = if (state.isPrimaryDate) {
            MaterialTheme.colorScheme.onPrimary
        } else {
            when {
                state.isHoliday -> CalendarDefault.sundayColor
                state.dayOfWeek == DayOfWeek.SUNDAY -> CalendarDefault.sundayColor
                state.dayOfWeek == DayOfWeek.SATURDAY -> CalendarDefault.saturdayColor
                else -> LocalContentColor.current
            }.copy(
                alpha = if (state.isInMonth) {
                    1F
                } else {
                    0.38F
                },
            )
        }

        Box(
            modifier = if (state.isPrimaryDate) {
                Modifier.padding(2.dp)
                    .background(MaterialTheme.colorScheme.primary, CircleShape)
            } else {
                Modifier.padding(2.dp)
            }.defaultMinSize(30.dp, 30.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                modifier = Modifier.padding(2.dp),
                text = state.dayOfMonth.toString(),
                color = color,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleSmall,
            )
        }
    }
}