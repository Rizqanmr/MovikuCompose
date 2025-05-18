package com.rizqanmr.movikucompose.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rizqanmr.movikucompose.data.models.ItemMovieModel
import com.rizqanmr.movikucompose.data.repository.MovieRepository
import com.rizqanmr.movikucompose.utils.network.Result
import java.io.IOException

class MoviePagingSource(
    private val movieRepository: MovieRepository,
    private val genreId: Int
) : PagingSource<Int, ItemMovieModel>() {
    override fun getRefreshKey(state: PagingState<Int, ItemMovieModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ItemMovieModel> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = movieRepository.getMovies(nextPageNumber, genreId)
            if (response is Result.Success) {
                val movies = response.data.results
                return LoadResult.Page(
                    data = movies,
                    prevKey = if (nextPageNumber == 1) null else nextPageNumber - 1,
                    nextKey = if (movies.isEmpty()) null else nextPageNumber + 1
                )
            } else {
                return LoadResult.Error(IOException("Unexpected error"))
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}