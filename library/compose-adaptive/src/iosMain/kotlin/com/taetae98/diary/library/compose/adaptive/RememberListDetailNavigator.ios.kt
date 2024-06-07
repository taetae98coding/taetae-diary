package com.taetae98.diary.library.compose.adaptive

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController

@Composable
public actual fun rememberListDetailNavigator(): ListDetailNavigator {
    val navController = rememberNavController()

    return remember(navController) {
        ListDetailNavigator(navController)
    }
}