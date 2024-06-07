package com.taetae98.diary.domain.memo.usecase

import com.taetae98.diary.domain.core.UseCase
import com.taetae98.diary.domain.memo.entity.MemoDetail
import com.taetae98.diary.domain.memo.repository.MemoRepository
import kotlinx.coroutines.flow.firstOrNull
import org.koin.core.annotation.Factory

@Factory
public class UpdateMemoUseCase internal constructor(
    private val repository: MemoRepository,
) : UseCase<UpdateMemoUseCase.Param, Unit>() {
    override suspend fun execute(param: Param) {
        val detail = if (param.detail.title.isEmpty()) {
            param.detail.copy(
                title = getOriginTitle(param.memoId)
            )
        } else {
            param.detail
        }

        repository.update(param.memoId, detail)
    }

    private suspend fun getOriginTitle(memoId: String): String {
        val memo = requireNotNull(repository.findById(memoId).firstOrNull())

        return memo.title
    }

    public data class Param(
        val memoId: String,
        val detail: MemoDetail,
    )
}