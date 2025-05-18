package com.rizqanmr.movikucompose.data.repository

import com.rizqanmr.movikucompose.data.ApiService
import com.rizqanmr.movikucompose.data.Constant
import com.rizqanmr.movikucompose.data.models.ErrorModel
import com.rizqanmr.movikucompose.data.models.GenresModel
import com.rizqanmr.movikucompose.utils.network.Result
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val moshi: Moshi
) : MovieRepository {
    override suspend fun getGenres(): Result<GenresModel> {
        try {
            val response = apiService.getGenres()
            return if (response.isSuccessful) {
                if (response.body() != null) {
                    Result.Success(response.body()!!)
                } else {
                    Result.Error(Constant.NO_DATA)
                }
            } else {
                val jsonAdapter: JsonAdapter<ErrorModel> = moshi.adapter(ErrorModel::class.java)
                withContext(Dispatchers.IO) {
                    val errorResponse = jsonAdapter.fromJson(response.errorBody()?.string() ?: "")
                    Result.Error(errorResponse?.statusMessage ?: Constant.SOMETHING_WRONG)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            var errorMessage = e.localizedMessage
            if (e.localizedMessage!!.contains("Unable to resolve host")) {
                errorMessage = Constant.NO_CONNECTION
            }
            return Result.Error(errorMessage ?: Constant.SOMETHING_WRONG)
        }
    }
}