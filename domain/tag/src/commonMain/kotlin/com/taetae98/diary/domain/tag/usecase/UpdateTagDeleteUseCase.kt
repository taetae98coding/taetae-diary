package com.taetae98.diary.domain.tag.usecase

import com.taetae98.diary.domain.core.UseCase
import com.taetae98.diary.domain.tag.repository.TagRepository
import org.koin.core.annotation.Factory

@Factory
public class UpdateTagDeleteUseCase internal constructor(
    private val repository: TagRepository,
) : UseCase<UpdateTagDeleteUseCase.Param, Unit>() {
    override suspend fun execute(param: Param) {
        repository.updateDelete(param.tagId, param.isDelete)
    }

    public data class Param(
        val tagId: String,
        val isDelete: Boolean,
    )
}
