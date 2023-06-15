package com.example.myapplication.presentation.authentication.register.input_credential

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.common.Resource
import com.example.myapplication.domain.model.User
import com.example.myapplication.domain.use_case.authentication.register.CheckEmailExistUseCase
import com.example.myapplication.domain.use_case.validation.ValidateEmailUseCase
import com.example.myapplication.domain.use_case.validation.ValidateNameUseCase
import com.example.myapplication.domain.use_case.validation.ValidatePasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class InputCredentialViewModel @Inject constructor(
    private val validateNameUseCase: ValidateNameUseCase,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase,
    private val checkEmailExistUseCase: CheckEmailExistUseCase
) : ViewModel() {

    private val _passwordVisible = MutableLiveData(false)
    val passwordVisible: LiveData<Boolean>
        get() = _passwordVisible

    private val _nameError = MutableLiveData("")
    val nameError: LiveData<String>
        get() = _nameError

    private val _emailError = MutableLiveData("")
    val emailError: LiveData<String>
        get() = _emailError

    private val _passwordError = MutableLiveData("")
    val passwordError: LiveData<String>
        get() = _passwordError

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _isValid = MutableLiveData(false)
    val isValid: LiveData<Boolean>
        get() = _isValid

    private val _user = MutableLiveData(User())
    val user: LiveData<User>
        get() = _user

    fun togglePasswordVisibility() {
        _passwordVisible.value = !_passwordVisible.value!!
    }

    fun validateInput(user: User) {
        val nameResult = validateNameUseCase(user.name!!)
        val emailResult = validateEmailUseCase(user.email!!)
        val passwordResult = validatePasswordUseCase(user.password!!)

        val hasError = listOf(
            nameResult,
            emailResult,
            passwordResult
        ).any { it.errorMessage != null }

        if (hasError) {
            _nameError.value = nameResult.errorMessage
            _emailError.value = emailResult.errorMessage
            _passwordError.value = passwordResult.errorMessage
        } else {
            _nameError.value = ""
            _emailError.value = ""
            _passwordError.value = ""

            checkEmailExistUseCase(user.email).onEach {
                when (it) {
                    is Resource.Success -> {
                        _isLoading.value = false
                        _user.value = user
                        _isValid.value = true
                    }

                    is Resource.Error -> {
                        _isLoading.value = false
                        _emailError.value = it.message
                    }

                    is Resource.Loading -> {
                        _isLoading.value = true
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    fun setIsValid(boolean: Boolean) {
        _isValid.value = boolean
    }
}

