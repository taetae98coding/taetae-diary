package com.taetae98.diary.domain.tag.usecase

import androidx.paging.PagingData
import com.taetae98.diary.core.model.tag.Tag
import com.taetae98.diary.domain.account.GetAccountUseCase
import com.taetae98.diary.domain.core.FlowUseCase
import com.taetae98.diary.domain.tag.repository.TagRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Factory

@Factory
public class PageTagUseCase internal constructor(
    private val getAccountUseCase: GetAccountUseCase,
    private val repository: TagRepository,
) : FlowUseCase<Unit, PagingData<Tag>>() {
    override fun execute(param: Unit): Flow<Result<PagingData<Tag>>> {
        return getAccountUseCase(Unit).map { it.getOrNull() }
            .flatMapLatest { repository.page(it?.uid) }
            .map { Result.success(it) }
    }
}
