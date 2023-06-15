package com.example.myapplication.domain.use_case.technique_guide

import com.example.myapplication.common.Resource
import com.example.myapplication.data.local.entity.TechniqueGuide
import com.example.myapplication.domain.repository.TechniqueGuideRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetTechniqueGuidesUseCase @Inject constructor(
    private val techniqueGuideRepository: TechniqueGuideRepository
) {
    operator fun invoke(): Flow<Resource<List<TechniqueGuide>>> = flow {
        try {
            emit(Resource.Loading())
            val listTechniqueGuide = techniqueGuideRepository.getTechniqueGuides()
            emit(Resource.Success(listTechniqueGuide))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected Error"))
        }
    }
}