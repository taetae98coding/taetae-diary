package com.taetae98.diary.feature.tag.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import com.taetae98.diary.core.compose.dimension.DpDefault
import com.taetae98.diary.core.compose.empty.EmptyLayout
import com.taetae98.diary.core.compose.swipe.FinishAndDeleteSwipe
import com.taetae98.diary.core.model.tag.Tag
import kotlinx.collections.immutable.ImmutableList

@Composable
internal fun TagColumn(
    modifier: Modifier = Modifier,
    tagList: () -> ImmutableList<Tag>?,
    onTag: (Tag) -> Unit,
    onFinish: (Tag) -> Unit,
    onDelete: (Tag) -> Unit,
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(vertical = DpDefault.ItemSpaceDp),
        verticalArrangement = Arrangement.spacedBy(DpDefault.ItemSpaceDp),
    ) {
        val items = tagList()

        if (items == null) {
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
        } else if (items.isEmpty()) {
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
        } else {
            items(
                items = items,
                key = { it.id },
                contentType = { "tag" },
            ) {
                FinishAndDeleteSwipe(
                    modifier = Modifier.fillParentMaxWidth()
                        .animateItemPlacement()
                        .clip(CardDefaults.shape)
                        .testTag("Tag"),
                    onFinish = {
                        onFinish(it)
                        true
                    },
                    onDelete = {
                        onDelete(it)
                        true
                    },
                ) {
                    TagCompose(
                        modifier = Modifier.fillMaxWidth(),
                        tag = it,
                        onClick = { onTag(it) },
                    )
                }
            }
        }
    }
}
