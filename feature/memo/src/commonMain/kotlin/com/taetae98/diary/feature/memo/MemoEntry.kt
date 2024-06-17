package com.taetae98.diary.feature.memo

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.navigation
import com.taetae98.diary.core.navigation.memo.MemoDetailNav
import com.taetae98.diary.core.navigation.memo.MemoListFilterNav
import com.taetae98.diary.core.navigation.memo.MemoListNav
import com.taetae98.diary.core.navigation.memo.MemoNav
import com.taetae98.diary.feature.memo.detail.MemoDetailRoute
import com.taetae98.diary.feature.memo.list.detail.MemoListDetailRoute
import com.taetae98.diary.feature.memo.list.filter.MemoListFilterRoute
import org.koin.compose.viewmodel.koinNavViewModel

public fun NavGraphBuilder.memoEntry(
    navController: NavController,
) {
    navigation(
        startDestination = MemoListNav.ROUTE,
        route = MemoNav.ROUTE,
    ) {
        composable(MemoListNav.ROUTE) {
            MemoListDetailRoute(
                navigateToListFilter = { navController.navigate(MemoListFilterNav.ROUTE) },
            )
        }

        dialog(MemoListFilterNav.ROUTE) {
            MemoListFilterRoute(
                navigateUp = navController::navigateUp,
                memoListFilterTagViewModel = koinNavViewModel(),
            )
        }

        composable(
            route = MemoDetailNav.ROUTE,
            arguments = MemoDetailNav.arguments,
        ) {
            MemoDetailRoute(
                navigateUp = navController::navigateUp,
                memoDetailViewModel = koinNavViewModel(),
                memoDetailTagViewModel = koinNavViewModel(),
            )
        }
    }
}
