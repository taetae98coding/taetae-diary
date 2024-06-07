package com.taetae98.diary.domain.memo

import com.taetae98.diary.core.model.account.Account
import com.taetae98.diary.domain.memo.entity.MemoDetail
import com.taetae98.diary.domain.memo.exception.MemoTitleEmptyException
import com.taetae98.diary.domain.memo.repository.MemoRepository
import com.taetae98.diary.domain.memo.usecase.InsertMemoUseCase
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.result.shouldBeFailure
import io.kotest.matchers.result.shouldBeSuccess
import io.mockk.clearAllMocks
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf

class InsertMemoUseCaseTest : BehaviorSpec() {
    init {
        val repository = mockk<MemoRepository>(relaxed = true, relaxUnitFun = true)
        val useCase = InsertMemoUseCase(
            getAccountUseCase = mockk {
                every { this@mockk.invoke(Unit) } returns flowOf(Result.success(Account.Guest))
            },
            repository = repository,
        )
        val memoDetail = mockk<MemoDetail>(relaxed = true, relaxUnitFun = true)

        Given("Title is not empty") {
            every { memoDetail.title } returns "not empty"

            When("call usecase") {
                val result = useCase(memoDetail)

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
            every { memoDetail.title } returns ""

            When("call usecase") {
                val result = useCase(memoDetail)

                Then("fail with MemoTitleException") {
                    result.shouldBeFailure<MemoTitleEmptyException>()
                }

                Then("not call repository") {
                    coVerify(exactly = 0) { repository.upsert(any()) }
                }
            }
        }
    }
}