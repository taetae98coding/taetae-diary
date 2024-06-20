package com.taetae98.diary.core.api.holiday

import com.taetae98.diary.core.test.HolidayFactory
import io.kotest.core.extensions.Extension
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.koin.KoinExtension
import io.kotest.koin.KoinLifecycleMode
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.collections.shouldHaveSize
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import kotlinx.datetime.Month
import org.koin.core.component.inject
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.module
import org.koin.ksp.generated.module
import org.koin.test.KoinTest

class HolidayServiceTest : BehaviorSpec(), KoinTest {
    private val service by inject<HolidayService>()

    override fun extensions(): List<Extension> {
        val modules = listOf(
            HolidayApiModule().module,
            emptyHolidayModule(),
            module {
                single<HttpClientEngineFactory<*>>(StringQualifier(HolidayApiModule.HOLIDAY_ENGINE)) {
                    object : HttpClientEngineFactory<HttpClientEngineConfig> {
                        override fun create(block: HttpClientEngineConfig.() -> Unit): HttpClientEngine {
                            return holidayMockEngine
                        }
                    }
                }
            },
        )

        return listOf(KoinExtension(modules = modules, mode = KoinLifecycleMode.Root))
    }

    init {
        Given("Mock Engine을 사용한다.") {
            When("2024년 6월 호출하면") {
                val result = service.findHoliday(2024, Month.JUNE)

                Then("1개의 공휴일이 나온다.") {
                    result.shouldHaveSize(1)
                }

                Then("holiday202406 포함된다.") {
                    result.shouldContainAll(HolidayFactory.holiday202406)
                }
            }

            When("2024년 5월 호출하면") {
                val result = service.findHoliday(2024, Month.MAY)

                Then("3개의 공휴일이 나온다.") {
                    result.shouldHaveSize(3)
                }

                Then("holiday202405 포함된다.") {
                    result.shouldContainAll(HolidayFactory.holiday202405)
                }
            }

            When("2023년 11월 호출하면") {
                val result = service.findHoliday(2023, Month.NOVEMBER)

                Then("0개의 공휴일이 나온다.") {
                    result.shouldHaveSize(0)
                }
            }
        }
    }
}
