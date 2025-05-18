package com.rizqanmr.movikucompose.di

import com.rizqanmr.movikucompose.data.repository.MovieRepository
import com.rizqanmr.movikucompose.data.repository.MovieRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindMovieRepository(movieRepository: MovieRepositoryImpl): MovieRepository
}