package com.taetae98.diary.app

import androidx.navigation.NavController

internal fun NavController.navigate(navigation: AppNavigation) {
    navigate(navigation.route) {
        popUpTo(AppNavigation.CALENDAR.route)
        launchSingleTop = true
    }
}