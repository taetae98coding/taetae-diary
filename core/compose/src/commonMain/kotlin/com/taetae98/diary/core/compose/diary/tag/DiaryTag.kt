package com.taetae98.diary.core.compose.diary.tag

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.FilterChip
import androidx.compose.material3.LocalMinimumInteractiveComponentEnforcement
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.taetae98.diary.core.compose.dimension.DiaryDp
import com.taetae98.diary.core.compose.dimension.DpDefault
import com.taetae98.diary.core.compose.icon.TagIcon
import com.taetae98.diary.core.model.tag.SelectTag

@Composable
public fun DiaryTag(
    modifier: Modifier = Modifier,
    state: DiaryTagState,
) {
    Card(
        modifier = modifier,
    ) {
        Spacer(modifier = Modifier.height(DiaryDp.diaryVerticalPadding))
        Text(
            modifier = Modifier.padding(horizontal = DiaryDp.diaryHorizontalPadding),
            text = "태그",
            style = MaterialTheme.typography.titleLarge,
        )
        Spacer(modifier = Modifier.height(6.dp))
        LazyRow(
            contentPadding = PaddingValues(horizontal = DiaryDp.diaryHorizontalPadding),
            horizontalArrangement = Arrangement.spacedBy(DpDefault.ItemSpaceDp),
        ) {
            items(
                items = state.tagList,
                key = { it.id },
                contentType = { "DiaryTag" },
            ) {
                TagCompose(
                    uiState = it,
                    onClick = { state.onUpdateMemoTag(it.id, !it.isSelected) },
                )
            }
        }
        Spacer(modifier = Modifier.height(DiaryDp.diaryVerticalPadding))
    }
}

@Composable
private fun TagCompose(
    modifier: Modifier = Modifier,
    uiState: SelectTag,
    onClick: () -> Unit,
) {
    CompositionLocalProvider(
        LocalMinimumInteractiveComponentEnforcement provides false,
    ) {
        FilterChip(
            modifier = modifier,
            selected = uiState.isSelected,
            onClick = onClick,
            label = { Text(text = uiState.title) },
            leadingIcon = { TagIcon() },
            shape = CircleShape,
        )
    }
}
