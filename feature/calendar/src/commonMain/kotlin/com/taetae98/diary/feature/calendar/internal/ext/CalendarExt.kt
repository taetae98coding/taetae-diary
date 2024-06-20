package com.taetae98.diary.feature.calendar.internal.ext

import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.isoDayNumber

internal val DayOfWeek.christOrdinal: Int
    get() = isoDayNumber % 7

internal fun Int.toChristDayOfWeek(): DayOfWeek {
    return if (this == 0) {
        DayOfWeek.SUNDAY
    } else {
        DayOfWeek(this)
    }
}

internal fun ClosedRange<LocalDate>.isOverlap(range: ClosedRange<LocalDate>): Boolean {
    return endInclusive >= range.start && start <= range.endInclusive
}
