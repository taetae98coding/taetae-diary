package com.taetae98.diary.domain.tag.usecase

import com.taetae98.diary.domain.core.UseCase
import com.taetae98.diary.domain.tag.repository.TagRepository
import org.koin.core.annotation.Factory

@Factory
public class UpdateTagMemoFilterUseCase internal constructor(
    private val repository: TagRepository,
) : UseCase<UpdateTagMemoFilterUseCase.Param, Unit>() {
    override suspend fun execute(param: Param) {
        repository.updateMemoFilter(param.tagId, param.isMemoFilter)
    }

    public data class Param(
        val tagId: String,
        val isMemoFilter: Boolean,
    )
}