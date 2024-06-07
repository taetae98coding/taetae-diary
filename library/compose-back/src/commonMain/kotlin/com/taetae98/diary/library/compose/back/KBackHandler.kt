package com.taetae98.diary.library.compose.back

import androidx.compose.runtime.Composable

@Composable
public expect fun KBackHandler(
    isEnabled: Boolean = true,
    onBack: () -> Unit
)