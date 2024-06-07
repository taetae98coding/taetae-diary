package com.taetae98.diary.data.memo

import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.paging.PagingData
import com.taetae98.diary.core.model.memo.Memo
import com.taetae98.diary.domain.memo.entity.MemoDetail
import com.taetae98.diary.domain.memo.repository.MemoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Factory

@Factory
internal class MemoRepositoryImpl(
    private val localDataSource: MemoLocalDataSource,
) : MemoRepository {
    override fun page(owner: String?): Flow<PagingData<Memo>> {
        return localDataSource.page(owner = owner)
            .map {
                val loadStates = LoadStates(
                    refresh = LoadState.NotLoading(true),
                    prepend = LoadState.NotLoading(true),
                    append = LoadState.NotLoading(true),
                )

                PagingData.from(data = it, sourceLoadStates = loadStates)
            }
    }

    override fun findById(id: String): Flow<Memo?> {
        return localDataSource.findById(id)
    }

    override suspend fun upsert(memo: Memo) {
        localDataSource.upsert(memo)
    }

    override suspend fun update(id: String, detail: MemoDetail) {
        localDataSource.update(id, detail)
    }

    override suspend fun updateFinish(id: String, isFinish: Boolean) {
        localDataSource.updateFinish(id, isFinish)
    }

    override suspend fun updateDelete(id: String, isDelete: Boolean) {
        localDataSource.updateDelete(id, isDelete)
    }
}
