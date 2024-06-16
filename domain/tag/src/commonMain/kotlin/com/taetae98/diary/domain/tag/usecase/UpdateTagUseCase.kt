package com.taetae98.diary.domain.tag.usecase

import com.taetae98.diary.domain.core.UseCase
import com.taetae98.diary.domain.tag.entity.TagDetail
import com.taetae98.diary.domain.tag.repository.TagRepository
import kotlinx.coroutines.flow.firstOrNull
import org.koin.core.annotation.Factory

@Factory
public class UpdateTagUseCase  internal constructor(
    private val repository: TagRepository,
) : UseCase<UpdateTagUseCase.Param, Unit>() {
    override suspend fun execute(param: Param) {
        val detail = if (param.detail.title.isEmpty()) {
            param.detail.copy(
                title = getOriginTitle(param.tagId)
            )
        } else {
            param.detail
        }

        repository.update(param.tagId, detail)
    }

    private suspend fun getOriginTitle(tagId: String): String {
        val tag = requireNotNull(repository.findById(tagId).firstOrNull())

        return tag.title
    }

    public data class Param(
        val tagId: String,
        val detail: TagDetail,
    )
}
