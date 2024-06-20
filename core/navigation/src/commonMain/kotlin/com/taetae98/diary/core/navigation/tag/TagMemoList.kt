package com.taetae98.diary.core.navigation.tag

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

public data object TagMemoList {
    public const val TAG_ID: String = "tagId"
    public const val ROUTE: String = "tag/{$TAG_ID}/memo/list"

    public val arguments: List<NamedNavArgument> = listOf(
        navArgument(TAG_ID) {
            type = NavType.StringType
            nullable = false
        },
    )

    public fun getRoute(tagId: String): String {
        return "tag/$tagId/memo/list"
    }
}
