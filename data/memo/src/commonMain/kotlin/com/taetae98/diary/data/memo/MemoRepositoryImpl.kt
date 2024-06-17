package com.taetae98.diary.data.memo

import androidx.paging.PagingData
import com.taetae98.diary.core.model.memo.Memo
import com.taetae98.diary.domain.memo.entity.MemoDetail
import com.taetae98.diary.domain.memo.repository.MemoRepository
import com.taetae98.diary.library.paging3.fromWithNotLoading
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Factory

@Factory
internal class MemoRepositoryImpl(
    private val localDataSource: MemoLocalDataSource,
) : MemoRepository {
    override fun page(owner: String?, tagIdList: List<String>): Flow<PagingData<Memo>> {
        return localDataSource.page(owner = owner, tagIdList = tagIdList)
            .map { PagingData.fromWithNotLoading(it) }
    }

    override fun pageByTagId(tagId: String): Flow<PagingData<Memo>> {
        return localDataSource.pageByTagId(tagId)
            .map { PagingData.fromWithNotLoading(it) }
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
