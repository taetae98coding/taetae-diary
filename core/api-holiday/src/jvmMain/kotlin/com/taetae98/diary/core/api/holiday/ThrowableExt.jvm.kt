package com.taetae98.diary.core.api.holiday

import java.net.UnknownHostException

internal actual fun Throwable.isNetworkError(): Boolean {
    return this is UnknownHostException
}