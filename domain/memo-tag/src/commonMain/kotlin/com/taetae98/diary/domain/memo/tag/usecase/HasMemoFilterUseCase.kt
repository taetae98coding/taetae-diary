package com.taetae98.diary.domain.memo.tag.usecase

import com.taetae98.diary.domain.core.FlowUseCase
import com.taetae98.diary.domain.tag.usecase.FindAllTagUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Factory

@Factory
public class HasMemoFilterUseCase internal constructor(
    private val findAllTagUseCase: FindAllTagUseCase,
) : FlowUseCase<Unit, Boolean>() {
    override fun execute(param: Unit): Flow<Result<Boolean>> {
        return findAllTagUseCase(Unit).map { it.getOrNull().orEmpty() }
            .map { list -> list.any { it.isMemoFilter } }
            .map { Result.success(it) }
    }
}
