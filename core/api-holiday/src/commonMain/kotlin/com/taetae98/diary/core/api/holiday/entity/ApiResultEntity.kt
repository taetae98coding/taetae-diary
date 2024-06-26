package com.taetae98.diary.core.api.holiday.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ApiResultEntity(
    @SerialName("response")
    val response: ResponseEntity,
)
