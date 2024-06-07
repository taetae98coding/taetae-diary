package com.taetae98.diary.core.compose.icon

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.ui.Modifier

@Composable
@NonRestartableComposable
public fun ClearIcon(
    modifier: Modifier = Modifier,
    contentDescription: String? = "지우기",
) {
    Icon(
        modifier = modifier,
        imageVector = Icons.Rounded.Clear,
        contentDescription = contentDescription,
    )
}
