package com.taetae98.diary.core.compose.icon

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
public fun DeleteIcon(
    modifier: Modifier = Modifier,
    contentDescription: String? = "삭제",
) {
    Icon(
        modifier = modifier,
        imageVector = Icons.Rounded.Delete,
        contentDescription = contentDescription,
    )
}