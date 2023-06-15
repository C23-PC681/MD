package com.example.myapplication.presentation.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.use_case.authentication.session.get_user_session.GetUserSessionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    getUserSessionUseCase: GetUserSessionUseCase
) : ViewModel() {

    private val _loginSession = MutableLiveData(false)
    val loginSession: LiveData<Boolean>
        get() = _loginSession

    init {
        viewModelScope.launch {
            getUserSessionUseCase()?.let {
                _loginSession.value = true
            }
        }
    }
}