package com.taetae98.diary.feature.tag

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.taetae98.diary.core.navigation.tag.TagListNav
import com.taetae98.diary.core.navigation.tag.TagNav
import com.taetae98.diary.feature.tag.list.detail.TagListDetailRoute

public fun NavGraphBuilder.tagEntry(
    navController: NavController,
) {
    navigation(
        startDestination = TagListNav.ROUTE,
        route = TagNav.ROUTE,
    ) {
        composable(TagListNav.ROUTE) {
            TagListDetailRoute()
        }
    }
}
