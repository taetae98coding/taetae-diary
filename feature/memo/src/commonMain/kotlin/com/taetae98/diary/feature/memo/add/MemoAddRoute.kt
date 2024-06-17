package com.taetae98.diary.feature.memo.add

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
import com.taetae98.diary.core.compose.diary.tag.DiaryTagState
import com.taetae98.diary.core.compose.text.TextFieldState
import com.taetae98.diary.feature.memo.detail.MemoDetailScreen
import com.taetae98.diary.feature.memo.detail.MemoDetailState

@Composable
@NonRestartableComposable
internal fun MemoAddRoute(
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit,
    memoAddViewModel: MemoAddViewModel,
) {
    val windowSize = calculateWindowSizeClass()
    val isExpand = windowSize.widthSizeClass == WindowWidthSizeClass.Expanded

    val title = memoAddViewModel.title.collectAsStateWithLifecycle()
    val description = memoAddViewModel.description.collectAsStateWithLifecycle()
    val pagerState = rememberPagerState(0) { 2 }

    val tagList = memoAddViewModel.tagList.collectAsStateWithLifecycle()

    val message by memoAddViewModel.message.collectAsStateWithLifecycle()

    MemoDetailScreen(
        modifier = modifier,
        state = remember(isExpand) {
            MemoDetailState.Add(
                isExpand = isExpand,
                onNavigateUp = navigateUp,
                onAdd = memoAddViewModel::add,
            )
        },
        infoState = remember {
            DiaryInfoState(
                title = TextFieldState(title, memoAddViewModel::setTitle),
                description = TextFieldState(description, memoAddViewModel::setDescription),
                pagerState = pagerState,
            )
        },
        tagState = remember {
            DiaryTagState(
                tagListState = tagList,
                onUpdateMemoTag = memoAddViewModel::updateMemoTag,
            )
        },
        message = { message },
    )
}
