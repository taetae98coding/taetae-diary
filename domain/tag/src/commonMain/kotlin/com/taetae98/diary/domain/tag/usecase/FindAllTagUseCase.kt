package com.taetae98.diary.domain.tag.usecase

import com.taetae98.diary.core.model.tag.Tag
import com.taetae98.diary.domain.account.GetAccountUseCase
import com.taetae98.diary.domain.core.FlowUseCase
import com.taetae98.diary.domain.tag.repository.TagRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Factory

@Factory
public class FindAllTagUseCase internal constructor(
    private val getAccountUseCase: GetAccountUseCase,
    private val repository: TagRepository,
) : FlowUseCase<Unit, List<Tag>>() {
    override fun execute(param: Unit): Flow<Result<List<Tag>>> {
        return getAccountUseCase(Unit).map { it.getOrNull() }
            .flatMapLatest { repository.findAll(it?.uid) }
            .map { Result.success(it) }
    }
}
