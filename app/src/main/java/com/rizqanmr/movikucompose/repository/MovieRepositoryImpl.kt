package com.rizqanmr.movikucompose.repository

import com.rizqanmr.movikucompose.network.ApiService
import com.squareup.moshi.Moshi
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val moshi: Moshi
) : MovieRepository {
}