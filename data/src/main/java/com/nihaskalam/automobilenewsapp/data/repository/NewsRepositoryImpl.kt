package com.nihaskalam.automobilenewsapp.data.repository

import com.nihaskalam.automobilenewsapp.data.network.RetrofitNewsDataSource
import com.nihaskalam.automobilenewsapp.domain.model.NetworkResult
import com.nihaskalam.automobilenewsapp.domain.model.NewsFeed
import com.nihaskalam.automobilenewsapp.domain.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsDataSource: RetrofitNewsDataSource
): NewsRepository {
    override suspend fun getNews(): Flow<NetworkResult<NewsFeed>> {
        return flow {
            emit(NetworkResult.ApiLoading())
            val result = newsDataSource.getNews()
            emit(result)
        }.flowOn(Dispatchers.IO)
    }
}