package com.nihaskalam.automobilenewsapp.data.network

import com.nihaskalam.automobilenewsapp.data.ApiService
import com.nihaskalam.automobilenewsapp.domain.model.NetworkResult
import com.nihaskalam.automobilenewsapp.domain.model.NewsFeed
import javax.inject.Inject

class RetrofitNewsDataSource @Inject constructor(
    private val apiService: ApiService
) : BaseNewsDataSource() {
    suspend fun getNews(): NetworkResult<NewsFeed> {
        return handleApi { apiService.getNews() }
    }
}