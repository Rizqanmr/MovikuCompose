package com.rizqanmr.movikucompose.data.models

import android.annotation.SuppressLint
import com.rizqanmr.movikucompose.data.Constant
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.SimpleDateFormat

@JsonClass(generateAdapter = true)
data class MoviesModel(
    val page: Int = 0,
    @Json(name = "total_pages") val totalPages: Int = 0,
    val results: List<ItemMovieModel> = emptyList(),
    @Json(name = "total_results") val totalResults: Int = 0
)

@JsonClass(generateAdapter = true)
data class ItemMovieModel(
    val overview: String? = "",
    @Json(name = "original_language") val originalLanguage: String? = "",
    @Json(name = "original_title") val originalTitle: String? = "",
    val video: Boolean? = false,
    val title: String? = "",
    @Json(name = "poster_path") val posterPath: String? = "",
    @Json(name = "backdrop_path") val backdropPath: String? = "",
    @Json(name = "release_date") val releaseDate: String? = "",
    val popularity: Double? = 0.0,
    @Json(name = "vote_average") val voteAverage: Double? = 0.0,
    val id: Int = 0,
    val adult: Boolean? = false,
    @Json(name = "vote_count") val voteCount: Int? = 0
) {
    @SuppressLint("SimpleDateFormat")
    fun getFormattedDate(): String? {
        return try {
            val date = SimpleDateFormat("yyyy-MM-dd").parse(releaseDate!!)
            val dateFormat = SimpleDateFormat("dd MMM yyyy")
            date?.let { dateFormat.format(it) }
        } catch (e: Exception) {
            null
        }
    }

    fun getRating(): String {
        return BigDecimal(voteAverage!!).setScale(1, RoundingMode.HALF_EVEN).toString()
    }

    fun getRatingStar(): Float {
        val rating = voteAverage?.times(0.5)
        return BigDecimal(rating!!).setScale(1, RoundingMode.HALF_EVEN).toFloat()
    }

    fun getUrlPoster(): String {
        return Constant.URL_POSTER + posterPath
    }

    fun getUrlBackdrop(): String {
        return Constant.URL_BACKDROP + backdropPath
    }
}