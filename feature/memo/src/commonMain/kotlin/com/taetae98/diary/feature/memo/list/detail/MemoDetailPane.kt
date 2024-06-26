package com.taetae98.diary.feature.memo.list.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.taetae98.diary.feature.memo.add.MemoAddRoute
import com.taetae98.diary.feature.memo.add.MemoAddViewModel
import com.taetae98.diary.feature.memo.detail.MemoDetailRoute
import com.taetae98.diary.feature.memo.detail.MemoDetailTagViewModel
import com.taetae98.diary.feature.memo.detail.MemoDetailViewModel

@Composable
@NonRestartableComposable
internal fun MemoDetailPane(
    navigateUp: () -> Unit,
    memoListDetailViewModel: MemoListDetailViewModel,
    memoAddViewModel: MemoAddViewModel,
    memoDetailViewModel: MemoDetailViewModel,
    memoDetailTagViewModel: MemoDetailTagViewModel,
) {
    val isAdd by memoListDetailViewModel.isAdd.collectAsStateWithLifecycle()

    if (isAdd) {
        MemoAddRoute(
            navigateUp = navigateUp,
            memoAddViewModel = memoAddViewModel,
        )
    } else {
        MemoDetailRoute(
            navigateUp = navigateUp,
            memoDetailViewModel = memoDetailViewModel,
            memoDetailTagViewModel = memoDetailTagViewModel,
        )
    }
}