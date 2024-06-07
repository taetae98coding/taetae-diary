package com.taetae98.diary.library.compose.adaptive

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
public expect fun KListDetailPaneScaffold(
    modifier: Modifier = Modifier,
    navigator: ListDetailNavigator,
    listPane: @Composable () -> Unit,
    detailPane: @Composable () -> Unit,
)