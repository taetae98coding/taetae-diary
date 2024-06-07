package com.taetae98.diary.core.compose.icon

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreHoriz
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

public val moreIcon: ImageVector = Icons.Rounded.MoreHoriz

@Composable
@NonRestartableComposable
public fun MoreIcon(
    modifier: Modifier = Modifier,
    contentDescription: String? = "더보기",
) {
    Icon(
        modifier = modifier,
        imageVector = moreIcon,
        contentDescription = contentDescription,
    )
}
