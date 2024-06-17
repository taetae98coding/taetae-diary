package com.taetae98.diary.core.navigation.memo

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

public data object MemoDetailNav {
    public const val MEMO_ID: String = "memoId"
    public const val ROUTE: String = "memo/detail/{memoId}"

    public val arguments: List<NamedNavArgument> = listOf(
        navArgument(MEMO_ID) {
            type = NavType.StringType
            nullable = false
        },
    )

    public fun getRoute(memoId: String): String {
        return "memo/detail/$memoId"
    }
}
