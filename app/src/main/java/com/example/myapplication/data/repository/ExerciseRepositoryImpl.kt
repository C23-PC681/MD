package com.example.myapplication.data.repository

import com.example.myapplication.domain.model.Exercise
import com.example.myapplication.domain.repository.ExerciseRepository
import com.example.myapplication.domain.use_case.exercise.FitnessPlan

class ExerciseRepositoryImpl : ExerciseRepository {
    override fun getExercises(fitnessPlanIndex: Int): List<Exercise> {
        val fitnessPlans = listOf(
            FitnessPlan.FitnessPlanMuscleGain1,
            FitnessPlan.FitnessPlanLoseWeight1,
            FitnessPlan.FitnessPlanMuscleGain2,
            FitnessPlan.FitnessPlanLoseWeight2,
        )
        return fitnessPlans[fitnessPlanIndex].exercises
    }
}