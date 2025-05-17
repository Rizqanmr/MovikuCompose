package com.rizqanmr.movikucompose.di

import com.rizqanmr.movikucompose.network.ApiService
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideApiService(): ApiService = NetworkModule().retrofitProvider.getApiService()

    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder().build()
}