package com.taetae98.diary.feature.calendar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taetae98.diary.core.coroutines.DEFAULT_WHILE_SUBSCRIBED
import com.taetae98.diary.domain.holiday.usecase.FindHolidayUseCase
import com.taetae98.diary.feature.calendar.internal.item.CalendarItemUiState
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month
import kotlinx.datetime.plus
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
internal class CalendarHolidayViewModel(
    private val findHolidayUseCase: FindHolidayUseCase,
) : ViewModel() {
    private val yearFlow = MutableStateFlow<Int?>(null)
    private val monthFlow = MutableStateFlow<Month?>(null)
    private val yearAndMonth = combine(
        yearFlow.filterNotNull(),
        monthFlow.filterNotNull(),
    ) { year, month ->
        year to month
    }

    val holiday = yearAndMonth.flatMapLatest { yearAndMonth ->
        val param = IntRange(-2, 2).map { LocalDate(yearAndMonth.first, yearAndMonth.second, 1).plus(it, DateTimeUnit.MONTH) }
            .map { it.year to it.month }

        findHolidayUseCase(param)
    }.mapLatest {
        it.getOrNull().orEmpty()
    }.mapLatest { list ->
        list.map {
            CalendarItemUiState.Holiday(
                name = it.name,
                start = it.start,
                endInclusive = it.endInclusive,
            )
        }.toImmutableList()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.DEFAULT_WHILE_SUBSCRIBED,
        initialValue = persistentListOf(),
    )

    fun updateYearAndMonth(year: Int, month: Month) {
        viewModelScope.launch {
            yearFlow.emit(year)
            monthFlow.emit(month)
        }
    }
}