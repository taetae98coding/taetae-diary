package com.taetae98.diary.core.compose.icon

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.ui.Modifier

@Composable
@NonRestartableComposable
public fun AddIcon(
    modifier: Modifier = Modifier,
    contentDescription: String? = "추가",
) {
    Icon(
        modifier = modifier,
        imageVector = Icons.Rounded.Add,
        contentDescription = contentDescription,
    )
}
