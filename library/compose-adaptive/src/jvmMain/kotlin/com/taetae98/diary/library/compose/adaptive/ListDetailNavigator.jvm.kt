package com.taetae98.diary.library.compose.adaptive

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.runtime.setValue

@Stable
public actual class ListDetailNavigator internal constructor(
    initialState: Navigation,
) {
    internal var state by mutableStateOf(initialState)
        private set

    public actual fun navigateToDetail() {
        state = Navigation.DETAIL
    }

    public actual fun navigateUp() {
        state = Navigation.LIST
    }

    public companion object {
        internal val Saver = run {
            val key = "navigation"

            mapSaver(
                save = { mapOf(key to it.state) },
                restore = {
                    val navigation = it[key] as? Navigation ?: Navigation.LIST
                    ListDetailNavigator(navigation)
                },
            )
        }
    }
}
