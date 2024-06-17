package com.taetae98.diary.domain.memo.tag.usecase

import com.taetae98.diary.core.model.memo.tag.MemoTag
import com.taetae98.diary.domain.core.UseCase
import com.taetae98.diary.domain.memo.tag.repository.MemoTagRepository
import org.koin.core.annotation.Factory

@Factory
public class UpdateMemoTagUseCase internal constructor(
    private val repository: MemoTagRepository,
) : UseCase<UpdateMemoTagUseCase.Param, Unit>() {
    override suspend fun execute(param: Param) {
        repository.updateMemoTag(param.memoTag, param.isSelected)
    }

    public data class Param(
        val memoTag: MemoTag,
        val isSelected: Boolean,
    )
}
