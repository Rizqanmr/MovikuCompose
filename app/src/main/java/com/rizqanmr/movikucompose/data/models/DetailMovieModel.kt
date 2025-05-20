package com.rizqanmr.movikucompose.data.models

import com.google.gson.annotations.SerializedName

data class DetailMovieModel(
    @SerializedName("original_language") val originalLanguage: String = "",
    @SerializedName("imdb_id") val imdbId: String = "",
    @SerializedName("videos") val videos: Videos? = null,
    @SerializedName("video") val video: Boolean = false,
    @SerializedName("title") val title: String = "",
    @SerializedName("backdrop_path") val backdropPath: String = "",
    @SerializedName("revenue") val revenue: Int = 0,
    @SerializedName("genres") val genres: List<GenresItem>?,
    @SerializedName("popularity") val popularity: Double = 0.0,
    @SerializedName("id") val id: Int = 0,
    @SerializedName("vote_count") val voteCount: Int = 0,
    @SerializedName("budget") val budget: Int = 0,
    @SerializedName("overview") val overview: String = "",
    @SerializedName("original_title") val originalTitle: String = "",
    @SerializedName("runtime") val runtime: Int = 0,
    @SerializedName("poster_path") val posterPath: String = "",
    @SerializedName("release_date") val releaseDate: String = "",
    @SerializedName("vote_average") val voteAverage: Double = 0.0,
    @SerializedName("tagline") val tagline: String = "",
    @SerializedName("adult") val adult: Boolean = false,
    @SerializedName("homepage") val homepage: String = "",
    @SerializedName("status") val status: String = ""
)

data class VideosItem(
    @SerializedName("site") val site: String = "",
    @SerializedName("size") val size: Int = 0,
    @SerializedName("iso_3166_1") val iso31661: String = "",
    @SerializedName("name") val name: String = "",
    @SerializedName("official") val official: Boolean = false,
    @SerializedName("id") val id: String = "",
    @SerializedName("type") val type: String = "",
    @SerializedName("published_at") val publishedAt: String = "",
    @SerializedName("iso_639_1") val iso6391: String = "",
    @SerializedName("key") val key: String = ""
)

data class Videos(
    @SerializedName("results") val results: List<VideosItem>?
)
