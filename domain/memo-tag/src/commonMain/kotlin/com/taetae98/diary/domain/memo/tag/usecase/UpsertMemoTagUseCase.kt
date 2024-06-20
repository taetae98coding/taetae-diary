package com.taetae98.diary.domain.memo.tag.usecase

import com.taetae98.diary.domain.core.UseCase
import com.taetae98.diary.domain.memo.tag.repository.MemoTagRepository
import org.koin.core.annotation.Factory

@Factory
public class UpsertMemoTagUseCase internal constructor(
    private val repository: MemoTagRepository,
) : UseCase<UpsertMemoTagUseCase.Param, Unit>() {
    override suspend fun execute(param: Param) {
        repository.upsert(param.memoId, param.tagIdSet)
    }

    public data class Param(
        val memoId: String,
        val tagIdSet: Set<String>,
    )
}