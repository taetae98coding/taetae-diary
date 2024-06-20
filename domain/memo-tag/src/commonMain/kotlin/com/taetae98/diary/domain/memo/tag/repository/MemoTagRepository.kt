package com.taetae98.diary.domain.memo.tag.repository

import com.taetae98.diary.core.model.memo.tag.MemoTag
import com.taetae98.diary.core.model.tag.Tag
import kotlinx.coroutines.flow.Flow

public interface MemoTagRepository {
    public fun findTagByMemoId(memoId: String): Flow<List<Tag>>

    public suspend fun upsert(memoId: String, tagIdSet: Set<String>)
    public suspend fun updateMemoTag(memoTag: MemoTag, isSelected: Boolean)
}