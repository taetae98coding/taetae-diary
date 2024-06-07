package com.taetae98.diary.library.compose.adaptive

import androidx.compose.runtime.Stable

@Stable
public expect class ListDetailNavigator {
    public fun navigateToDetail()
    public fun navigateUp()
}
