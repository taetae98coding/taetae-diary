package com.taetae98.diary.data.memo

import com.taetae98.diary.core.database.diary.DiaryDatabase
import com.taetae98.diary.core.database.diary.toEntity
import com.taetae98.diary.core.database.diary.toModel
import com.taetae98.diary.core.database.diary.memo.MemoEntity
import com.taetae98.diary.core.model.memo.Memo
import com.taetae98.diary.domain.memo.entity.MemoDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Factory

@Factory
internal class MemoLocalDataSource(
    private val diaryDatabase: DiaryDatabase,
) {
    fun page(owner: String?, tagIdList: List<String>): Flow<List<Memo>> {
        return diaryDatabase.memo().page(owner, tagIdList, tagIdList.size)
            .map { it.map(MemoEntity::toModel) }
    }

    fun pageByTagId(tagId: String): Flow<List<Memo>> {
        return diaryDatabase.memo().pageByTagId(tagId)
            .map { it.map(MemoEntity::toModel) }
    }

    fun findById(id: String): Flow<Memo?> {
        return diaryDatabase.memo().findById(id)
            .map { it?.toModel() }
    }

    suspend fun upsert(memo: Memo) {
        diaryDatabase.memo().upsert(memo.toEntity())
    }

    suspend fun update(id: String, detail: MemoDetail) {
        diaryDatabase.memo().update(id, detail.title, detail.description)
    }

    suspend fun updateFinish(id: String, isFinish: Boolean) {
        diaryDatabase.memo().updateFinish(id, isFinish)
    }

    suspend fun updateDelete(id: String, isDelete: Boolean) {
        diaryDatabase.memo().updateDelete(id, isDelete)
    }
}
