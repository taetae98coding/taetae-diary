package com.taetae98.diary.core.database.holiday

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [HolidayEntity::class],
    version = 1,
)
@TypeConverters(LocalDateConverter::class)
public abstract class HolidayDatabase : RoomDatabase() {
    public abstract fun holiday(): HolidayDao
}
