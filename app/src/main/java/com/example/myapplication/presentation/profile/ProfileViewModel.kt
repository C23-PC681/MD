package com.example.myapplication.presentation.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.domain.model.User
import com.example.myapplication.domain.use_case.authentication.logout.LogOutUseCase
import com.example.myapplication.domain.use_case.user.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val logOutUseCase: LogOutUseCase
) : ViewModel() {

    private val _loginSession = MutableLiveData(true)
    val loginSession: LiveData<Boolean>
        get() = _loginSession

    private val _user = MutableLiveData<User>()
    val user: LiveData<User>
        get() = _user

    init {
        _user.value = getUserUseCase()
    }

    fun logOut() {
        logOutUseCase()
        _loginSession.value = false
    }
}