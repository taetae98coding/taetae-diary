package com.taetae98.diary.feature.memo.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import app.cash.paging.compose.LazyPagingItems
import app.cash.paging.compose.itemKey
import com.taetae98.diary.core.compose.swipe.FinishAndDeleteSwipe
import com.taetae98.diary.core.model.memo.Memo

@Composable
internal fun MemoColumn(
    modifier: Modifier = Modifier,
    pagingItems: LazyPagingItems<Memo>,
    onMemo: (Memo) -> Unit,
    onFinish: (Memo) -> Unit,
    onDelete: (Memo) -> Unit,
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(vertical = 4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        if (pagingItems.itemCount == 0) {
            if (pagingItems.loadState.refresh == LoadState.Loading) {
                items(
                    count = 3,
                    contentType = { "memo" },
                ) {
                    MemoCompose(
                        modifier = Modifier.fillParentMaxWidth(),
                        memo = null,
                        onClick = {},
                    )
                }
            } else {
                item(
                    key = "Empty",
                    contentType = "empty",
                ) {
                    MemoEmptyLayout(modifier = Modifier.fillParentMaxSize())
                }
            }
        }

        items(
            count = pagingItems.itemCount,
            key = pagingItems.itemKey { it.id },
            contentType = { "Memo" },
        ) {
            FinishAndDeleteSwipe(
                modifier = Modifier.fillParentMaxWidth()
                    .animateItemPlacement()
                    .clip(CardDefaults.shape),
                onFinish = { pagingItems[it]?.let(onFinish) },
                onDelete = { pagingItems[it]?.let(onDelete) },
            ) {
                MemoCompose(
                    modifier = Modifier.fillMaxWidth(),
                    memo = pagingItems[it],
                    onClick = { pagingItems[it]?.let(onMemo) },
                )
            }
        }
    }
}
