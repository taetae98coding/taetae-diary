package com.taetae98.diary.feature.memo.list.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.ui.Modifier
import com.taetae98.diary.feature.memo.add.MemoAddViewModel
import com.taetae98.diary.feature.memo.detail.MemoDetailTagViewModel
import com.taetae98.diary.feature.memo.detail.MemoDetailViewModel
import com.taetae98.diary.feature.memo.list.MemoListRoute
import com.taetae98.diary.library.compose.adaptive.KListDetailPaneScaffold
import com.taetae98.diary.library.compose.adaptive.rememberListDetailNavigator
import org.koin.compose.viewmodel.koinNavViewModel

@Composable
@NonRestartableComposable
internal fun MemoListDetailRoute(
    modifier: Modifier = Modifier,
    navigateToListFilter: () -> Unit,
) {
    val navigator = rememberListDetailNavigator()
    val memoListDetailViewModel = koinNavViewModel<MemoListDetailViewModel>()
    val memoAddViewModel = koinNavViewModel<MemoAddViewModel>()
    val memoDetailViewModel = koinNavViewModel<MemoDetailViewModel>()
    val memoDetailTagViewModel = koinNavViewModel<MemoDetailTagViewModel>()

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
                    memoDetailTagViewModel.setMemoId(it)
                    memoListDetailViewModel.setIsAdd(false)
                    navigator.navigateToDetail()
                },
                navigateToListFilter = navigateToListFilter,
                memoListDetailViewModel = koinNavViewModel(),
                memoListViewModel = koinNavViewModel(),
                memoActionViewModel = koinNavViewModel(),
                memoFilterViewModel = koinNavViewModel(),
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
                memoDetailTagViewModel = koinNavViewModel(),
            )
        },
    )
}
