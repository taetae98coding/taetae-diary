package com.taetae98.diary.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.taetae98.diary.core.compose.comming.ComingSoon
import com.taetae98.diary.core.navigation.calendar.CalendarNav
import com.taetae98.diary.feature.calendar.calendarEntry
import com.taetae98.diary.feature.memo.memoEntry
import com.taetae98.diary.feature.tag.tagEntry

@Composable
internal fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = CalendarNav.ROUTE,
        route = "app",
    ) {
        memoEntry(navController = navController)
        tagEntry(navController = navController)
        calendarEntry(navController = navController)

        composable("buddy") {
            Scaffold {
                ComingSoon(
                    modifier = Modifier.fillMaxSize()
                        .padding(it),
                )
            }
        }

        composable("more") {
            Scaffold {
                ComingSoon(
                    modifier = Modifier.fillMaxSize()
                        .padding(it),
                )
            }
        }
    }
}
