package com.taetae98.diary.core.api.holiday

import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.okhttp.OkHttp

internal actual fun getHttpClientEngine(): HttpClientEngineFactory<*> {
    return OkHttp
}
