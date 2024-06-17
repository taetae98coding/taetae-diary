package com.taetae98.diary.data.tag

import com.taetae98.diary.core.model.tag.Tag
import com.taetae98.diary.domain.tag.entity.TagDetail
import com.taetae98.diary.domain.tag.repository.TagRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory

@Factory
internal class TagRepositoryImpl(
    private val localDataSource: TagLocalDataSource,
) : TagRepository {
    override fun findAll(owner: String?): Flow<List<Tag>> {
        return localDataSource.findAll(owner = owner)
    }

    override fun findById(id: String): Flow<Tag?> {
        return localDataSource.findById(id)
    }

    override suspend fun upsert(tag: Tag) {
        localDataSource.upsert(tag)
    }

    override suspend fun update(id: String, detail: TagDetail) {
        localDataSource.update(id, detail)
    }

    override suspend fun updateMemoFilter(id: String, isMemoFilter: Boolean) {
        localDataSource.updateMemoFilter(id, isMemoFilter)
    }

    override suspend fun updateFinish(id: String, isFinish: Boolean) {
        localDataSource.updateFinish(id, isFinish)
    }

    override suspend fun updateDelete(id: String, isDelete: Boolean) {
        localDataSource.updateDelete(id, isDelete)
    }
}
