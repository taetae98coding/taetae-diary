package com.taetae98.diary.library.compose.navigation.suite

import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
public expect fun KNavigationSuiteScaffold(
    modifier: Modifier = Modifier,
    navigationSuiteItems: NavigationSuiteScope.() -> Unit,
    content: @Composable () -> Unit
)