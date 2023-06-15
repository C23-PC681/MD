package com.example.myapplication.data.remote.dto

import com.example.myapplication.domain.model.User

data class LoginResult(
    val userId: String,
    val name: String,
    val email: String,
    val gender: String,
    val height: Int,
    val weight: Int,
    val goal: String,
    val token: String
)

fun LoginResult.toModel(): User =
    User(
        name = name,
        email = email,
        gender = gender,
        height = height,
        weight = weight,
        goal = goal,
        token = token
    )