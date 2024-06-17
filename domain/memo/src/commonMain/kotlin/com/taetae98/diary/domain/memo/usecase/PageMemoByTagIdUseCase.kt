package com.taetae98.diary.domain.memo.usecase

import androidx.paging.PagingData
import com.taetae98.diary.core.model.memo.Memo
import com.taetae98.diary.domain.core.FlowUseCase
import com.taetae98.diary.domain.memo.repository.MemoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Factory

@Factory
public class PageMemoByTagIdUseCase internal constructor(
    private val repository: MemoRepository,
) : FlowUseCase<String, PagingData<Memo>>() {
    override fun execute(param: String): Flow<Result<PagingData<Memo>>> {
        return repository.pageByTagId(param).map { Result.success(it) }
    }
}