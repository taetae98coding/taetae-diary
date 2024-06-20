package com.taetae98.diary.data.tag

import com.taetae98.diary.core.database.diary.DiaryDatabase
import com.taetae98.diary.core.database.diary.toEntity
import com.taetae98.diary.core.database.diary.toModel
import com.taetae98.diary.core.database.diary.tag.TagEntity
import com.taetae98.diary.core.model.tag.Tag
import com.taetae98.diary.domain.tag.entity.TagDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Factory

@Factory
internal class TagLocalDataSource(
    private val diaryDatabase: DiaryDatabase,
) {
    fun findAll(owner: String?): Flow<List<Tag>> {
        return diaryDatabase.tag().findAll(owner)
            .map { it.map(TagEntity::toModel) }
    }

    fun findById(id: String): Flow<Tag?> {
        return diaryDatabase.tag().findById(id)
            .map { it?.toModel() }
    }

    suspend fun upsert(tag: Tag) {
        diaryDatabase.tag().upsert(tag.toEntity())
    }

    suspend fun update(id: String, detail: TagDetail) {
        diaryDatabase.tag().update(id, detail.title, detail.description)
    }

    suspend fun updateMemoFilter(id: String, isMemoFilter: Boolean) {
        diaryDatabase.tag().updateMemoFilter(id, isMemoFilter)
    }

    suspend fun updateFinish(id: String, isFinish: Boolean) {
        diaryDatabase.tag().updateFinish(id, isFinish)
    }

    suspend fun updateDelete(id: String, isDelete: Boolean) {
        diaryDatabase.tag().updateDelete(id, isDelete)
    }
}
