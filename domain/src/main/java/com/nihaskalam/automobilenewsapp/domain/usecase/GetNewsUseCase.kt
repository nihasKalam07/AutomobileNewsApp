package com.nihaskalam.automobilenewsapp.domain.usecase

import com.nihaskalam.automobilenewsapp.domain.model.NetworkResult
import com.nihaskalam.automobilenewsapp.domain.model.NewsFeed
import com.nihaskalam.automobilenewsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Use case layer which call repositories to get  data
 *
 * @param repository Repository to connect with Android data frameworks
 */
class GetNewsUseCase @Inject constructor(private val repository: NewsRepository) {
    suspend operator fun invoke(): Flow<NetworkResult<NewsFeed>> {
        return repository.getNews()
    }
}