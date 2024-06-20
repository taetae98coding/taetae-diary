package com.taetae98.diary.data.memo.tag

import com.taetae98.diary.core.database.diary.DiaryDatabase
import com.taetae98.diary.core.database.diary.toEntity
import com.taetae98.diary.core.database.diary.toModel
import com.taetae98.diary.core.database.diary.memo.tag.MemoTagEntity
import com.taetae98.diary.core.database.diary.tag.TagEntity
import com.taetae98.diary.core.model.memo.tag.MemoTag
import com.taetae98.diary.core.model.tag.Tag
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Factory

@Factory
internal class MemoTagLocalDataSource(
    private val diaryDatabase: DiaryDatabase,
) {
    fun findTagByMemoId(memoId: String): Flow<List<Tag>> {
        return diaryDatabase.memoTag().findByMemoId(memoId)
            .map { it.map(TagEntity::toModel) }
    }

    suspend fun upsert(memoId: String, tagIdSet: Set<String>) {
        val collection = tagIdSet.map { MemoTagEntity(memoId, it) }
        diaryDatabase.memoTag().upsert(collection)
    }

    suspend fun updateMemoTag(memoTag: MemoTag, isSelected: Boolean) {
        if (isSelected) {
            diaryDatabase.memoTag().upsert(memoTag.toEntity())
        } else {
            diaryDatabase.memoTag().delete(memoTag.toEntity())
        }
    }
}
