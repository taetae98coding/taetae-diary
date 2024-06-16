package com.taetae98.diary.app

import androidx.compose.ui.graphics.vector.ImageVector
import com.taetae98.diary.core.compose.icon.buddyIcon
import com.taetae98.diary.core.compose.icon.calendarIcon
import com.taetae98.diary.core.compose.icon.memoIcon
import com.taetae98.diary.core.compose.icon.moreIcon
import com.taetae98.diary.core.compose.icon.tagIcon
import com.taetae98.diary.core.navigation.memo.MemoNav
import com.taetae98.diary.core.navigation.tag.TagNav

internal enum class AppNavigation(
    val icon: ImageVector,
    val label: String,
    val route: String,
) {
    MEMO(memoIcon, "메모", MemoNav.ROUTE),
    TAG(tagIcon, "태그", TagNav.ROUTE),
    CALENDAR(calendarIcon, "캘린더", "calendar"),
    BUDDY(buddyIcon, "버디", "buddy"),
    MORE(moreIcon, "더보기", "more"),
}