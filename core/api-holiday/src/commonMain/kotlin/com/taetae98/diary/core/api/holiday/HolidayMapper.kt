package com.taetae98.diary.core.api.holiday

import com.taetae98.diary.core.api.holiday.entity.HolidayEntity
import com.taetae98.diary.core.model.holiday.Holiday

internal fun HolidayEntity.toDomain(): Holiday {
    return Holiday(
        name = name.toPrettyName(),
        start = localDate,
        endInclusive = localDate,
    )
}

private fun String.toPrettyName(): String {
    return when (this) {
        "1월1일" -> "새해"
        "기독탄신일" -> "크리스마스"
        else -> this
    }
}
