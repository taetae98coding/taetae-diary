package com.taetae98.diary.app

import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.taetae98.diary.core.compose.theme.DiaryTheme
import com.taetae98.diary.library.compose.navigation.suite.KNavigationSuiteScaffold

@Composable
public actual fun App(
    modifier: Modifier,
) {
    DiaryTheme {
        val navController = rememberNavController()
        val backStack by navController.currentBackStackEntryAsState()

        KNavigationSuiteScaffold(
            modifier = modifier,
            navigationSuiteItems = {
                val selected = AppNavigation.entries.find { navigation ->
                    backStack?.destination?.hierarchy?.any { it.route == navigation.route } == true
                }

                AppNavigation.entries.forEach { item ->
                    item(
                        selected = item == selected,
                        onClick = { navController.navigate(item) },
                        icon = { Icon(imageVector = item.icon, item.label) },
                        label = { Text(text = item.label) },
                        alwaysShowLabel = false,
                    )
                }
            },
            content = {
                AppNavHost(
                    navController = navController,
                )
            },
        )
    }
}