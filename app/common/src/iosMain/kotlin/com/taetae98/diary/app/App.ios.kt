package com.taetae98.diary.app

import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
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

        Scaffold(
            bottomBar = {
                NavigationBar {
                    AppNavigation.entries.forEach {
                        NavigationBarItem(
                            selected = it == selectNavigation,
                            onClick = { navController.navigate(it) },
                            icon = { Icon(imageVector = it.icon, it.label) },
                            label = { Text(text = it.label) },
                            alwaysShowLabel = false,
                        )
                    }
                }
            },
        ) {
            AppNavHost(
                modifier = Modifier.padding(it)
                    .consumeWindowInsets(it),
                navController = navController,
            )
        }
    }
}
