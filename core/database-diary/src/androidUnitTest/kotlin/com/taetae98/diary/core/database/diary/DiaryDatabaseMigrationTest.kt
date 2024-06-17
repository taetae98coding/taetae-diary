package com.taetae98.diary.core.database.diary

import androidx.room.testing.MigrationTestHelper
import androidx.test.platform.app.InstrumentationRegistry
import com.taetae98.diary.core.database.diary.migration.ROOM_MIGRATION_1_2
import com.taetae98.diary.core.database.diary.migration.ROOM_MIGRATION_2_3
import kotlin.test.Test
import org.junit.Rule
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class DiaryDatabaseMigrationTest {
    @get:Rule
    val helper = MigrationTestHelper(
        instrumentation = InstrumentationRegistry.getInstrumentation(),
        databaseClass = DiaryDatabase::class.java,
    )

    @Test
    fun migrate1To3() {
        helper.createDatabase(DATABASE_NAME, 1).close()
        helper.runMigrationsAndValidate(DATABASE_NAME, 2, true, ROOM_MIGRATION_1_2).close()
        helper.runMigrationsAndValidate(DATABASE_NAME, 3, true, ROOM_MIGRATION_2_3).close()
    }

    companion object {
        private const val DATABASE_NAME = "migration.db"
    }
}
