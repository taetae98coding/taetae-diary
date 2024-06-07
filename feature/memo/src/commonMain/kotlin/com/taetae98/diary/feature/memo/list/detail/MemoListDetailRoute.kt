package com.taetae98.diary.feature.memo.list.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.ui.Modifier
import com.taetae98.diary.feature.memo.add.MemoAddViewModel
import com.taetae98.diary.feature.memo.detail.MemoDetailViewModel
import com.taetae98.diary.feature.memo.list.MemoListRoute
import com.taetae98.diary.library.compose.adaptive.KListDetailPaneScaffold
import com.taetae98.diary.library.compose.adaptive.rememberListDetailNavigator
import org.koin.compose.viewmodel.koinNavViewModel

@Composable
@NonRestartableComposable
internal fun MemoListDetailRoute(
    modifier: Modifier = Modifier,
) {
    val navigator = rememberListDetailNavigator()
    val memoListDetailViewModel = koinNavViewModel<MemoListDetailViewModel>()
    val memoAddViewModel = koinNavViewModel<MemoAddViewModel>()
    val memoDetailViewModel = koinNavViewModel<MemoDetailViewModel>()

    KListDetailPaneScaffold(
        modifier = modifier,
        navigator = navigator,
        listPane = {
            MemoListRoute(
                navigateToAdd = {
                    memoAddViewModel.clear()
                    memoListDetailViewModel.setIsAdd(true)
                    navigator.navigateToDetail()
                },
                navigateToDetail = {
                    memoDetailViewModel.setMemoId(it)
                    memoListDetailViewModel.setIsAdd(false)
                    navigator.navigateToDetail()
                },
                memoListDetailViewModel = koinNavViewModel(),
                memoListViewModel = koinNavViewModel(),
                memoActionViewModel = koinNavViewModel(),
            )
        },
        detailPane = {
            MemoDetailPane(
                navigateUp = {
                    memoListDetailViewModel.setIsAdd(true)
                    navigator.navigateUp()
                },
                memoListDetailViewModel = koinNavViewModel(),
                memoAddViewModel = koinNavViewModel(),
                memoDetailViewModel = koinNavViewModel(),
            )
        },
    )
}
