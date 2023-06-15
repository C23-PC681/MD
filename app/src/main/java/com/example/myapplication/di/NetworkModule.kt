package com.example.myapplication.di

import com.example.myapplication.data.remote.api.ApiConfig
import com.example.myapplication.data.remote.api.ArticleService
import com.example.myapplication.data.remote.api.AuthService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideAuthService(): AuthService {
        return ApiConfig.getAuthService()
    }

    @Provides
    @Singleton
    fun provideArticleService(): ArticleService {
        return ApiConfig.getArticleService()
    }

}