package com.example.myapplication.data.remote.api

import com.example.myapplication.data.remote.dto.EmailCheckResponse
import com.example.myapplication.data.remote.dto.LoginResponse
import com.example.myapplication.data.remote.dto.RegisterResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthService {

    @FormUrlEncoded
    @POST("auth/email-check")
    suspend fun emailCheck(
        @Field("email") email: String
    ): EmailCheckResponse

    @FormUrlEncoded
    @POST("auth/register")
    suspend fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("gender") gender: String,
        @Field("height") height: Int,
        @Field("weight") weight: Int,
        @Field("goal") goal: String
    ): RegisterResponse

    @FormUrlEncoded
    @POST("auth/login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse
}