package com.taetae98.diary.library.compose.adaptive

import androidx.compose.material3.adaptive.layout.ThreePaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.ThreePaneScaffoldNavigator
import androidx.compose.runtime.Stable

@Stable
public actual class ListDetailNavigator(
    private val navigator: ThreePaneScaffoldNavigator<Nothing>,
) {
    internal val scaffoldDirective get() = navigator.scaffoldDirective
    internal val scaffoldValue get() = navigator.scaffoldValue

    public actual fun navigateToDetail() {
        navigator.navigateTo(ThreePaneScaffoldRole.Primary)
    }

    public actual fun navigateUp() {
        navigator.navigateBack()
    }

    internal fun canNavigateBack(): Boolean {
        return navigator.canNavigateBack()
    }
}
