package com.taetae98.diary.core.database.diary.memo.tag

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.taetae98.diary.core.model.memo.tag.MemoTag
import com.taetae98.diary.core.model.tag.Tag
import kotlinx.coroutines.flow.Flow

@Dao
public interface MemoTagDao {
    @Query(
        """
        SELECT *
        FROM TagEntity
        WHERE isFinish = 0
        AND isDelete = 0
        AND id IN (
            SELECT tagId
            FROM MemoTagEntity
            WHERE memoId = :memoId
        )
    """,
    )
    public fun findByMemoId(memoId: String): Flow<List<Tag>>

    @Upsert(MemoTagEntity::class)
    public suspend fun upsert(memoTag: MemoTag)

    @Transaction
    @Upsert(MemoTagEntity::class)
    public suspend fun upsert(collection: Collection<MemoTag>)

    @Delete(MemoTagEntity::class)
    public suspend fun delete(memoTag: MemoTag)
}
