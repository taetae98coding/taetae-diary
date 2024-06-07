package com.taetae98.diary.core.compose.icon

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Preview
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.ui.Modifier

@Composable
@NonRestartableComposable
public fun PreviewIcon(
    modifier: Modifier = Modifier,
    contentDescription: String? = "미리보기",
) {
    Icon(
        modifier = modifier,
        imageVector = Icons.Rounded.Preview,
        contentDescription = contentDescription,
    )
}
