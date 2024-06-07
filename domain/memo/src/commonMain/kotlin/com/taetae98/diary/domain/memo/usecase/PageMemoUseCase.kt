package com.taetae98.diary.domain.memo.usecase

import androidx.paging.PagingData
import com.taetae98.diary.core.model.memo.Memo
import com.taetae98.diary.domain.account.GetAccountUseCase
import com.taetae98.diary.domain.core.FlowUseCase
import com.taetae98.diary.domain.memo.repository.MemoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Factory

@Factory
public class PageMemoUseCase internal constructor(
    private val getAccountUseCase: GetAccountUseCase,
    private val repository: MemoRepository,
) : FlowUseCase<Unit, PagingData<Memo>>() {
    override fun execute(param: Unit): Flow<Result<PagingData<Memo>>> {
        return getAccountUseCase(Unit).map { it.getOrNull() }
            .flatMapLatest { repository.page(it?.uid) }
            .map { Result.success(it) }
    }
}
