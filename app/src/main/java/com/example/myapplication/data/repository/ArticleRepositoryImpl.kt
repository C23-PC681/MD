package com.example.myapplication.data.repository

import com.example.myapplication.data.remote.api.ArticleService
import com.example.myapplication.data.remote.dto.ArticleResponse
import com.example.myapplication.domain.repository.ArticleRepository
import javax.inject.Inject

class ArticleRepositoryImpl @Inject constructor(
    private val articleService: ArticleService
) : ArticleRepository {

    override suspend fun getArticles(): ArticleResponse {
        return articleService
            .getArticles(
                countryId = "us",
                apiKey = "02e2f25fea264d8788a370823a213bda"
            )
    }
}