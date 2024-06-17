package com.taetae98.diary.core.compose.dimension

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

public data object DiaryDp {
    public val diaryHorizontalPadding: Dp = 16.dp
    public val diaryVerticalPadding: Dp = 8.dp

    public val diaryPadding: PaddingValues = PaddingValues(horizontal = diaryHorizontalPadding, vertical = diaryVerticalPadding)
}