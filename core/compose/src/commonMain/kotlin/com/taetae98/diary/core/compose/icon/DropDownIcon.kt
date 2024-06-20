package com.taetae98.diary.core.compose.icon

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
public fun DropDownIcon(
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
) {
    Icon(
        modifier = modifier,
        imageVector = Icons.Rounded.ArrowDropDown,
        contentDescription = contentDescription,
    )
}
