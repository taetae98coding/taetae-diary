package com.taetae98.diary.library.compose.adaptive

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
public actual fun KListDetailPaneScaffold(
    modifier: Modifier,
    navigator: ListDetailNavigator,
    listPane: @Composable () -> Unit,
    detailPane: @Composable () -> Unit,
) {
    if (calculateWindowSizeClass().widthSizeClass != WindowWidthSizeClass.Expanded) {
        Box(
            modifier = modifier,
            contentAlignment = Alignment.Center,
        ) {
            when (navigator.state) {
                Navigation.LIST -> {
                    key(Navigation.LIST) {
                        listPane()
                    }
                }

                Navigation.DETAIL -> {
                    key(Navigation.DETAIL) {
                        detailPane()
                    }
                }
            }
        }
    } else {
        Row(
            modifier = modifier,
        ) {
            Box(
                modifier = Modifier.width(360.dp),
            ) {
                key(Navigation.LIST) {
                    listPane()
                }
            }

            Spacer(modifier = Modifier.width(24.dp))

            key(Navigation.DETAIL) {
                detailPane()
            }
        }
    }
}
