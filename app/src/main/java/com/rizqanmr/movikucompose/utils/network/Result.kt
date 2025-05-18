package com.rizqanmr.movikucompose.utils.network

sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val errorMessage: String, val showRetry: Boolean = true) : Result<Nothing>()
}