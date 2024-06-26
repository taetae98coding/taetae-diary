package com.taetae98.diary.feature.calendar

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.taetae98.diary.core.navigation.calendar.CalendarNav
import org.koin.compose.viewmodel.koinNavViewModel

public fun NavGraphBuilder.calendarEntry(navController: NavController) {
    composable(CalendarNav.ROUTE) {
        CalendarRoute(
            calendarHolidayViewModel = koinNavViewModel(),
        )
    }
}
