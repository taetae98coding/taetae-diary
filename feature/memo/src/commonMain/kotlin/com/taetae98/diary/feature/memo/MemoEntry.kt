package com.taetae98.diary.feature.memo

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.taetae98.diary.core.navigation.memo.MemoListNav
import com.taetae98.diary.core.navigation.memo.MemoNav
import com.taetae98.diary.feature.memo.list.detail.MemoListDetailRoute

public fun NavGraphBuilder.memoEntry(
    navController: NavController,
) {
    navigation(
        startDestination = MemoListNav.ROUTE,
        route = MemoNav.ROUTE,
    ) {
        composable(MemoListNav.ROUTE) {
            MemoListDetailRoute()
        }
    }
}
