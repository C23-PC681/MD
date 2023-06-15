package com.example.myapplication.domain.use_case.authentication.session.save_user_session

import com.example.myapplication.domain.model.User
import com.example.myapplication.domain.repository.DataStoreRepository
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class SaveUserSessionUseCase @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) {
    operator fun invoke(user: User) = runBlocking {
        dataStoreRepository.putBoolean("loginSession", true)
        dataStoreRepository.putString("name", user.name.toString())
        dataStoreRepository.putString("email", user.email.toString())
        dataStoreRepository.putString("password", user.password.toString())
        dataStoreRepository.putString("gender", user.gender.toString())
        dataStoreRepository.putInt("height", user.height!!)
        dataStoreRepository.putInt("weight", user.weight!!)
        dataStoreRepository.putString("goal", user.goal.toString())
    }
}