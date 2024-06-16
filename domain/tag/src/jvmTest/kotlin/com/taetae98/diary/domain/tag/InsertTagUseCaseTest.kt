package com.taetae98.diary.domain.tag

import com.taetae98.diary.core.model.account.Account
import com.taetae98.diary.domain.tag.entity.TagDetail
import com.taetae98.diary.domain.tag.exception.TagTitleEmptyException
import com.taetae98.diary.domain.tag.repository.TagRepository
import com.taetae98.diary.domain.tag.usecase.InsertTagUseCase
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.result.shouldBeFailure
import io.kotest.matchers.result.shouldBeSuccess
import io.mockk.clearAllMocks
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf

class InsertTagUseCaseTest : BehaviorSpec() {
    init {
        val repository = mockk<TagRepository>(relaxed = true, relaxUnitFun = true)
        val useCase = InsertTagUseCase(
            getAccountUseCase = mockk {
                every { this@mockk.invoke(Unit) } returns flowOf(Result.success(Account.Guest))
            },
            repository = repository,
        )
        val tagDetail = mockk<TagDetail>(relaxed = true, relaxUnitFun = true)

        Given("Title is not empty") {
            every { tagDetail.title } returns "not empty"

            When("call usecase") {
                val result = useCase(tagDetail)

                Then("success") {
                    result.shouldBeSuccess()
                }

                Then("call repository") {
                    coVerify(exactly = 1) { repository.upsert(any()) }
                }
            }

            clearAllMocks(answers = false)
        }

        Given("Title is empty") {
            every { tagDetail.title } returns ""

            When("call usecase") {
                val result = useCase(tagDetail)

                Then("fail with TagTitleEmptyException") {
                    result.shouldBeFailure<TagTitleEmptyException>()
                }

                Then("not call repository") {
                    coVerify(exactly = 0) { repository.upsert(any()) }
                }
            }
        }
    }
}