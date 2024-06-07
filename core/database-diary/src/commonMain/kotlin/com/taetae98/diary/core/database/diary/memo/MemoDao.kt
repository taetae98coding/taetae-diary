package com.taetae98.diary.core.database.diary.memo

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.taetae98.diary.core.model.memo.Memo
import kotlinx.coroutines.flow.Flow

@Dao
public interface MemoDao {
    @Query(
        """
        SELECT * 
        FROM MemoEntity 
        WHERE ((owner = :owner) OR (owner IS NULL AND :owner IS NULL))
        AND isFinish = 0
        AND isDelete = 0
    """,
    )
    public fun page(owner: String?): Flow<List<Memo>>

    @Query("SELECT * FROM MemoEntity WHERE id = :id")
    public fun findById(id: String): Flow<Memo?>

    @Upsert(MemoEntity::class)
    public suspend fun upsert(memo: Memo)

    @Query(
        """
        UPDATE MemoEntity
        SET title = :title, description = :description
        WHERE id = :id
    """,
    )
    public suspend fun update(id: String, title: String, description: String)

    @Query("UPDATE MemoEntity SET isFinish = :isFinish WHERE id = :id")
    public suspend fun updateFinish(id: String, isFinish: Boolean)

    @Query("UPDATE MemoEntity SET isDelete = :isDelete WHERE id = :id")
    public suspend fun updateDelete(id: String, isDelete: Boolean)
}