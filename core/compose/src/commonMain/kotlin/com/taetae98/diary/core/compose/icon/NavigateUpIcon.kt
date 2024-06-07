package com.taetae98.diary.core.compose.icon

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.ui.Modifier

@Composable
@NonRestartableComposable
public fun NavigateUpIcon(
    modifier: Modifier = Modifier,
    contentDescription: String? = "뒤로가기",
) {
    Icon(
        modifier = modifier,
        imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
        contentDescription = contentDescription,
    )
}
