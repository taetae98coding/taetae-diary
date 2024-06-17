package com.taetae98.diary.core.database.diary.migration

import androidx.room.migration.Migration
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.execSQL

internal val ROOM_MIGRATION_1_2 = object : Migration(1, 2) {
    val sql = """
        CREATE TABLE IF NOT EXISTS TagEntity(
            id TEXT NOT NULL,
            title TEXT NOT NULL,
            description TEXT NOT NULL,
            isFinish INTEGER NOT NULL,
            isDelete INTEGER NOT NULL,
            owner TEXT, 
            PRIMARY KEY(id)
        )
    """.trimIndent()

    override fun migrate(connection: SQLiteConnection) {
        connection.execSQL(sql)
    }
}

internal val ROOM_MIGRATION_2_3 = object : Migration(2, 3) {
    val addIsMemoTagColumnTagEntity = """
        ALTER TABLE TagEntity ADD COLUMN isMemoFilter INTEGER NOT NULL DEFAULT 0
    """.trimIndent()

    val createMemoTagEntityTableSql = """
        CREATE TABLE IF NOT EXISTS MemoTagEntity(
            memoId TEXT NOT NULL, 
            tagId TEXT NOT NULL, 
            PRIMARY KEY(memoId, tagId), 
            FOREIGN KEY(memoId) REFERENCES MemoEntity(id) ON UPDATE CASCADE ON DELETE CASCADE, 
            FOREIGN KEY(tagId) REFERENCES TagEntity(id) ON UPDATE CASCADE ON DELETE CASCADE
        )
    """.trimIndent()

    val createMemoTagEntityMemoIdIndex = """
        CREATE INDEX IF NOT EXISTS index_MemoTagEntity_memoId ON MemoTagEntity(memoId)
    """.trimIndent()

    val createMemoTagEntityTagIdIndex = """
        CREATE INDEX IF NOT EXISTS index_MemoTagEntity_tagId ON MemoTagEntity(tagId)
    """.trimIndent()

    override fun migrate(connection: SQLiteConnection) {
        connection.execSQL(addIsMemoTagColumnTagEntity)
        connection.execSQL(createMemoTagEntityTableSql)
        connection.execSQL(createMemoTagEntityMemoIdIndex)
        connection.execSQL(createMemoTagEntityTagIdIndex)
    }
}
