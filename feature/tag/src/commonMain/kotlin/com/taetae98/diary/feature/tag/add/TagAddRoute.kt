package com.taetae98.diary.feature.tag.add

import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.taetae98.diary.core.compose.diary.info.DiaryInfoState
import com.taetae98.diary.core.compose.text.TextFieldState
import com.taetae98.diary.feature.tag.detail.TagDetailScreen
import com.taetae98.diary.feature.tag.detail.TagDetailState

@Composable
@NonRestartableComposable
internal fun TagAddRoute(
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit,
    tagAddViewModel: TagAddViewModel,
) {
    val windowSize = calculateWindowSizeClass()
    val isExpand = windowSize.widthSizeClass == WindowWidthSizeClass.Expanded

    val title = tagAddViewModel.title.collectAsStateWithLifecycle()
    val description = tagAddViewModel.description.collectAsStateWithLifecycle()
    val pagerState = rememberPagerState(0) { 2 }

    val message by tagAddViewModel.message.collectAsStateWithLifecycle()

    TagDetailScreen(
        modifier = modifier,
        state = remember(isExpand) {
            TagDetailState.Add(
                isExpand = isExpand,
                onNavigateUp = navigateUp,
                onAdd = tagAddViewModel::add,
            )
        },
        infoState = remember {
            DiaryInfoState(
                title = TextFieldState(title, tagAddViewModel::setTitle),
                description = TextFieldState(description, tagAddViewModel::setDescription),
                pagerState = pagerState,
            )
        },
        message = { message },
    )
}
