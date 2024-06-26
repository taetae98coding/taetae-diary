package com.taetae98.diary.feature.calendar.internal

import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import com.taetae98.diary.feature.calendar.internal.item.CalendarItemUiState
import com.taetae98.diary.feature.calendar.internal.month.MonthState
import kotlinx.collections.immutable.ImmutableList
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn

@Stable
internal data class CalendarState(
    internal val pagerState: PagerState,
    private val primaryDate: State<ImmutableList<LocalDate>>,
    private val holiday: State<ImmutableList<CalendarItemUiState.Holiday>>,
) {
    private val localDate: LocalDate
        get() = pagerState.currentPage.toLocalDate()

    val year: Int
        get() = localDate.year

    val month: Month
        get() = localDate.month

    suspend fun scrollToToday() {
        val localDate = Clock.System.todayIn(TimeZone.currentSystemDefault())
        scrollTo(localDate.year, localDate.month)
    }

    suspend fun scrollTo(year: Int, month: Month) {
        pagerState.animateScrollToPage((year - FIRST_YEAR) * 12 + month.ordinal)
    }

    @Composable
    fun getMonthState(page: Int): MonthState {
        return remember {
            val localDate = page.toLocalDate()

            MonthState(
                year = localDate.year,
                month = localDate.month,
                primaryDate = primaryDate,
                holiday = holiday,
            )
        }
    }

    private fun Int.toLocalDate(): LocalDate {
        val year = this / 12 + FIRST_YEAR
        val month = this % 12 + 1

        return LocalDate(year, month, 1)
    }

    companion object {
        internal const val FIRST_YEAR = 1900
        internal const val LAST_YEAR = 2100

        internal const val PAGE_SIZE = (LAST_YEAR - FIRST_YEAR + 1) * 12
    }
}
