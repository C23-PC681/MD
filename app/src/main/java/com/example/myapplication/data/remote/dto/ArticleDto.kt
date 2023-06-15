package com.example.myapplication.data.remote.dto

import com.example.myapplication.domain.model.Article

data class ArticleDto(
    val title: String? = null,
    val description: String? = null,
    val urlToImage: String? = null
)

fun ArticleDto.toModel(): Article {
    return Article(
        title = title,
        description = description,
        urlToImage = urlToImage
    )
}