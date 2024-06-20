package com.taetae98.diary.core.database.holiday

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.taetae98.diary.core.model.holiday.Holiday
import com.taetae98.diary.core.test.HolidayFactory
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.LocalDate
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class HolidayDaoTest {
    private lateinit var holidayDatabase: HolidayDatabase

    @Before
    fun before() {
        holidayDatabase = Room.inMemoryDatabaseBuilder(
            context = InstrumentationRegistry.getInstrumentation().context,
            klass = HolidayDatabase::class.java,
        ).build()
    }

    @Test
    fun `findHoliday where condition test`() = runTest {
        holidayDatabase.holiday().insert(holidayList)
        holidayDatabase.holiday().insert(HolidayFactory.holiday202110.map(Holiday::toEntity))

        assertContentEquals(
            expected = HolidayFactory.holiday202110.map(Holiday::toEntity),
            actual = holidayDatabase.holiday().findHoliday(2021, 10).firstOrNull(),
        )
    }

    @Test
    fun `upsert test`() = runTest {
        holidayDatabase.holiday().upsert(2021, 10, oldHolidayList202110)
        holidayDatabase.holiday().upsert(2021, 10, HolidayFactory.holiday202110.map(Holiday::toEntity))

        assertContentEquals(
            expected = HolidayFactory.holiday202110.map(Holiday::toEntity),
            actual = holidayDatabase.holiday().findHoliday(2021, 10).firstOrNull(),
        )
    }

    companion object {
        val holidayList = listOf(
            HolidayEntity("설날", LocalDate(2022, 1, 31), LocalDate(2022, 1, 31)),
            HolidayEntity("설날", LocalDate(2022, 2, 1), LocalDate(2022, 2, 1)),
            HolidayEntity("설날", LocalDate(2022, 2, 2), LocalDate(2022, 2, 2)),
        )

        val oldHolidayList202110 = listOf(
            HolidayEntity("개천절", LocalDate(2021, 10, 3), LocalDate(2021, 10, 3)),
            HolidayEntity("대체 휴일", LocalDate(2021, 10, 4), LocalDate(2021, 10, 4)),
            HolidayEntity("한글날", LocalDate(2021, 10, 9), LocalDate(2021, 10, 9)),
        )
    }
}
