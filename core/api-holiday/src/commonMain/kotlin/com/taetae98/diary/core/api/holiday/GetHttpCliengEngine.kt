package com.taetae98.diary.core.api.holiday

import io.ktor.client.engine.HttpClientEngineFactory

internal expect fun getHttpClientEngine(): HttpClientEngineFactory<*>