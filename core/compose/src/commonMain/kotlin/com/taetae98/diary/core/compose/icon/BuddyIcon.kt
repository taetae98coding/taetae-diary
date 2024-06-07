package com.taetae98.diary.core.compose.icon

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.People
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

public val buddyIcon: ImageVector = Icons.Rounded.People

@Composable
@NonRestartableComposable
public fun BuddyIcon(
    modifier: Modifier = Modifier,
    contentDescription: String? = "버디",
) {
    Icon(
        modifier = modifier,
        imageVector = buddyIcon,
        contentDescription = contentDescription,
    )
}
