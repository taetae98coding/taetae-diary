package com.taetae98.diary.core.api.holiday

import io.ktor.client.engine.darwin.DarwinHttpRequestException

internal actual fun Throwable.isNetworkError(): Boolean {
    return this is DarwinHttpRequestException
}
