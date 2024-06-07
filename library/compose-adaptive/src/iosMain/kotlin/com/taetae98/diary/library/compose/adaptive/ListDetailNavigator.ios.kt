package com.taetae98.diary.library.compose.adaptive

import androidx.compose.runtime.Stable
import androidx.navigation.NavHostController

@Stable
public actual class ListDetailNavigator(
    internal val navController: NavHostController,
) {
    public actual fun navigateToDetail() {
        navController.navigate("detail")
    }

    public actual fun navigateUp() {
        navController.navigateUp()
    }
}
