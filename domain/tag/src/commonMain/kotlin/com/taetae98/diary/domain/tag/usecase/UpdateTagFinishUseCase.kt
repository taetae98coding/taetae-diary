package com.taetae98.diary.domain.tag.usecase

import com.taetae98.diary.domain.core.UseCase
import com.taetae98.diary.domain.tag.repository.TagRepository
import org.koin.core.annotation.Factory

@Factory
public class UpdateTagFinishUseCase internal constructor(
    private val repository: TagRepository,
) : UseCase<UpdateTagFinishUseCase.Param, Unit>() {
    override suspend fun execute(param: Param) {
        repository.updateFinish(param.tagId, param.isFinish)
    }

    public data class Param(
        val tagId: String,
        val isFinish: Boolean,
    )
}
