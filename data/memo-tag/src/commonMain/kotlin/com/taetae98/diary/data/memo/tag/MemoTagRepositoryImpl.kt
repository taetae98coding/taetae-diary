package com.taetae98.diary.data.memo.tag

import com.taetae98.diary.core.model.memo.tag.MemoTag
import com.taetae98.diary.core.model.tag.Tag
import com.taetae98.diary.domain.memo.tag.repository.MemoTagRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory

@Factory
internal class MemoTagRepositoryImpl(
    private val localDataSource: MemoTagLocalDataSource,
) : MemoTagRepository {
    override fun findTagByMemoId(memoId: String): Flow<List<Tag>> {
        return localDataSource.findTagByMemoId(memoId)
    }

    override suspend fun upsert(memoId: String, tagIdSet: Set<String>) {
        localDataSource.upsert(memoId, tagIdSet)
    }

    override suspend fun updateMemoTag(memoTag: MemoTag, isSelected: Boolean) {
        localDataSource.updateMemoTag(memoTag, isSelected)
    }
}
