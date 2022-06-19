package com.example.basicstackapp.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class QuestionsResponse {
    @Json(name = "has_more")
    var hasMore: Boolean? = null

    var items: List<Question> = emptyList()

    @Json(name = "quota_max")
    var quotaMax: Int? = null

    @Json(name = "quota_remaining")
    var quotaRemaining: Int? = null
}