package com.rizqanmr.movikucompose.di

import com.example.movikucompose.BuildConfig
import com.rizqanmr.movikucompose.network.RetrofitProvider
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

class NetworkModule {
    val retrofitProvider by lazy {
        RetrofitProvider(getOkHttpClient())
    }

    private fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            if (BuildConfig.DEBUG) {
                addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            }
            addInterceptor(createAuthInterceptor())
            readTimeout(30, TimeUnit.SECONDS)
        }.build()
    }

    private fun createAuthInterceptor(): Interceptor {
        return Interceptor { chain ->
            val url = chain.request().url.newBuilder()
                .addQueryParameter("api_key", BuildConfig.API_KEY)
                .build()
            val request = chain.request().newBuilder().url(url).build()
            chain.proceed(request)
        }
    }
}