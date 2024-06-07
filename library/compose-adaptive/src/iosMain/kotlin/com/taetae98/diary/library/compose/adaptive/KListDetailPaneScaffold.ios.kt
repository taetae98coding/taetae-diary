package com.taetae98.diary.library.compose.adaptive

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
public actual fun KListDetailPaneScaffold(
    modifier: Modifier,
    navigator: ListDetailNavigator,
    listPane: @Composable () -> Unit,
    detailPane: @Composable () -> Unit,
) {
    NavHost(
        modifier = modifier,
        navController = navigator.navController,
        startDestination = "list"
    ) {
        composable("list") {
            listPane()
        }

        composable("detail") {
            detailPane()
        }
    }
}
