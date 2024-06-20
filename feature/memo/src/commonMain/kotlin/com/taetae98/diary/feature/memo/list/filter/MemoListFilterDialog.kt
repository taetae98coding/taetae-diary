package com.taetae98.diary.feature.memo.list.filter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.FilterChip
import androidx.compose.material3.LocalMinimumInteractiveComponentEnforcement
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.taetae98.diary.core.compose.dimension.DpDefault
import com.taetae98.diary.core.compose.empty.EmptyLayout
import com.taetae98.diary.core.compose.icon.TagIcon
import com.taetae98.diary.core.model.tag.Tag

@Composable
internal fun MemoListFilterDialog(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    state: MemoListFilterState,
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = onDismissRequest,
        confirmButton = {},
        title = { Text(text = "필터") },
        text = {
            if (state.tagList.isEmpty()) {
                EmptyLayout(
                    modifier = Modifier.fillMaxWidth()
                        .height(150.dp),
                    text = "선택 가능한 태그가 없어요 🐒",
                    style = MaterialTheme.typography.bodyMedium,
                )
            } else {
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(DpDefault.ItemSpaceDp, Alignment.CenterHorizontally),
                    verticalArrangement = Arrangement.spacedBy(DpDefault.ItemSpaceDp)
                ) {
                    state.tagList.forEach {
                        TagCompose(
                            tag = it,
                            onClick = { state.onSelect(it.id, !it.isMemoFilter) },
                        )
                    }
                }
            }
        },
    )
}

@Composable
private fun TagCompose(
    modifier: Modifier = Modifier,
    tag: Tag,
    onClick: () -> Unit,
) {
    CompositionLocalProvider(
        LocalMinimumInteractiveComponentEnforcement provides false,
    ) {
        FilterChip(
            modifier = modifier,
            selected = tag.isMemoFilter,
            onClick = onClick,
            label = { Text(text = tag.title) },
            leadingIcon = { TagIcon() },
            shape = CircleShape,
        )
    }
}
