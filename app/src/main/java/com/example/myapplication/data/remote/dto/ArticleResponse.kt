package com.example.myapplication.data.remote.dto

data class ArticleResponse(
    val error: Boolean,
    val message: String,
    val data: List<ArticleDto>,
)