package com.taetae98.diary.feature.tag

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.taetae98.diary.core.navigation.memo.MemoDetailNav
import com.taetae98.diary.core.navigation.tag.TagListNav
import com.taetae98.diary.core.navigation.tag.TagMemoList
import com.taetae98.diary.core.navigation.tag.TagNav
import com.taetae98.diary.feature.tag.list.detail.TagListDetailRoute
import com.taetae98.diary.feature.tag.memo.TagMemoListRoute
import org.koin.compose.viewmodel.koinNavViewModel

public fun NavGraphBuilder.tagEntry(
    navController: NavController,
) {
    navigation(
        startDestination = TagListNav.ROUTE,
        route = TagNav.ROUTE,
    ) {
        composable(TagListNav.ROUTE) {
            TagListDetailRoute(
                navigateToTagMemoList = { navController.navigate(TagMemoList.getRoute(it)) },
            )
        }

        composable(
            route = TagMemoList.ROUTE,
            arguments = TagMemoList.arguments,
        ) {
            TagMemoListRoute(
                navigateUp = { navController.navigateUp() },
                navigateToMemoDetail = { navController.navigate(MemoDetailNav.getRoute(it)) },
                tagMemoListViewModel = koinNavViewModel(),
                tagMemoListDetailViewModel = koinNavViewModel(),
            )
        }
    }
}
