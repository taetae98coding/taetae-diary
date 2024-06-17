package com.taetae98.diary.core.compose.text

import androidx.compose.material3.IconButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.testTag
import com.taetae98.diary.core.compose.icon.ClearIcon

@Composable
public fun ClearTextField(
    modifier: Modifier = Modifier,
    state: TextFieldState,
    label: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
) {
    val focusRequester = remember { FocusRequester() }

    TextField(
        modifier = modifier.focusRequester(focusRequester)
            .testTag("TextField"),
        value = state.value,
        onValueChange = state.onValueChange,
        label = label,
        trailingIcon = {
            if (state.value.isNotEmpty()) {
                IconButton(
                    onClick = {
                        state.onValueChange("")
                        focusRequester.requestFocus()
                    },
                ) {
                    ClearIcon()
                }
            }
        },
        isError = isError,
        singleLine = singleLine,
        maxLines = maxLines,
        colors = TextFieldDefaults.colors().removeIndicator(),
    )
}
