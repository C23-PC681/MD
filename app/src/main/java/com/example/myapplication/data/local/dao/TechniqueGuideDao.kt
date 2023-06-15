package com.example.myapplication.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.myapplication.data.local.entity.TechniqueGuide

@Dao
interface TechniqueGuideDao {
    @Query("SELECT * FROM technique_guide")
    suspend fun getTechniqueGuides(): List<TechniqueGuide>

    @Query("SELECT * FROM technique_guide WHERE techniqueGuideId = :techniqueGuideId")
    suspend fun getTechniqueGuide(techniqueGuideId: Int): TechniqueGuide
}