package com.taetae98.diary.core.api.holiday

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.ProxyBuilder
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.Url
import io.ktor.serialization.kotlinx.json.DefaultJson
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Named
import org.koin.core.annotation.Singleton

@Module
@ComponentScan
public class HolidayApiModule {
    @Named(HOLIDAY_JSON)
    @Singleton
    internal fun providesJson(): Json {
        return Json(DefaultJson) {
            ignoreUnknownKeys = true
        }
    }

    @Named(HOLIDAY_CLIENT)
    @Singleton
    internal fun providesHttpClient(
        @Named(HOLIDAY_ENGINE)
        engine: HttpClientEngineFactory<*>,
        @Named(HOLIDAY_JSON)
        json: Json,
        @Named(HOLIDAY_PROXY_URL)
        proxyUrl: String,
        @Named(HOLIDAY_API_URL)
        apiUrl: String,
        @Named(HOLIDAY_API_KEY)
        apiKey: String,
    ): HttpClient {
        return HttpClient(engine) {
            expectSuccess = true

            engine {
                if (proxyUrl.isNotBlank()) {
                    proxy = ProxyBuilder.http(Url(proxyUrl))
                }
            }

            defaultRequest {
                url(apiUrl)
                url.parameters.append("serviceKey", apiKey)
                url.parameters.append("_type", "json")
            }

            install(ContentNegotiation) {
                json(json)
            }

            install(HttpRequestRetry) {
                maxRetries = Int.MAX_VALUE
                retryOnExceptionIf { _, throwable ->
                    throwable.printStackTrace()
                    !throwable.isNetworkError()
                }
                exponentialDelay()
            }
        }
    }

    @Named(HOLIDAY_ENGINE)
    @Singleton
    internal fun provideEngine(): HttpClientEngineFactory<*> {
        return getHttpClientEngine()
    }

    public companion object {
        public const val HOLIDAY_ENGINE: String = "holidayEngin"
        public const val HOLIDAY_JSON: String = "holidayJson"
        public const val HOLIDAY_CLIENT: String = "holidayClient"
        public const val HOLIDAY_PROXY_URL: String = "holidayProxyUrl"
        public const val HOLIDAY_API_URL: String = "holidayApiUrl"
        public const val HOLIDAY_API_KEY: String = "holidayApiKey"
    }
}
