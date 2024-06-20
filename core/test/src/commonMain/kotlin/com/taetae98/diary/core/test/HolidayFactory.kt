package com.taetae98.diary.core.test

import com.taetae98.diary.core.model.holiday.Holiday
import kotlinx.datetime.LocalDate

public data object HolidayFactory {
    public val holiday202110: List<Holiday> = listOf(
        Holiday("개천절", LocalDate(2021, 10, 3), LocalDate(2021, 10, 3)),
        Holiday("대체공휴일", LocalDate(2021, 10, 4), LocalDate(2021, 10, 4)),
        Holiday("한글날", LocalDate(2021, 10, 9), LocalDate(2021, 10, 9)),
        Holiday("대체공휴일", LocalDate(2021, 10, 11), LocalDate(2021, 10, 11)),
    )

    public val holiday202201: List<Holiday> = listOf(
        Holiday("1월1일", LocalDate(2022, 1, 1), LocalDate(2022, 1, 1)),
        Holiday("설날", LocalDate(2022, 1, 31), LocalDate(2022, 1, 31)),
    )

    public val holiday202202: List<Holiday> = listOf(
        Holiday("설날", LocalDate(2022, 2, 1), LocalDate(2022, 2, 1)),
        Holiday("설날", LocalDate(2022, 2, 2), LocalDate(2022, 2, 2)),
    )

    public val holiday202406: List<Holiday> = listOf(
        Holiday("현충일", LocalDate(2024, 6, 6), LocalDate(2024, 6, 6)),
    )

    public val holiday202405: List<Holiday> = listOf(
        Holiday("어린이날", LocalDate(2024, 5, 5), LocalDate(2024, 5, 5)),
        Holiday("대체공휴일(어린이날)", LocalDate(2024, 5, 6), LocalDate(2024, 5, 6)),
        Holiday("부처님오신날", LocalDate(2024, 5, 15), LocalDate(2024, 5, 15)),
    )
}
