package com.rizqanmr.movikucompose.data.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GenresModel(
    val genres: List<GenresItem>?
)

@JsonClass(generateAdapter = true)
data class GenresItem(
    val name: String? = "",
    val id: Int? = 0
)