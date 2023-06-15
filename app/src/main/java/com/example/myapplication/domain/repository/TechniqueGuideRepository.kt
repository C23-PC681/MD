package com.example.myapplication.domain.repository

import com.example.myapplication.data.local.entity.TechniqueGuide

interface TechniqueGuideRepository {
    suspend fun getTechniqueGuides(): List<TechniqueGuide>
    suspend fun getTechniqueGuide(techniqueGuideId: Int): TechniqueGuide
}