package com.taetae98.diary.core.api.holiday

import com.taetae98.diary.core.test.fileAsText
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.HttpHeaders
import io.ktor.http.headersOf

val holidayMockEngine = MockEngine {
    val solYear = it.url.parameters["solYear"]
    val solMonth = it.url.parameters["solMonth"]

    respond(
        content = fileAsText("holiday_open_api_${solYear}_${solMonth}.json"),
        headers = headersOf(HttpHeaders.ContentType, "application/json"),
    )
}
