package com.taetae98.diary.domain.memo.tag.usecase

import com.taetae98.diary.core.model.tag.SelectTag
import com.taetae98.diary.core.model.tag.Tag
import com.taetae98.diary.domain.core.FlowUseCase
import com.taetae98.diary.domain.tag.usecase.FindAllTagUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import org.koin.core.annotation.Factory

@Factory
public class FindMemoTagUseCase internal constructor(
    private val findSelectTagByMemoIdUseCase: FindSelectTagByMemoIdUseCase,
    private val findAllTagUseCase: FindAllTagUseCase,
) : FlowUseCase<String, List<SelectTag>>() {
    override fun execute(param: String): Flow<Result<List<SelectTag>>> {
        val tagListFlow = findSelectTagByMemoIdUseCase(param)
        val tagAllListFlow = findAllTagUseCase(Unit)

        return combine(tagListFlow, tagAllListFlow) { tagListResult, tagAllList ->
            if (tagListResult.isFailure) {
                Result.failure(requireNotNull(tagListResult.exceptionOrNull()))
            } else if (tagAllList.isFailure) {
                Result.failure(requireNotNull(tagAllList.exceptionOrNull()))
            } else {
                map(tagListResult.getOrThrow(), tagAllList.getOrThrow())
            }
        }
    }

    private fun map(tagList: List<Tag>, tagAllList: List<Tag>): Result<List<SelectTag>> {
        val tagIdSet = tagList.map { it.id }.toSet()
        val selectTagPagingData = tagAllList.map {
            SelectTag(
                id = it.id,
                title = it.title,
                isSelected = it.id in tagIdSet,
            )
        }

        return Result.success(selectTagPagingData)
    }
}
