package com.rizqanmr.movikucompose.data

import com.rizqanmr.movikucompose.data.models.GenresModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("genre/movie/list") suspend fun getGenres(): Response<GenresModel>
}