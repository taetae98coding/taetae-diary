package com.taetae98.diary.core.database.holiday

import com.taetae98.diary.core.model.holiday.Holiday

public fun Holiday.toEntity(): HolidayEntity {
    return HolidayEntity(name, start, endInclusive)
}

public fun HolidayEntity.toModel(): Holiday {
    return Holiday(name, start, endInclusive)
}