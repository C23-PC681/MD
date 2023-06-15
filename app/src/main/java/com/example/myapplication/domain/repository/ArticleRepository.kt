package com.example.myapplication.domain.repository

import com.example.myapplication.data.remote.dto.ArticleResponse

interface ArticleRepository {
    suspend fun getArticles(): ArticleResponse
}