package com.taetae98.diary.core.compose.text

import androidx.compose.runtime.State

public data class TextFieldState(
    private val stringState: State<String>,
    val onValueChange: (String) -> Unit,
) {
    public val value: String
        get() = stringState.value
}
