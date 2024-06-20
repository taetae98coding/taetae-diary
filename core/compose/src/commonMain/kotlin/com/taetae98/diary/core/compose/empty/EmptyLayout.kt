package com.taetae98.diary.core.compose.empty

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle

@Composable
public fun EmptyLayout(
    modifier: Modifier = Modifier,
    text: String,
    style: TextStyle = MaterialTheme.typography.headlineSmall,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            style = style,
        )
    }
}
