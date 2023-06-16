package com.example.myapplication.presentation.authentication.register.input_goal

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.common.Resource
import com.example.myapplication.domain.model.User
import com.example.myapplication.domain.use_case.authentication.register.RegisterUseCase
import com.example.myapplication.domain.use_case.authentication.session.save_user_session.SaveUserSessionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class InputGoalViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
    private val saveUserSessionUseCase: SaveUserSessionUseCase
) : ViewModel() {

    private val _goal = MutableLiveData("")
    val goal: LiveData<String>
        get() = _goal

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _errorMessage = MutableLiveData<String>("")
    val errorMessage: LiveData<String>
        get() = _errorMessage

    private val _isValid = MutableLiveData<Boolean>(false)
    val isValid: LiveData<Boolean>
        get() = _isValid

    fun onCheckedChanged(goal: Goal) {
        _goal.value = goal.name
    }

    fun register(user: User) {
        registerUseCase(user).onEach {
            when (it) {
                is Resource.Success -> {
                    saveUserSessionUseCase(user)
                    Log.e("#inputgoal", "$user")
                    _isLoading.value = false
                    _isValid.value = true
                }

                is Resource.Error -> {
                    _isLoading.value = false
                    _errorMessage.value = it.message!!
                }

                is Resource.Loading -> {
                    _isLoading.value = true
                }
            }
        }.launchIn(viewModelScope)
    }
}