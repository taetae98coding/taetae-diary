package com.taetae98.diary.library.compose.adaptive

import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
public actual fun rememberListDetailNavigator(): ListDetailNavigator {
    val navigator = rememberListDetailPaneScaffoldNavigator()

    return remember(navigator) {
        ListDetailNavigator(navigator)
    }
}
