package com.taetae98.diary.core.compose.icon

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Tag
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

public val tagIcon: ImageVector = Icons.Rounded.Tag

@Composable
@NonRestartableComposable
public fun TagIcon(
    modifier: Modifier = Modifier,
    contentDescription: String? = "태그",
) {
    Icon(
        modifier = modifier,
        imageVector = tagIcon,
        contentDescription = contentDescription,
    )
}
