package com.taetae98.diary.library.compose.adaptive

import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable

@Composable
public actual fun rememberListDetailNavigator(): ListDetailNavigator {
    return rememberSaveable(saver = ListDetailNavigator.Saver) {
        ListDetailNavigator(Navigation.LIST)
    }
}
