package com.taetae98.diary.core.model.holiday

import kotlinx.datetime.LocalDate

public data class Holiday(
    val name: String,
    override val start: LocalDate,
    override val endInclusive: LocalDate,
) : ClosedRange<LocalDate>, Comparable<Holiday> {
    override fun compareTo(other: Holiday): Int {
        if (start != other.start) compareValues(start, other.start)
        if (endInclusive != other.endInclusive) return compareValues(endInclusive, other.endInclusive)

        return compareValues(name, other.name)
    }
}
