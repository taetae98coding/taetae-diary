package com.taetae98.diary.domain.tag.repository

import androidx.paging.PagingData
import com.taetae98.diary.core.model.tag.Tag
import com.taetae98.diary.domain.tag.entity.TagDetail
import kotlinx.coroutines.flow.Flow

public interface TagRepository {
    public fun page(owner: String?): Flow<PagingData<Tag>>
    public fun findById(id: String): Flow<Tag?>

    public suspend fun upsert(tag: Tag)
    public suspend fun update(id: String, detail: TagDetail)
    public suspend fun updateFinish(id: String, isFinish: Boolean)
    public suspend fun updateDelete(id: String, isDelete: Boolean)
}
