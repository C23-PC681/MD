package com.example.myapplication.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val name: String? = null,
    val email: String? = null,
    val password: String? = null,
    val gender: String? = null,
    val height: Int? = null,
    val weight: Int? = null,
    val goal: String? = null,
    val token: String? = null
) : Parcelable