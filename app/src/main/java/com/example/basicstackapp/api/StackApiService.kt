package com.example.basicstackapp.api

import com.example.basicstackapp.common.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface StackApiService {

    @GET(Constants.QUESTIONS_END_POINT)
    suspend fun getQuestionsForAll(
        @Query("page") page: Int,
        @Query("pagesize") pageSize: Int,
        @Query("order") order: String?,
        @Query("sort") sortCondition: String?,
        @Query("site") site: String?,
        @Query(value = "filter", encoded = true) filter: String?,
        @Query("key") siteKey: String?
    ): QuestionsResponse
}
