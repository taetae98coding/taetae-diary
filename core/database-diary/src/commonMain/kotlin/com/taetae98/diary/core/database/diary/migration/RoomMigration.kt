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
