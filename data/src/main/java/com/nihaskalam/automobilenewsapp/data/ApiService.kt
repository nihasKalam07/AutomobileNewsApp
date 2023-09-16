package com.nihaskalam.automobilenewsapp.data

import com.nihaskalam.automobilenewsapp.domain.model.NewsFeed
import retrofit2.Response
import retrofit2.http.GET

/**
 * Retrofit Api Service
 */
const val GET_AUTOMOBILE_NEWS_URL = "data/api/news/automobile.json"

interface ApiService {

    @GET(GET_AUTOMOBILE_NEWS_URL)
    suspend fun getNews(): Response<NewsFeed>
}