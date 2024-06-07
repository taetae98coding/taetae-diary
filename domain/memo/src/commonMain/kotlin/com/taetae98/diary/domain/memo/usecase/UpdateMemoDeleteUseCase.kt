package com.taetae98.diary.domain.memo.usecase

import com.taetae98.diary.domain.core.UseCase
import com.taetae98.diary.domain.memo.repository.MemoRepository
import org.koin.core.annotation.Factory

@Factory
public class UpdateMemoDeleteUseCase internal constructor(
    private val repository: MemoRepository,
) : UseCase<UpdateMemoDeleteUseCase.Param, Unit>() {
    override suspend fun execute(param: Param) {
        repository.updateDelete(param.memoId, param.isDelete)
    }

    public data class Param(
        val memoId: String,
        val isDelete: Boolean,
    )
}