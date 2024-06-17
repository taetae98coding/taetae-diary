package com.taetae98.diary.domain.memo.usecase

import androidx.paging.PagingData
import com.taetae98.diary.core.model.memo.Memo
import com.taetae98.diary.domain.account.GetAccountUseCase
import com.taetae98.diary.domain.core.FlowUseCase
import com.taetae98.diary.domain.memo.repository.MemoRepository
import com.taetae98.diary.domain.tag.usecase.FindAllTagUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Factory

@Factory
public class PageMemoUseCase internal constructor(
    private val getAccountUseCase: GetAccountUseCase,
    private val findAllTagUseCase: FindAllTagUseCase,
    private val repository: MemoRepository,
) : FlowUseCase<Unit, PagingData<Memo>>() {
    override fun execute(param: Unit): Flow<Result<PagingData<Memo>>> {
        val accountFlow = getAccountUseCase(Unit)
        val tagIdListFlow = findAllTagUseCase(Unit).map { result -> result.map { list -> list.filter { it.isMemoFilter }.map { it.id } } }

        return combine(accountFlow, tagIdListFlow) { account, tagIdList ->
            if (account.isFailure) {
                flowOf(Result.failure(requireNotNull(account.exceptionOrNull())))
            } else if (tagIdList.isFailure) {
                flowOf(Result.failure(requireNotNull(tagIdList.exceptionOrNull())))
            } else {
                repository.page(account.getOrThrow().uid, tagIdList = tagIdList.getOrThrow()).map { Result.success(it) }
            }
        }.flatMapLatest {
            it
        }
    }
}
