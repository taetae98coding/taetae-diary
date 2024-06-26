package com.taetae98.diary.core.database.holiday

import androidx.room.testing.MigrationTestHelper
import androidx.test.platform.app.InstrumentationRegistry
import kotlin.test.Test
import org.junit.Rule
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class HolidayDatabaseMigrationTest {
    @get:Rule
    val helper = MigrationTestHelper(
        instrumentation = InstrumentationRegistry.getInstrumentation(),
        databaseClass = HolidayDatabase::class.java,
    )

    @Test
    fun migration() {
        helper.createDatabase(DATABASE_NAME, 1).close()
    }

    companion object {
        private const val DATABASE_NAME = "migration.db"
    }
}