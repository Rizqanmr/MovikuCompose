package com.rizqanmr.movikucompose.data.repository

import com.rizqanmr.movikucompose.data.models.DetailMovieModel
import com.rizqanmr.movikucompose.data.models.GenresModel
import com.rizqanmr.movikucompose.data.models.MoviesModel
import com.rizqanmr.movikucompose.utils.network.Result

interface MovieRepository {

    suspend fun getGenres() : Result<GenresModel>

    suspend fun getMovies(page: Int, genreId: Int) : Result<MoviesModel>

    suspend fun getDetailMovie(movieId: Int?) : Result<DetailMovieModel>
}