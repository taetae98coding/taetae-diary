package com.taetae98.diary.feature.memo.detail

import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.LifecycleResumeEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.taetae98.diary.core.compose.diary.DiaryInfoState
import com.taetae98.diary.core.compose.text.TextFieldState

@Composable
@NonRestartableComposable
internal fun MemoDetailRoute(
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit,
    memoDetailViewModel: MemoDetailViewModel,
) {
    val windowSize = calculateWindowSizeClass()
    val isExpand = remember {
        derivedStateOf { windowSize.widthSizeClass == WindowWidthSizeClass.Expanded }
    }

    val title = memoDetailViewModel.title.collectAsStateWithLifecycle()
    val description = memoDetailViewModel.description.collectAsStateWithLifecycle()
    val pagerState = rememberPagerState(1) { 2 }

    MemoDetailScreen(
        modifier = modifier,
        state = remember {
            MemoDetailState(
                isExpand = isExpand,
                isAdd = false,
                onNavigateUp = navigateUp,
            )
        },
        infoState = remember {
            DiaryInfoState(
                title = TextFieldState(title, memoDetailViewModel::setTitle),
                description = TextFieldState(description, memoDetailViewModel::setDescription),
                pagerState = pagerState,
            )
        },
        message = { null },
    )

    LifecycleResumeEffect(Unit) {
        onPauseOrDispose { memoDetailViewModel.updateIfChanged() }
    }
}