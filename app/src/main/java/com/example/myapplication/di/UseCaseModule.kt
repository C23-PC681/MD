package com.example.myapplication.di

import com.example.myapplication.domain.repository.AuthRepository
import com.example.myapplication.domain.repository.DataStoreRepository
import com.example.myapplication.domain.repository.TechniqueGuideRepository
import com.example.myapplication.domain.use_case.authentication.logout.LogOutUseCase
import com.example.myapplication.domain.use_case.authentication.register.CheckEmailExistUseCase
import com.example.myapplication.domain.use_case.authentication.session.get_user_session.GetUserSessionUseCase
import com.example.myapplication.domain.use_case.authentication.session.save_user_session.SaveUserSessionUseCase
import com.example.myapplication.domain.use_case.technique_guide.GetTechniqueGuidesUseCase
import com.example.myapplication.domain.use_case.user.GetUserUseCase
import com.example.myapplication.domain.use_case.validation.ValidateEmailUseCase
import com.example.myapplication.domain.use_case.validation.ValidateNameUseCase
import com.example.myapplication.domain.use_case.validation.ValidatePasswordUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    @Singleton
    fun provideValidateNameUseCase(): ValidateNameUseCase {
        return ValidateNameUseCase()
    }

    @Provides
    @Singleton
    fun provideValidateEmailUseCase(): ValidateEmailUseCase {
        return ValidateEmailUseCase()
    }

    @Provides
    @Singleton
    fun provideValidatePasswordUseCase(): ValidatePasswordUseCase {
        return ValidatePasswordUseCase()
    }

    @Provides
    @Singleton
    fun provideGetTechniqueGuidesUseCase(
        techniqueGuideRepository: TechniqueGuideRepository
    ): GetTechniqueGuidesUseCase {
        return GetTechniqueGuidesUseCase(techniqueGuideRepository)
    }

    @Provides
    @Singleton
    fun provideCheckEmailExistUseCase(
        authRepository: AuthRepository
    ): CheckEmailExistUseCase {
        return CheckEmailExistUseCase(authRepository)
    }

    @Provides
    @Singleton
    fun provideSaveUserSessionUseCase(
        dataStoreRepository: DataStoreRepository
    ): SaveUserSessionUseCase {
        return SaveUserSessionUseCase(dataStoreRepository)
    }

    @Provides
    @Singleton
    fun provideReadUserSessionUseCase(
        dataStoreRepository: DataStoreRepository
    ): GetUserSessionUseCase {
        return GetUserSessionUseCase(dataStoreRepository)
    }

    @Provides
    @Singleton
    fun provideLogOutUseCase(
        dataStoreRepository: DataStoreRepository
    ): LogOutUseCase {
        return LogOutUseCase(dataStoreRepository)
    }

    @Provides
    @Singleton
    fun provideGetUserUseCase(
        dataStoreRepository: DataStoreRepository
    ): GetUserUseCase {
        return GetUserUseCase(dataStoreRepository)
    }
}