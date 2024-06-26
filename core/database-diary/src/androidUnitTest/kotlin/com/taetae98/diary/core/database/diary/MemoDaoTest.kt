package com.taetae98.diary.core.database.diary

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.taetae98.diary.core.database.diary.memo.MemoEntity
import com.taetae98.diary.core.database.diary.memo.tag.MemoTagEntity
import com.taetae98.diary.core.database.diary.tag.TagEntity
import com.taetae98.diary.core.model.memo.Memo
import com.taetae98.diary.core.model.memo.tag.MemoTag
import com.taetae98.diary.core.model.tag.Tag
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class MemoDaoTest {
    private lateinit var diaryDatabase: DiaryDatabase

    @Before
    fun before() {
        diaryDatabase = Room.inMemoryDatabaseBuilder(
            context = InstrumentationRegistry.getInstrumentation().context,
            klass = DiaryDatabase::class.java,
        ).build()
    }

    @Test
    fun `태그가 있는 메모가 없을 때 빈 태그로 page 호출하면 모든 메모가 나온다`() = runTest {
        // Given
        diaryDatabase.memo().upsert(memo1)
        diaryDatabase.memo().upsert(memo2)

        // When
        val tagIdList = emptyList<String>()
        val result = diaryDatabase.memo().page(null, tagIdList, tagIdList.size).firstOrNull()

        //Then
        assertContentEquals(listOf(memo1, memo2), result)
    }

    @Test
    fun `태그가 있는 메모가 없을 때 태그로 page 호출하면 EmptyList가 나온다`() = runTest {
        // Given
        diaryDatabase.memo().upsert(memo1)
        diaryDatabase.memo().upsert(memo2)
        diaryDatabase.tag().upsert(tag1)

        // When
        val tagIdList = listOf(tag1).map { it.id }
        val result = diaryDatabase.memo().page(null, tagIdList, tagIdList.size).firstOrNull()

        //Then
        assertContentEquals(emptyList(), result)
    }

    @Test
    fun `태그가 있는 메모가 하나 있을 때 태그로 page 호출하면 메모 하나가 나온다`() = runTest {
        // Given
        diaryDatabase.memo().upsert(memo1)
        diaryDatabase.memo().upsert(memo2)
        diaryDatabase.tag().upsert(tag1)
        diaryDatabase.memoTag().upsert(memoTag)

        // When
        val tagIdList = listOf(tag1).map { it.id }
        val result = diaryDatabase.memo().page(null, tagIdList, tagIdList.size).firstOrNull()

        //Then
        assertContentEquals(listOf(memo1), result)
    }

    companion object {
        private val memo1 = MemoEntity(id = "1", title = "memo1", description = "", isFinish = false, isDelete = false, owner = null)
        private val memo2 = MemoEntity(id = "2", title = "memo2", description = "", isFinish = false, isDelete = false, owner = null)

        private val tag1 = TagEntity(id = "1", title = "tag1", description = "", isMemoFilter = true, isFinish = false, isDelete = false, owner = null)

        private val memoTag = MemoTagEntity(memo1.id, tag1.id)
    }
}