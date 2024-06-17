package com.taetae98.diary.feature.tag.list.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.ui.Modifier
import com.taetae98.diary.feature.tag.add.TagAddViewModel
import com.taetae98.diary.feature.tag.detail.TagDetailPane
import com.taetae98.diary.feature.tag.detail.TagDetailViewModel
import com.taetae98.diary.feature.tag.list.TagListRoute
import com.taetae98.diary.library.compose.adaptive.KListDetailPaneScaffold
import com.taetae98.diary.library.compose.adaptive.rememberListDetailNavigator
import org.koin.compose.viewmodel.koinNavViewModel

@Composable
@NonRestartableComposable
internal fun TagListDetailRoute(
    modifier: Modifier = Modifier,
    navigateToTagMemoList: (String) -> Unit,
) {
    val navigator = rememberListDetailNavigator()
    val tagListDetailViewModel = koinNavViewModel<TagListDetailViewModel>()
    val tagAddViewModel = koinNavViewModel<TagAddViewModel>()
    val tagDetailViewModel = koinNavViewModel<TagDetailViewModel>()

    KListDetailPaneScaffold(
        modifier = modifier,
        navigator = navigator,
        listPane = {
            TagListRoute(
                navigateToAdd = {
                    tagAddViewModel.clear()
                    tagListDetailViewModel.setIsAdd(true)
                    navigator.navigateToDetail()
                },
                navigateToDetail = {
                    tagDetailViewModel.setTagId(it)
                    tagListDetailViewModel.setIsAdd(false)
                    navigator.navigateToDetail()
                },
                tagListDetailViewModel = koinNavViewModel(),
                tagListViewModel = koinNavViewModel(),
                tagActionViewModel = koinNavViewModel(),
            )
        },
        detailPane = {
            TagDetailPane(
                navigateUp = {
                    tagListDetailViewModel.setIsAdd(true)
                    navigator.navigateUp()
                },
                navigateToMemo = { navigateToTagMemoList(tagDetailViewModel.id.value) },
                tagListDetailViewModel = koinNavViewModel(),
                tagAddViewModel = koinNavViewModel(),
                tagDetailViewModel = koinNavViewModel(),
            )
        },
    )
}
