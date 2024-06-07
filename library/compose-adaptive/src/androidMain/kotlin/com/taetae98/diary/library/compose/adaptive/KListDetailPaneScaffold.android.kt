package com.taetae98.diary.library.compose.adaptive

import androidx.activity.compose.BackHandler
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
public actual fun KListDetailPaneScaffold(
    modifier: Modifier,
    navigator: ListDetailNavigator,
    listPane: @Composable () -> Unit,
    detailPane: @Composable () -> Unit,
) {
    ListDetailPaneScaffold(
        directive = navigator.scaffoldDirective,
        value = navigator.scaffoldValue,
        listPane = {
            AnimatedPane {
                listPane()
            }
        },
        detailPane = {
            AnimatedPane {
                detailPane()
            }
        },
    )

    BackHandler(
        enabled = navigator.canNavigateBack(),
        onBack = navigator::navigateUp,
    )
}
