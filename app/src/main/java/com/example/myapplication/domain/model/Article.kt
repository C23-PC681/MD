package com.example.myapplication.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Article(
    val title: String? = null,
    val description: String? = null,
    val urlToImage: String? = null
) : Parcelable