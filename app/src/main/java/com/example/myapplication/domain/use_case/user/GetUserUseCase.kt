package com.example.myapplication.domain.use_case.user

import com.example.myapplication.domain.model.User
import com.example.myapplication.domain.repository.DataStoreRepository
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) {
    operator fun invoke() = runBlocking {
        val name = dataStoreRepository.getString("name")
        val email = dataStoreRepository.getString("email")
        val password = dataStoreRepository.getString("password")
        val height = dataStoreRepository.getInt("height")
        val weight = dataStoreRepository.getInt("weight")
        val gender = dataStoreRepository.getString("gender")
        val goal = dataStoreRepository.getString("goal")

        return@runBlocking User(
            name = name,
            email = email,
            password = password,
            height = height,
            weight = weight,
            gender = gender,
            goal = goal
        )
    }
}