package com.taetae98.diary.feature.tag.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import app.cash.paging.compose.LazyPagingItems
import app.cash.paging.compose.itemKey
import com.taetae98.diary.core.compose.empty.EmptyLayout
import com.taetae98.diary.core.compose.swipe.FinishAndDeleteSwipe
import com.taetae98.diary.core.model.tag.Tag

@Composable
internal fun TagColumn(
    modifier: Modifier = Modifier,
    pagingItems: LazyPagingItems<Tag>,
    onTag: (Tag) -> Unit,
    onFinish: (Tag) -> Unit,
    onDelete: (Tag) -> Unit,
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
                    contentType = { "tag" },
                ) {
                    TagCompose(
                        modifier = Modifier.fillParentMaxWidth()
                            .testTag("PlaceHolder"),
                        tag = null,
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
                        text = "태그가 없어요 🐵",
                    )
                }
            }
        }

        items(
            count = pagingItems.itemCount,
            key = pagingItems.itemKey { it.id },
            contentType = { "tag" },
        ) {
            FinishAndDeleteSwipe(
                modifier = Modifier.fillParentMaxWidth()
                    .animateItemPlacement()
                    .clip(CardDefaults.shape)
                    .testTag("Tag"),
                onFinish = { pagingItems[it]?.let(onFinish) },
                onDelete = { pagingItems[it]?.let(onDelete) },
            ) {
                TagCompose(
                    modifier = Modifier.fillMaxWidth(),
                    tag = pagingItems[it],
                    onClick = { pagingItems[it]?.let(onTag) },
                )
            }
        }
    }
}
