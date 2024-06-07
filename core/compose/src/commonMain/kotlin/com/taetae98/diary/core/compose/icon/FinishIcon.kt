package com.taetae98.diary.core.compose.icon

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Verified
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
public fun FinishIcon(
    modifier: Modifier = Modifier,
    contentDescription: String? = "완료",
) {
    Icon(
        modifier = modifier,
        imageVector = Icons.Rounded.Verified,
        contentDescription = contentDescription,
    )
}