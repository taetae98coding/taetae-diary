package com.taetae98.diary.domain.memo.usecase

import com.taetae98.diary.domain.core.UseCase
import com.taetae98.diary.domain.memo.repository.MemoRepository
import org.koin.core.annotation.Factory

@Factory
public class UpdateMemoFinishUseCase internal constructor(
    private val repository: MemoRepository,
) : UseCase<UpdateMemoFinishUseCase.Param, Unit>() {
    override suspend fun execute(param: Param) {
        repository.updateFinish(param.memoId, param.isFinish)
    }

    public data class Param(
        val memoId: String,
        val isFinish: Boolean,
    )
}