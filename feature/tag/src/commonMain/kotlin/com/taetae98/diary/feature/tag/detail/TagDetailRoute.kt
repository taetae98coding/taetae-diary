package com.taetae98.diary.feature.tag.detail

import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.LifecycleResumeEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.taetae98.diary.core.compose.diary.info.DiaryInfoState
import com.taetae98.diary.core.compose.text.TextFieldState

@Composable
@NonRestartableComposable
internal fun TagDetailRoute(
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit,
    navigateToMemo: () -> Unit,
    tagDetailViewModel: TagDetailViewModel,
) {
    val windowSize = calculateWindowSizeClass()
    val isExpand = windowSize.widthSizeClass == WindowWidthSizeClass.Expanded

    val title = tagDetailViewModel.title.collectAsStateWithLifecycle()
    val description = tagDetailViewModel.description.collectAsStateWithLifecycle()
    val pagerState = rememberPagerState(1) { 2 }

    TagDetailScreen(
        modifier = modifier,
        state = remember(isExpand) {
            TagDetailState.Detail(
                isExpand = isExpand,
                onNavigateUp = navigateUp,
                onMemo = navigateToMemo,
            )
        },
        infoState = remember {
            DiaryInfoState(
                title = TextFieldState(title, tagDetailViewModel::setTitle),
                description = TextFieldState(description, tagDetailViewModel::setDescription),
                pagerState = pagerState,
            )
        },
        message = { null },
    )

    LifecycleResumeEffect(Unit) {
        onPauseOrDispose { tagDetailViewModel.updateIfChanged() }
    }
}
