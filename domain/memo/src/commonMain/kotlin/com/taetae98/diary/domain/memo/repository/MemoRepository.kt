package com.taetae98.diary.domain.memo.repository

import androidx.paging.PagingData
import com.taetae98.diary.core.model.memo.Memo
import com.taetae98.diary.domain.memo.entity.MemoDetail
import kotlinx.coroutines.flow.Flow

public interface MemoRepository {
    public fun page(owner: String?): Flow<PagingData<Memo>>
    public fun findById(id: String): Flow<Memo?>

    public suspend fun upsert(memo: Memo)
    public suspend fun update(id: String, detail: MemoDetail)
    public suspend fun updateFinish(id: String, isFinish: Boolean)
    public suspend fun updateDelete(id: String, isDelete: Boolean)
}
