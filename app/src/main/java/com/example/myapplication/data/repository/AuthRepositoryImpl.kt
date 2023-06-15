package com.example.myapplication.data.repository

import com.example.myapplication.data.remote.api.AuthService
import com.example.myapplication.data.remote.dto.EmailCheckResponse
import com.example.myapplication.data.remote.dto.LoginResponse
import com.example.myapplication.data.remote.dto.RegisterResponse
import com.example.myapplication.domain.model.User
import com.example.myapplication.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authService: AuthService
) : AuthRepository {

    override suspend fun emailCheck(email: String): EmailCheckResponse {
        return authService.emailCheck(email)
    }

    override suspend fun register(
        user: User
    ): RegisterResponse {
        return authService.register(
            name = user.name!!,
            email = user.email!!,
            password = user.password!!,
            gender = user.gender!!,
            height = user.height!!,
            weight = user.weight!!,
            goal = user.goal!!
        )
    }

    override suspend fun login(
        email: String,
        password: String
    ): LoginResponse {
        return authService.login(
            email = email,
            password = password
        )
    }
}