package com.taetae98.diary.library.compose.back

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable

@Composable
public actual fun KBackHandler(
    isEnabled: Boolean,
    onBack: () -> Unit,
) {
    BackHandler(
        enabled = isEnabled,
        onBack = onBack,
    )
}