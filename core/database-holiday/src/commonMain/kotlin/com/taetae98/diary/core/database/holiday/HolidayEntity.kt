package com.taetae98.diary.core.database.holiday

import androidx.room.Entity
import kotlinx.datetime.LocalDate

@Entity(
    primaryKeys = ["name", "start", "endInclusive"],
)
public data class HolidayEntity(
    val name: String,
    val start: LocalDate,
    val endInclusive: LocalDate,
)
