package com.taetae98.diary.core.compose.text

import androidx.compose.material3.TextFieldColors
import androidx.compose.ui.graphics.Color

public fun TextFieldColors.removeIndicator(): TextFieldColors {
    return copy(
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent,
        errorIndicatorColor = Color.Transparent,
    )
}