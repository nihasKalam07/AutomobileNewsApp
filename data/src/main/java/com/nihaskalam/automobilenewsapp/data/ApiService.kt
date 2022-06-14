package com.nihaskalam.automobilenewsapp.data

import com.nihaskalam.automobilenewsapp.domain.model.NewsFeed
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Retrofit Api Service
 */
const val CATEGORY = "category"
const val GET_NEWS_URL = "/news"

interface ApiService {

    @GET(GET_NEWS_URL)
    suspend fun getNews(@Query(CATEGORY) category: String = "automobile"): Response<NewsFeed>
}