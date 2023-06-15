package com.example.myapplication.domain.use_case.authentication.register

import com.example.myapplication.common.Resource
import com.example.myapplication.data.remote.dto.RegisterResponse
import com.example.myapplication.domain.model.User
import com.example.myapplication.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(user: User): Flow<Resource<RegisterResponse>> = flow {
        emit(Resource.Loading())
        try {
            val data = authRepository.register(user = user)
            emit(Resource.Success(data))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected Error"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach the server. Check your internet connection!"))
        }
    }
}