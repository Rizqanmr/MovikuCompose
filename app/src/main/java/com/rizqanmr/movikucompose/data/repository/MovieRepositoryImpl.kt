package com.rizqanmr.movikucompose.data.repository

import com.rizqanmr.movikucompose.data.ApiService
import com.squareup.moshi.Moshi
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val moshi: Moshi
) : MovieRepository {
}