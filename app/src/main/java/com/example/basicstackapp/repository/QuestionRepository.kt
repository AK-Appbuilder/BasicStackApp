package com.example.basicstackapp.repository

import com.example.basicstackapp.api.*
import javax.inject.Inject

interface QuestionRepository {
    suspend fun loadQuestion(
        page: Int,
        pageSize: Int,
        order: String,
        sortCondition: String,
        site: String,
        filter: String,
        siteKey: String
    ): Result<QuestionsResponse>
}

class QuestionRepositoryImpl @Inject constructor(val apiService: StackApiService) :
    QuestionRepository {

    override suspend fun loadQuestion(
        page: Int,
        pageSize: Int,
        order: String,
        sortCondition: String,
        site: String,
        filter: String,
        siteKey: String
    ) = safeApiCall {
        apiService.getQuestionsForAll(
            page,
            pageSize,
            order,
            sortCondition,
            site,
            filter,
            siteKey
        )
    }
}
