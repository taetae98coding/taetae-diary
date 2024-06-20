package com.taetae98.diary.core.api.holiday.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
internal data class BodyEntity(
    @SerialName("totalCount")
    val count: Int,
    @SerialName("items")
    val items: JsonElement,
)
