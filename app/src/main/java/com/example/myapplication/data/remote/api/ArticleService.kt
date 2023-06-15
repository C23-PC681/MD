package com.example.myapplication.data.remote.api

import com.example.myapplication.data.remote.dto.ArticleResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticleService {
    @GET("article/all")
    suspend fun getArticles(
        @Query("country") countryId: String,
        @Query("apiKey") apiKey: String
    ): ArticleResponse
}