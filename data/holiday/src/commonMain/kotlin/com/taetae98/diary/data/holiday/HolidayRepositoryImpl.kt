package com.taetae98.diary.data.holiday

import com.taetae98.diary.core.model.holiday.Holiday
import com.taetae98.diary.domain.holiday.repository.HolidayRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil
import kotlinx.datetime.todayIn
import org.koin.core.annotation.Factory

@Factory
internal class HolidayRepositoryImpl(
    private val prefDataStore: HolidayPrefDataStore,
    private val localDataSource: HolidayLocalDataSource,
    private val remoteDataSource: HolidayRemoteDataSource,
    private val clock: Clock,
) : HolidayRepository {
    override fun findHoliday(range: List<Pair<Int, Month>>): Flow<List<Holiday>> {
        return combine(range.map { localDataSource.findHoliday(it.first, it.second) }) { array ->
            array.flatMap { it }
        }.onStart {
            range.forEach {
                runCatching { updateIfNeed(it.first, it.second) }
            }
        }.map {
            it.zipHoliday()
        }
    }

    private suspend fun updateIfNeed(year: Int, month: Month) {
        if (prefDataStore.isUpdated(year, month).first()) return

        localDataSource.upsert(year, month, remoteDataSource.findHoliday(year, month))
        if (isBeforeOrEqualsMonth(year, month)) {
            prefDataStore.setUpdated(year, month, true)
        }
    }

    private fun isBeforeOrEqualsMonth(year: Int, month: Month): Boolean {
        val date = LocalDate(year, month, 1)
        val now = clock.todayIn(TimeZone.currentSystemDefault())

        return now.daysUntil(date) <= 0
    }

    private fun List<Holiday>.zipHoliday(): List<Holiday> {
        if (isEmpty()) return this

        val sorted = sorted()
        return buildList {
            var holiday = sorted.first()

            fun addAndReplace(day: Holiday) {
                add(holiday)
                holiday = day
            }

            sorted.forEach {
                if (holiday.name == it.name) {
                    if (holiday.endInclusive.daysUntil(it.start) <= 1) {
                        holiday = holiday.copy(
                            start = minOf(holiday.start, it.start),
                            endInclusive = maxOf(holiday.endInclusive, it.endInclusive),
                        )
                    } else {
                        addAndReplace(it)
                    }
                } else {
                    addAndReplace(it)
                }
            }

            add(holiday)
        }
    }
}
