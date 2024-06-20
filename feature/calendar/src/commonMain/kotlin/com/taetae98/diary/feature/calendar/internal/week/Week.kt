package com.taetae98.diary.feature.calendar.internal.week

import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.taetae98.diary.feature.calendar.internal.color.CalendarDefault
import com.taetae98.diary.feature.calendar.internal.day.DayOfMonth
import com.taetae98.diary.feature.calendar.internal.ext.toChristDayOfWeek
import com.taetae98.diary.feature.calendar.internal.ext.toContrastColor
import com.taetae98.diary.feature.calendar.internal.item.CalendarItemState
import kotlinx.collections.immutable.ImmutableList

@Composable
internal fun Week(
    modifier: Modifier = Modifier,
    state: WeekState,
) {
    Column(
        modifier = modifier,
    ) {
        HorizontalDivider(thickness = 0.5.dp)
        DayOfMonthRow(state = state)
        CalendarItem(
            modifier = Modifier.fillMaxSize(),
            state = state,
        )
    }
}

@Composable
private fun DayOfMonthRow(
    modifier: Modifier = Modifier,
    state: WeekState,
) {
    Row(modifier = modifier) {
        repeat(7) {
            DayOfMonth(
                modifier = Modifier.weight(1F),
                state = state.getDayOfMonthUiState(it.toChristDayOfWeek()),
            )
        }
    }
}

@Composable
private fun CalendarItem(
    modifier: Modifier = Modifier,
    state: WeekState,
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState()),
    ) {
        state.items.forEach {
            ItemRow(uiState = it)
        }
    }
}

@Composable
private fun ItemRow(
    modifier: Modifier = Modifier,
    uiState: ImmutableList<CalendarItemState>,
) {
    Row(
        modifier = modifier.height(intrinsicSize = IntrinsicSize.Min),
    ) {
        uiState.forEach {
            when (it) {
                is CalendarItemState.Space -> {
                    Spacer(modifier = Modifier.weight(it.weight))
                }

                is CalendarItemState.Holiday -> {
                    key(it.key) {
                        Text(
                            modifier = Modifier.weight(it.weight)
                                .fillMaxHeight()
                                .background(color = CalendarDefault.sundayColor)
                                .basicMarquee(iterations = Int.MAX_VALUE),
                            text = it.name,
                            color = CalendarDefault.sundayColor.toContrastColor(),
                            textAlign = TextAlign.Center,
                            maxLines = 1,
                            style = MaterialTheme.typography.labelMedium,
                        )
                    }
                }
            }
        }
    }
}
