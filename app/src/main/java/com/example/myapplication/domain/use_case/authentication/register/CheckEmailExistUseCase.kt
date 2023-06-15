package com.example.myapplication.domain.use_case.authentication.register

import com.example.myapplication.common.Resource
import com.example.myapplication.domain.repository.AuthRepository
import com.example.myapplication.domain.use_case.validation.ValidationResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CheckEmailExistUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(email: String): Flow<Resource<ValidationResult>> = flow {
        emit(Resource.Loading())
        try {
            val data = authRepository.emailCheck(email)
            emit(Resource.Success(ValidationResult(successful = true)))
        } catch (e: HttpException) {
            emit(Resource.Error("Email already exists!"))
        } catch (e: IOException) {
            emit(Resource.Error("No internet connection"))
        }
    }
}