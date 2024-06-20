package com.taetae98.diary.core.compose.icon

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
public fun ChevronIcon(
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
) {
    Icon(
        modifier = modifier,
        imageVector = Icons.Rounded.ChevronRight,
        contentDescription = contentDescription,
    )
}
