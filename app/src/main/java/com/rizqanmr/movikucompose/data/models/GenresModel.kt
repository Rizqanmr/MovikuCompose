package com.rizqanmr.movikucompose.data.models

import com.google.gson.annotations.SerializedName


data class GenresModel(
    @SerializedName("genres") val genres: List<GenresItem>?
)

data class GenresItem(
    @SerializedName("name") val name: String? = "",
    @SerializedName("id") val id: Int? = 0
)