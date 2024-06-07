package com.taetae98.diary.core.compose.icon

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Article
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

public val memoIcon: ImageVector = Icons.AutoMirrored.Rounded.Article

@Composable
@NonRestartableComposable
public fun MemoIcon(
    modifier: Modifier = Modifier,
    contentDescription: String? = "메모",
) {
    Icon(
        modifier = modifier,
        imageVector = memoIcon,
        contentDescription = contentDescription,
    )
}
