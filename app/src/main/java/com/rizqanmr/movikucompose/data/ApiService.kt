package com.rizqanmr.movikucompose.data

import com.rizqanmr.movikucompose.data.models.GenresModel
import com.rizqanmr.movikucompose.data.models.MoviesModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("genre/movie/list") suspend fun getGenres(): Response<GenresModel>

    @GET("discover/movie")
    suspend fun getMovies(
        @Query("page") page: Int,
        @Query("with_genres") genreId: Int
    ) : Response<MoviesModel>
}