package com.taetae98.diary.app

import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
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
        val selectNavigation by remember {
            derivedStateOf {
                AppNavigation.entries.find { navigation ->
                    backStack?.destination?.hierarchy?.any { it.route == navigation.route } == true
                }
            }
        }

        KNavigationSuiteScaffold(
            modifier = modifier,
            navigationSuiteItems = {
                AppNavigation.entries.forEach {
                    item(
                        selected = it == selectNavigation,
                        onClick = { navController.navigate(it) },
                        icon = { Icon(imageVector = it.icon, it.label) },
                        label = { Text(text = it.label) },
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