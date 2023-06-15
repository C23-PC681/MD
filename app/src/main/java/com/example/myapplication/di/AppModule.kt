package com.example.myapplication.di

import com.example.myapplication.data.local.dao.TechniqueGuideDao
import com.example.myapplication.data.remote.api.ArticleService
import com.example.myapplication.data.remote.api.AuthService
import com.example.myapplication.data.repository.ArticleRepositoryImpl
import com.example.myapplication.data.repository.AuthRepositoryImpl
import com.example.myapplication.data.repository.ExerciseRepositoryImpl
import com.example.myapplication.data.repository.TechniqueGuideRepositoryImpl
import com.example.myapplication.domain.repository.ArticleRepository
import com.example.myapplication.domain.repository.AuthRepository
import com.example.myapplication.domain.repository.ExerciseRepository
import com.example.myapplication.domain.repository.TechniqueGuideRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideArticleRepository(articleService: ArticleService): ArticleRepository {
        return ArticleRepositoryImpl(articleService)
    }

    @Provides
    @Singleton
    fun provideTechniqueGuideRepository(
        techniqueGuideDao: TechniqueGuideDao
    ): TechniqueGuideRepository {
        return TechniqueGuideRepositoryImpl(techniqueGuideDao)
    }

    @Provides
    @Singleton
    fun provideExerciseRepository(): ExerciseRepository {
        return ExerciseRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideAuthRepository(authService: AuthService): AuthRepository {
        return AuthRepositoryImpl(authService)
    }
}