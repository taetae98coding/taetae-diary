package com.taetae98.diary.data.tag

import androidx.paging.PagingData
import com.taetae98.diary.core.model.tag.Tag
import com.taetae98.diary.domain.tag.entity.TagDetail
import com.taetae98.diary.domain.tag.repository.TagRepository
import com.taetae98.diary.library.paging3.fromWithNotLoading
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Factory

@Factory
internal class TagRepositoryImpl(
    private val localDataSource: TagLocalDataSource,
) : TagRepository {
    override fun page(owner: String?): Flow<PagingData<Tag>> {
        return localDataSource.page(owner = owner)
            .map { PagingData.fromWithNotLoading(it) }
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

    override suspend fun updateFinish(id: String, isFinish: Boolean) {
        localDataSource.updateFinish(id, isFinish)
    }

    override suspend fun updateDelete(id: String, isDelete: Boolean) {
        localDataSource.updateDelete(id, isDelete)
    }
}
