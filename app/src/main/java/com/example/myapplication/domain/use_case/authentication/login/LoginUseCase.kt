package com.example.myapplication.domain.use_case.authentication.login

import com.example.myapplication.common.Resource
import com.example.myapplication.data.remote.dto.toModel
import com.example.myapplication.domain.model.User
import com.example.myapplication.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    operator fun invoke(email: String, password: String): Flow<Resource<User>> = flow {
        emit(Resource.Loading())
        try {
            val data = authRepository
                .login(email = email, password = password)
                .data
                .toModel()
            emit(Resource.Success(data))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected Error"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach the server. Check your internet connection!"))
        }
    }
}