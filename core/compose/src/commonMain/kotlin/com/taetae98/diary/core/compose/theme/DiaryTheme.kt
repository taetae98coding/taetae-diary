package com.taetae98.diary.core.compose.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

@Composable
public fun DiaryTheme(
    content: @Composable () -> Unit,
) {
    val colorScheme = if (!isSystemInDarkTheme()) {
        lightColorScheme()
    } else {
        darkColorScheme()
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content,
    )
}