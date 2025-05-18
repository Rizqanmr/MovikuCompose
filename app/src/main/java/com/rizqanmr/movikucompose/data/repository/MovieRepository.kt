package com.rizqanmr.movikucompose.data.repository

import com.rizqanmr.movikucompose.data.models.GenresModel
import com.rizqanmr.movikucompose.utils.network.Result

interface MovieRepository {

    suspend fun getGenres() : Result<GenresModel>
}