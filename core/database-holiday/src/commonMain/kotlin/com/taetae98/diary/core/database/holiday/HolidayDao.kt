package com.taetae98.diary.core.database.holiday

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.taetae98.diary.core.model.holiday.Holiday
import kotlinx.coroutines.flow.Flow

@Dao
public interface HolidayDao {
    @Query(
        """
        SELECT *
        FROM HolidayEntity
        WHERE (CAST(STRFTIME('%Y', start) AS INTEGER) = :year OR CAST(STRFTIME('%Y', endInclusive) AS INTEGER) = :year)
        AND (CAST(STRFTIME('%m', start) AS INTEGER) = :month OR CAST(STRFTIME('%m', endInclusive) AS INTEGER) = :month)
    """,
    )
    public fun findHoliday(year: Int, month: Int): Flow<List<HolidayEntity>>

    @Insert(HolidayEntity::class)
    public suspend fun insert(holiday: List<HolidayEntity>)

    @Query(
        """
        DELETE FROM HolidayEntity 
        WHERE (CAST(STRFTIME('%Y', start) AS INTEGER) = :year OR CAST(STRFTIME('%Y', endInclusive) AS INTEGER) = :year)
        AND (CAST(STRFTIME('%m', start) AS INTEGER) = :month OR CAST(STRFTIME('%m', endInclusive) AS INTEGER) = :month)
    """,
    )
    public suspend fun delete(year: Int, month: Int)

    @Transaction
    public suspend fun upsert(year: Int, month: Int, holiday: List<HolidayEntity>) {
        delete(year, month)
        insert(holiday)
    }
}
