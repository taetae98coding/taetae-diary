package com.taetae98.diary.core.database.diary.tag

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.taetae98.diary.core.model.tag.Tag
import kotlinx.coroutines.flow.Flow

@Dao
public interface TagDao {
    @Query(
        """
        SELECT * 
        FROM TagEntity 
        WHERE ((owner = :owner) OR (owner IS NULL AND :owner IS NULL))
        AND isFinish = 0
        AND isDelete = 0
    """,
    )
    public fun page(owner: String?): Flow<List<Tag>>

    @Query("SELECT * FROM TagEntity WHERE id = :id")
    public fun findById(id: String): Flow<Tag?>

    @Upsert(TagEntity::class)
    public suspend fun upsert(tag: Tag)

    @Query(
        """
        UPDATE TagEntity
        SET title = :title, description = :description
        WHERE id = :id
    """,
    )
    public suspend fun update(id: String, title: String, description: String)

    @Query("UPDATE TagEntity SET isFinish = :isFinish WHERE id = :id")
    public suspend fun updateFinish(id: String, isFinish: Boolean)

    @Query("UPDATE TagEntity SET isDelete = :isDelete WHERE id = :id")
    public suspend fun updateDelete(id: String, isDelete: Boolean)
}