package com.taetae98.diary.domain.memo.tag.usecase

import com.taetae98.diary.core.model.tag.Tag
import com.taetae98.diary.domain.core.FlowUseCase
import com.taetae98.diary.domain.memo.tag.repository.MemoTagRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Factory

@Factory
public class FindSelectTagByMemoIdUseCase internal constructor(
    private val repository: MemoTagRepository,
) : FlowUseCase<String, List<Tag>>() {
    override fun execute(param: String): Flow<Result<List<Tag>>> {
        return repository.findTagByMemoId(param)
            .map { Result.success(it) }
    }
}
