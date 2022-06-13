package com.nihaskalam.automobilenewsapp.data.network

import com.nihaskalam.automobilenewsapp.domain.model.NetworkResult
import com.nihaskalam.automobilenewsapp.domain.model.NetworkResult.*
import retrofit2.HttpException
import retrofit2.Response

abstract class BaseNewsDataSource () {

    suspend fun <T : Any> handleApi(
        execute: suspend () -> Response<T>
    ): NetworkResult<T> {
        return try {
            val response = execute()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                ApiSuccess(body)
            } else {
                ApiError(code = response.code(), message = response.message())
            }
        } catch (e: HttpException) {
            ApiError(code = e.code(), message = e.message())
        } catch (e: Throwable) {
            ApiException(e)
        }
    }
}