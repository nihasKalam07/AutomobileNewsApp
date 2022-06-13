package com.nihaskalam.automobilenewsapp.domain.model

sealed class NetworkResult<T : Any> {
    class ApiLoading<T: Any>() : NetworkResult<T>()
    data class ApiSuccess<T: Any>(val newsList: T) : NetworkResult<T>()
    data class ApiError<T: Any>(val code: Int, val message: String?) : NetworkResult<T>()
    data class ApiException<T: Any>(val e: Throwable) : NetworkResult<T>()
}
