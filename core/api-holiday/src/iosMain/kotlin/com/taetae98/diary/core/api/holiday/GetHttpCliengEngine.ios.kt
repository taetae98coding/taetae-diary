package com.taetae98.diary.core.api.holiday

import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.darwin.Darwin

internal actual fun getHttpClientEngine(): HttpClientEngineFactory<*> {
    return Darwin
}