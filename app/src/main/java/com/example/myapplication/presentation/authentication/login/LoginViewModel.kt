package com.example.myapplication.presentation.authentication.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.common.Resource
import com.example.myapplication.domain.use_case.authentication.login.LoginUseCase
import com.example.myapplication.domain.use_case.authentication.session.save_user_session.SaveUserSessionUseCase
import com.example.myapplication.domain.use_case.validation.ValidateEmailUseCase
import com.example.myapplication.domain.use_case.validation.ValidatePasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase,
    private val loginUseCase: LoginUseCase,
    private val saveUserSessionUseCase: SaveUserSessionUseCase
) : ViewModel() {

    private val _passwordVisible = MutableLiveData(false)
    val passwordVisible: LiveData<Boolean>
        get() = _passwordVisible

    private val _emailError = MutableLiveData("")
    val emailError: LiveData<String>
        get() = _emailError

    private val _passwordError = MutableLiveData("")
    val passwordError: LiveData<String>
        get() = _passwordError

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _errorMessage = MutableLiveData("")
    val errorMessage: LiveData<String>
        get() = _errorMessage

    private val _isValid = MutableLiveData(false)
    val isValid: LiveData<Boolean>
        get() = _isValid

    fun togglePasswordVisibility() {
        _passwordVisible.value = !_passwordVisible.value!!
    }

    private fun validateInput(email: String, password: String): Boolean {
        val emailResult = validateEmailUseCase(email)
        val passwordResult = validatePasswordUseCase(password)

        val hasError = listOf(
            emailResult,
            passwordResult
        ).any { it.errorMessage != null }

        if (hasError) {
            _emailError.value = emailResult.errorMessage
            _passwordError.value = passwordResult.errorMessage
            return false
        }
        return true
    }

    fun login(
        email: String,
        password: String
    ) {
        if (!validateInput(email, password)) return

        loginUseCase(email = email, password = password).onEach {
            when (it) {
                is Resource.Success -> {
                    it.data?.let { user ->
                        saveUserSessionUseCase(user)
                    }
                    _isValid.value = true
                    _isLoading.value = false
                }

                is Resource.Error -> {
                    _isLoading.value = false
                    _errorMessage.value = it.message
                }

                is Resource.Loading -> {
                    _isLoading.value = true
                }
            }
        }.launchIn(viewModelScope)
    }
}

