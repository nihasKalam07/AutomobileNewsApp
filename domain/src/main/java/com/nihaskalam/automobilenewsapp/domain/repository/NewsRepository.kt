package com.nihaskalam.automobilenewsapp.domain.repository

import com.nihaskalam.automobilenewsapp.domain.model.NetworkResult
import com.nihaskalam.automobilenewsapp.domain.model.NewsFeed
import kotlinx.coroutines.flow.Flow

/**
 * Repository Interface which should be implemented in Data layer
 */
interface NewsRepository {
    /**
     * Method to get NewsFeed as coroutine flow
     */
    suspend fun getNews(): Flow<NetworkResult<NewsFeed>>
}