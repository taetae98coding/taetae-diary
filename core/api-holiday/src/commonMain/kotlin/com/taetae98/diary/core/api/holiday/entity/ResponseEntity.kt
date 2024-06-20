package com.taetae98.diary.core.api.holiday.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ResponseEntity(
    @SerialName("body")
    val body: BodyEntity,
)
