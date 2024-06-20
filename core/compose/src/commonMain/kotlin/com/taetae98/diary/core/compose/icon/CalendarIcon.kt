package com.taetae98.diary.core.compose.icon

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

public val calendarIcon: ImageVector = Icons.Rounded.DateRange

@Composable
@NonRestartableComposable
public fun CalendarIcon(
    modifier: Modifier = Modifier,
    contentDescription: String? = "캘린더",
) {
    Icon(
        modifier = modifier,
        imageVector = calendarIcon,
        contentDescription = contentDescription,
    )
}
