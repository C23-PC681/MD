package com.example.myapplication.domain.use_case.exercise

import com.example.myapplication.domain.model.Exercise
import com.example.myapplication.domain.repository.ExerciseRepository
import javax.inject.Inject

class GetExercisesUseCase @Inject constructor(
    private val exerciseRepository: ExerciseRepository
) {
    operator fun invoke(fitnessPlanIndex: Int): List<Exercise> {
        return exerciseRepository.getExercises(fitnessPlanIndex)
    }
}