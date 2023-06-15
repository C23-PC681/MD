package com.example.myapplication.presentation.fitness_plan

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.domain.model.Exercise
import com.example.myapplication.domain.model.User
import com.example.myapplication.domain.use_case.exercise.GetExercisesUseCase
import com.example.myapplication.domain.use_case.user.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FitnessPlanViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val getExercisesUseCase: GetExercisesUseCase,
) : ViewModel() {

    private val _exercises = MutableLiveData<List<Exercise>>()
    val exercises: LiveData<List<Exercise>>
        get() = _exercises

    private val _errorMessage = MutableLiveData("")
    val errorMessage: LiveData<String>
        get() = _errorMessage

    private val _user = MutableLiveData<User>(null)
    val user: LiveData<User>
        get() = _user

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    init {
        _user.value = getUserUseCase()
        if (user.value?.goal == "LOSE_WEIGHT") {
            getExercises(1)
        } else {
            getExercises(0)
        }
    }

    fun getExercises(fitnessPlanIndex: Int) {
        _exercises.value = getExercisesUseCase(fitnessPlanIndex)
    }
}