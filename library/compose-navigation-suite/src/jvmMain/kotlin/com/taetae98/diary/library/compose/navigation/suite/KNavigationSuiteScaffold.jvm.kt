package com.taetae98.diary.library.compose.navigation.suite

import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScope
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
public actual fun KNavigationSuiteScaffold(
    modifier: Modifier,
    navigationSuiteItems: NavigationSuiteScope.() -> Unit,
    content: @Composable () -> Unit,
) {
    val windowSize = calculateWindowSizeClass()
    val layoutType = remember(windowSize) {
        if (windowSize.widthSizeClass == WindowWidthSizeClass.Compact) {
            NavigationSuiteType.NavigationBar
        } else {
            NavigationSuiteType.NavigationRail
        }
    }

    NavigationSuiteScaffold(
        modifier = modifier,
        navigationSuiteItems = navigationSuiteItems,
        layoutType = layoutType,
        content = content,
    )
}