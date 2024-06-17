package com.taetae98.diary.data.memo.tag

import com.taetae98.diary.core.database.diary.DiaryDatabase
import com.taetae98.diary.core.model.memo.tag.MemoTag
import com.taetae98.diary.core.model.tag.Tag
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory

@Factory
internal class MemoTagLocalDataSource(
    private val diaryDatabase: DiaryDatabase,
) {
    fun findTagByMemoId(memoId: String): Flow<List<Tag>> {
        return diaryDatabase.memoTag().findByMemoId(memoId)
    }

    suspend fun upsert(memoId: String, tagIdSet: Set<String>) {
        val collection = tagIdSet.map { MemoTag(memoId, it) }
        diaryDatabase.memoTag().upsert(collection)
    }

    suspend fun updateMemoTag(memoTag: MemoTag, isSelected: Boolean) {
        if (isSelected) {
            diaryDatabase.memoTag().upsert(memoTag)
        } else {
            diaryDatabase.memoTag().delete(memoTag)
        }
    }
}
