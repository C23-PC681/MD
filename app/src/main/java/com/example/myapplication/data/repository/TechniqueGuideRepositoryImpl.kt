package com.example.myapplication.data.repository

import com.example.myapplication.data.local.dao.TechniqueGuideDao
import com.example.myapplication.data.local.entity.TechniqueGuide
import com.example.myapplication.domain.repository.TechniqueGuideRepository
import javax.inject.Inject

class TechniqueGuideRepositoryImpl @Inject constructor(
    private val techniqueGuideDao: TechniqueGuideDao
) : TechniqueGuideRepository {
    override suspend fun getTechniqueGuides(): List<TechniqueGuide> {
        return techniqueGuideDao.getTechniqueGuides()
    }

    override suspend fun getTechniqueGuide(techniqueGuideId: Int): TechniqueGuide {
        return techniqueGuideDao.getTechniqueGuide(techniqueGuideId)
    }
}