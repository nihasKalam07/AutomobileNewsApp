package com.nihaskalam.automobilenewsapp.data

import com.nihaskalam.automobilenewsapp.domain.model.NewsFeed
import retrofit2.Response
import retrofit2.http.GET

/**
 * Retrofit Api Service
 */
interface ApiService {

    @GET("/news?category=automobile")
    suspend fun getNews(): Response<NewsFeed>
}