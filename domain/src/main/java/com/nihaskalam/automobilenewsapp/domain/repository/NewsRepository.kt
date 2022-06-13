package com.nihaskalam.automobilenewsapp.domain.repository

import com.nihaskalam.automobilenewsapp.domain.model.NetworkResult
import com.nihaskalam.automobilenewsapp.domain.model.NewsFeed
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun getNews(): Flow<NetworkResult<NewsFeed>>
}