package com.rizqanmr.movikucompose.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ErrorModel(
    @Json(name = "status_code") val statusCode: Int,
    @Json(name = "status_message") val statusMessage: String,
    val success: Boolean,
)
