package com.example.myapplication.domain.use_case.authentication.logout

import com.example.myapplication.domain.repository.DataStoreRepository
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class LogOutUseCase @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) {
    operator fun invoke() = runBlocking {
        dataStoreRepository.clearBooleanPreference("loginSession")
    }
}