package com.taetae98.diary.core.compose.diary.info

import androidx.compose.foundation.pager.PagerState
import com.taetae98.diary.core.compose.text.TextFieldState

public data class DiaryInfoState(
    val title: TextFieldState,
    val description: TextFieldState,
    val pagerState: PagerState
)
