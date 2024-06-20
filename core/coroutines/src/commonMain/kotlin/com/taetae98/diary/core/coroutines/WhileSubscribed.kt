package com.taetae98.diary.core.coroutines

import kotlinx.coroutines.flow.SharingStarted

public val SharingStarted.Companion.DEFAULT_WHILE_SUBSCRIBED: SharingStarted
    get() = WhileSubscribed(5000L)