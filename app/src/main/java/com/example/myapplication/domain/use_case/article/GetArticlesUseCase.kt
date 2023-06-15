package com.example.myapplication.domain.use_case.article

import android.util.Log
import com.example.myapplication.common.Resource
import com.example.myapplication.data.remote.dto.toModel
import com.example.myapplication.domain.model.Article
import com.example.myapplication.domain.repository.ArticleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetArticlesUseCase @Inject constructor(
    private val repository: ArticleRepository
) {
    operator fun invoke(): Flow<Resource<List<Article>>> = flow {
        try {
            Log.e("#getartUC", "cuy")
            emit(Resource.Loading())
            val articles = repository.getArticles().data.map { it.toModel() }
            emit(Resource.Success(articles))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected Error"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach the server. Check your internet connection!"))
        }
    }
}