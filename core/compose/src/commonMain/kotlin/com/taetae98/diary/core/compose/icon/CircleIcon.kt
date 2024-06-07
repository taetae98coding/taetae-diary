package com.taetae98.diary.core.compose.icon

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Circle
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
public fun CircleIcon(
    modifier: Modifier = Modifier,
    contentDescription: String? = null
) {
    Icon(
        modifier = modifier,
        imageVector = Icons.Rounded.Circle,
        contentDescription = contentDescription,
    )
}