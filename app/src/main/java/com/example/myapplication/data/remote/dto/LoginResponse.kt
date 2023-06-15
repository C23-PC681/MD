package com.example.myapplication.data.remote.dto

data class LoginResponse(
    val error: Boolean,
    val message: String,
    val data: LoginResult
)