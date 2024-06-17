package com.taetae98.diary.core.compose.memo

import androidx.compose.ui.test.ComposeUiTest
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.runComposeUiTest
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.taetae98.diary.core.model.memo.Memo
import com.taetae98.diary.library.paging3.emptyWithLoading
import com.taetae98.diary.library.paging3.fromWithNotLoading
import io.mockk.mockk
import kotlin.test.Test
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class MemoColumnTest {
    @Test
    fun `Refresh Loading 일 때 PlaceHolder 노출된다`() {
        internalTest(
            flow = flowOf(PagingData.emptyWithLoading()),
            test = {
                onAllNodesWithTag("PlaceHolder")
                    .onFirst()
                    .assertExists()
                    .assertIsDisplayed()
            },
        )
    }

    @Test
    fun `Refresh 끝났을 때 0개면 EmptyLayout 노출된다`() {
        internalTest(
            flow = flowOf(PagingData.fromWithNotLoading(emptyList())),
            test = {
                onNodeWithTag("EmptyLayout")
                    .assertExists()
                    .assertIsDisplayed()
            },
        )
    }


    @Test
    fun `메모가 있으면 PlaceHolder, Empty 노출되지 않고 Memo 노출된다`() {
        val memo = mockk<Memo>(relaxed = true, relaxUnitFun = true)
        val pagingData = PagingData.fromWithNotLoading(listOf(memo))

        internalTest(
            flow = flowOf(pagingData),
            test = {
                onNodeWithTag("EmptyLayout")
                    .assertDoesNotExist()

                onNodeWithTag("PlaceHolder")
                    .assertDoesNotExist()

                onNodeWithTag("Memo")
                    .assertExists()
                    .assertIsDisplayed()
            },
        )
    }

    private fun internalTest(
        flow: Flow<PagingData<Memo>>,
        test: ComposeUiTest.() -> Unit,
    ) {
        runComposeUiTest {
            setContent {
                MemoColumn(
                    pagingItems = flow.collectAsLazyPagingItems(),
                    onMemo = {},
                    onFinish = {},
                    onDelete = {},
                )
            }
            test()
        }
    }
}
