package com.example.myapplication.domain.repository

import com.example.myapplication.domain.model.Exercise

interface ExerciseRepository {
    fun getExercises(fitnessPlanIndex: Int): List<Exercise>
}