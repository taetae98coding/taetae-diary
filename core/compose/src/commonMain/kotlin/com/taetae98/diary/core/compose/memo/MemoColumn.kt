package com.taetae98.diary.core.compose.memo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.paging.LoadState
import app.cash.paging.compose.LazyPagingItems
import app.cash.paging.compose.itemKey
import com.taetae98.diary.core.compose.dimension.DpDefault
import com.taetae98.diary.core.compose.empty.EmptyLayout
import com.taetae98.diary.core.compose.swipe.FinishAndDeleteSwipe
import com.taetae98.diary.core.model.memo.Memo

@Composable
public fun MemoColumn(
    modifier: Modifier = Modifier,
    pagingItems: LazyPagingItems<Memo>,
    onMemo: (Memo) -> Unit,
    onFinish: (Memo) -> Unit,
    onDelete: (Memo) -> Unit,
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(vertical = DpDefault.ItemSpaceDp),
        verticalArrangement = Arrangement.spacedBy(DpDefault.ItemSpaceDp),
    ) {
        if (pagingItems.itemCount == 0) {
            if (pagingItems.loadState.refresh == LoadState.Loading) {
                items(
                    count = 3,
                    contentType = { "memo" },
                ) {
                    MemoCompose(
                        modifier = Modifier.fillParentMaxWidth()
                            .testTag("PlaceHolder"),
                        memo = null,
                        onClick = {},
                    )
                }
            } else {
                item(
                    key = "Empty",
                    contentType = "empty",
                ) {
                    EmptyLayout(
                        modifier = Modifier.fillParentMaxSize()
                            .testTag("EmptyLayout"),
                        text = "메모가 없어요 🐶",
                    )
                }
            }
        }

        items(
            count = pagingItems.itemCount,
            key = pagingItems.itemKey { it.id },
            contentType = { "Memo" },
        ) {
            val uiState = pagingItems[it]

            FinishAndDeleteSwipe(
                modifier = Modifier.fillParentMaxWidth()
                    .animateItemPlacement()
                    .clip(CardDefaults.shape)
                    .testTag("Memo"),
                onFinish = {
                    uiState?.let(onFinish)
                    uiState != null
                },
                onDelete = {
                    uiState?.let(onDelete)
                    uiState != null
                },
            ) {
                MemoCompose(
                    modifier = Modifier.fillMaxWidth(),
                    memo = uiState,
                    onClick = { uiState?.let(onMemo) },
                )
            }
        }
    }
}
