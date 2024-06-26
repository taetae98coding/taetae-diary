package com.taetae98.diary.core.api.holiday

import com.taetae98.diary.core.api.holiday.entity.ApiResultEntity
import com.taetae98.diary.core.api.holiday.entity.BodyEntity
import com.taetae98.diary.core.api.holiday.entity.HolidayEntity
import com.taetae98.diary.core.api.holiday.entity.HolidayItemEntity
import com.taetae98.diary.core.api.holiday.entity.HolidayItemsEntity
import com.taetae98.diary.core.model.holiday.Holiday
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.datetime.Month
import kotlinx.datetime.number
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Named

@Factory
public class HolidayService(
    @Named(HolidayApiModule.HOLIDAY_CLIENT)
    private val client: HttpClient,
    @Named(HolidayApiModule.HOLIDAY_JSON)
    private val json: Json,
) {
    public suspend fun findHoliday(year: Int, month: Month): List<Holiday> {
        val solMonth = if (month.number >= 10) {
            month.number.toString()
        } else {
            "0${month.number}"
        }

        val response = client.get("getHoliDeInfo") {
            parameter("solYear", year)
            parameter("solMonth", solMonth)
        }

        return response.body<ApiResultEntity>()
            .response
            .body
            .getHolidayList()
            .map(HolidayEntity::toDomain)
    }

    private fun BodyEntity.getHolidayList(): List<HolidayEntity> {
        return when (count) {
            0 -> emptyList()
            1 -> listOf(json.decodeFromJsonElement<HolidayItemEntity>(items).item)
            else -> json.decodeFromJsonElement<HolidayItemsEntity>(items).items
        }
    }
}
