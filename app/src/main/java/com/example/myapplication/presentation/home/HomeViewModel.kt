package com.example.myapplication.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.common.Resource
import com.example.myapplication.data.local.entity.TechniqueGuide
import com.example.myapplication.domain.model.User
import com.example.myapplication.domain.use_case.technique_guide.GetTechniqueGuidesUseCase
import com.example.myapplication.domain.use_case.user.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getTechniqueGuidesUseCase: GetTechniqueGuidesUseCase,
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {

    private val _user = MutableLiveData<User>(null)
    val user: LiveData<User>
        get() = _user

    private val _techniqueGuides = MutableLiveData<List<TechniqueGuide>>()
    val techniqueGuides: LiveData<List<TechniqueGuide>>
        get() = _techniqueGuides

    private val _techniqueGuidesLoading = MutableLiveData(false)
    val techniqueGuidesLoading: LiveData<Boolean>
        get() = _techniqueGuidesLoading

    private val _techniqueGuidesErrorMessage = MutableLiveData<String>(null)
    val techniqueGuidesErrorMessage: LiveData<String>
        get() = _techniqueGuidesErrorMessage

    init {
        getTechniqueGuides()
        _user.value = getUserUseCase()
    }

    private fun getTechniqueGuides() {
        getTechniqueGuidesUseCase().onEach {
            when (it) {
                is Resource.Success -> {
                    _techniqueGuidesLoading.value = false
                    _techniqueGuides.value = it.data ?: emptyList()
                }

                is Resource.Error -> {
                    _techniqueGuidesLoading.value = false
                    _techniqueGuidesErrorMessage.value = it.message ?: "Unexpected Error"
                }

                is Resource.Loading -> {
                    _techniqueGuidesLoading.value = true
                }
            }
        }.launchIn(viewModelScope)
    }
}