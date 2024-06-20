package com.taetae98.diary.data.holiday

import com.taetae98.diary.core.model.holiday.Holiday
import com.taetae98.diary.core.test.HolidayFactory
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.equals.shouldBeEqual
import io.mockk.clearAllMocks
import io.mockk.clearMocks
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flowOf
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn

class HolidayRepositoryTest : BehaviorSpec() {
    init {
        val prefDataStore = mockk<HolidayPrefDataStore>(relaxed = true, relaxUnitFun = true)
        val localDataSource = mockk<HolidayLocalDataSource>(relaxed = true, relaxUnitFun = true)
        val remoteDataSource = mockk<HolidayRemoteDataSource>(relaxed = true, relaxUnitFun = true)
        val clock = mockk<Clock>(relaxed = true, relaxUnitFun = true)

        val repository = HolidayRepositoryImpl(
            prefDataStore = prefDataStore,
            localDataSource = localDataSource,
            remoteDataSource = remoteDataSource,
            clock = clock,
        )

        Given("2024년 6월 24일에") {
            every { clock.now() } returns LocalDate(2024, 6, 24).atStartOfDayIn(TimeZone.currentSystemDefault())

            And("캐시가 없을 때") {
                every { prefDataStore.isUpdated(any(), any()) } returns flowOf(false)

                When("2024년 6월 공휴일을 요청하면") {
                    repository.findHoliday(listOf(2024 to Month.JUNE)).firstOrNull()

                    Then("LocalDataSource 호출한다.") {
                        verify { localDataSource.findHoliday(2024, Month.JUNE) }
                    }

                    Then("RemoteDataSource 호출한다.") {
                        coVerify { remoteDataSource.findHoliday(2024, Month.JUNE) }
                    }

                    Then("Local 캐시 업데이트한다.") {
                        coVerify { localDataSource.upsert(2024, Month.JUNE, any()) }
                    }

                    Then("캐시 업데이트 체크한다.") {
                        coVerify { prefDataStore.setUpdated(2024, Month.JUNE, true) }
                    }
                }

                clearAllMocks(answers = false)
                clearMocks(prefDataStore)
            }

            And("캐시가 있을 때") {
                every { prefDataStore.isUpdated(any(), any()) } returns flowOf(true)

                When("2024년 6월 공휴일을 요청하면") {
                    repository.findHoliday(listOf(2024 to Month.JUNE)).firstOrNull()

                    Then("LocalDataSource 호출한다.") {
                        verify { localDataSource.findHoliday(2024, Month.JUNE) }
                    }

                    Then("RemoteDataSource 호출하지 않는다.") {
                        coVerify(exactly = 0) { remoteDataSource.findHoliday(2024, Month.JUNE) }
                    }

                    Then("Local 캐시 업데이트하지 않는다.") {
                        coVerify(exactly = 0) { localDataSource.upsert(2024, Month.JUNE, any()) }
                    }

                    Then("캐시 업데이트 체크하지 않는다.") {
                        coVerify(exactly = 0) { prefDataStore.setUpdated(2024, Month.JUNE, true) }
                    }
                }

                clearAllMocks(answers = false)
                clearMocks(prefDataStore)
            }

            When("2024년 7월 공휴일을 요청하면") {
                repository.findHoliday(listOf(2024 to Month.JULY)).firstOrNull()

                Then("LocalDataSource 호출한다.") {
                    verify { localDataSource.findHoliday(2024, Month.JULY) }
                }

                Then("RemoteDataSource 호출한다.") {
                    coVerify(exactly = 0) { remoteDataSource.findHoliday(2024, Month.JULY) }
                }

                Then("Local 캐시 업데이트한다.") {
                    coVerify(exactly = 0) { localDataSource.upsert(2024, Month.JULY, any()) }
                }

                Then("캐시 업데이트 체크하지 않는다.") {
                    coVerify(exactly = 0) { prefDataStore.setUpdated(2024, Month.JUNE, true) }
                }
            }

            clearAllMocks()
        }

        Given("아무날에") {
            every { localDataSource.findHoliday(2021, Month.OCTOBER) } returns flowOf(HolidayFactory.holiday202110)
            every { localDataSource.findHoliday(2022, Month.JANUARY) } returns flowOf(HolidayFactory.holiday202201)
            every { localDataSource.findHoliday(2022, Month.FEBRUARY) } returns flowOf(HolidayFactory.holiday202202)

            When("2021년 10월 공휴일을 요청하면") {
                val list = repository.findHoliday(listOf(2021 to Month.OCTOBER)).first()

                Then("대체 공휴일 연결되지 않고 2개 생성된다.") {
                    list shouldBeEqual HolidayFactory.holiday202110
                }
            }

            When("2022년 1월, 2월 공휴일 요청하면") {
                val list = repository.findHoliday(listOf(2022 to Month.JANUARY, 2022 to Month.FEBRUARY)).first()

                Then("설날이 1월31~2월2일로 생성된다.") {
                    list.shouldContain(Holiday("설날", LocalDate(2022, 1, 31), LocalDate(2022, 2, 2)))
                }
            }
        }
    }
}
