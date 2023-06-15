package com.example.myapplication.domain.use_case.authentication.session.get_user_session

import com.example.myapplication.domain.repository.DataStoreRepository
import javax.inject.Inject

class GetUserSessionUseCase @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) {
    suspend operator fun invoke(): Boolean? {
        return dataStoreRepository.getBoolean("loginSession")
    }
}