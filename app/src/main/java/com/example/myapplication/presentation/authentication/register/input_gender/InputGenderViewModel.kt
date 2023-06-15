package com.example.myapplication.presentation.authentication.register.input_gender

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class InputGenderViewModel @Inject constructor() : ViewModel() {

    private val _gender = MutableLiveData("")
    val gender: LiveData<String>
        get() = _gender

    fun onCheckedChanged(gender: Gender) {
        _gender.value = gender.name
    }
}