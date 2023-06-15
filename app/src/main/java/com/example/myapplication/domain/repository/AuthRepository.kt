package com.example.myapplication.domain.repository

import com.example.myapplication.data.remote.dto.EmailCheckResponse
import com.example.myapplication.data.remote.dto.LoginResponse
import com.example.myapplication.data.remote.dto.RegisterResponse
import com.example.myapplication.domain.model.User

interface AuthRepository {

    suspend fun emailCheck(
        email: String
    ): EmailCheckResponse

    suspend fun register(
        user: User
    ): RegisterResponse

    suspend fun login(
        email: String,
        password: String
    ): LoginResponse
}