package com.taetae98.diary.feature.calendar.internal.week

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.taetae98.diary.feature.calendar.internal.day.DayOfMonthState
import com.taetae98.diary.feature.calendar.internal.ext.christOrdinal
import com.taetae98.diary.feature.calendar.internal.ext.isOverlap
import com.taetae98.diary.feature.calendar.internal.item.CalendarItemState
import com.taetae98.diary.feature.calendar.internal.item.CalendarItemUiState
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month
import kotlinx.datetime.minus
import kotlinx.datetime.plus

@Stable
internal data class WeekState(
    val year: Int,
    val month: Month,
    val weekOfMonth: Int,
    private val primaryDate: State<ImmutableList<LocalDate>>,
    private val holiday: State<ImmutableList<CalendarItemUiState.Holiday>>,
) {
    val items: ImmutableList<ImmutableList<CalendarItemState>> by derivedStateOf {
        val startAt = LocalDate(year, month, 1)
            .let { it.minus(it.dayOfWeek.christOrdinal, DateTimeUnit.DAY) }
            .plus(weekOfMonth, DateTimeUnit.WEEK)

        val endAt = startAt.plus(6, DateTimeUnit.DAY)
        val range = startAt..endAt

        val filterHoliday = holiday.value.filter { it.isOverlap(range) }

        val itemList = buildList {
            addAll(filterHoliday)
        }.sorted().toMutableList()

        buildList {
            while (itemList.isNotEmpty()) {
                val row = buildList {
                    addAll(
                        weekStart = startAt,
                        weekEndInclusive = endAt,
                        iterator = itemList.iterator(),
                    )
                }

                add(row.toImmutableList())
            }
        }.toImmutableList()
    }

    @Composable
    fun getDayOfMonthUiState(dayOfWeek: DayOfWeek): DayOfMonthState {
        return remember {
            val localDate = LocalDate(year, month, 1)
                .let { it.minus(it.dayOfWeek.christOrdinal, DateTimeUnit.DAY) }
                .plus(weekOfMonth, DateTimeUnit.WEEK)
                .plus(dayOfWeek.christOrdinal, DateTimeUnit.DAY)

            DayOfMonthState(
                year = year,
                month = month,
                localDate = localDate,
                primaryDate = primaryDate,
                holiday = holiday
            )
        }
    }

    private fun MutableList<CalendarItemState>.addAll(
        weekStart: LocalDate,
        weekEndInclusive: LocalDate,
        iterator: MutableIterator<CalendarItemUiState>,
    ) {
        val sundayCursor = DayOfWeek.SUNDAY.christOrdinal
        val saturdayCursor = DayOfWeek.SATURDAY.christOrdinal
        var cursor = sumOf { it.weight.toInt() }

        while (iterator.hasNext()) {
            val item = iterator.next()
            val itemStartCursor = if (item.start < weekStart) {
                sundayCursor
            } else {
                item.start.dayOfWeek.christOrdinal
            }
            val itemEndCursor = if (item.endInclusive > weekEndInclusive) {
                saturdayCursor
            } else {
                item.endInclusive.dayOfWeek.christOrdinal
            }

            if (cursor < itemStartCursor) {
                add(CalendarItemState.Space((itemStartCursor - cursor).toFloat()))
            }
            if (cursor <= itemStartCursor) {
                val weight = itemEndCursor - itemStartCursor + 1F
                val weekDayItem = when (item) {
                    is CalendarItemUiState.Holiday -> CalendarItemState.Holiday(
                        key = item.toString(),
                        name = item.name,
                        weight = weight,
                    )
                }

                add(weekDayItem)
                iterator.remove()
                cursor = itemEndCursor + 1
                if (cursor > saturdayCursor) {
                    break
                }
            }
        }

        if (cursor <= saturdayCursor) {
            add(CalendarItemState.Space(saturdayCursor - cursor + 1F))
        }
    }
}
