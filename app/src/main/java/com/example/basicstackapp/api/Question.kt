package com.example.basicstackapp.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Question(
    @Json(name = "question_id")
    val questionId: Int,

    var title: String? = null,

    var body: String? = null,

    var owner: Owner? = null,

    @Json(name = "is_answered")
    var isAnswered: Boolean? = null,

    @Json(name = "view_count")
    var viewCount: Int? = null,

    @Json(name = "accepted_answer_id")
    var acceptedAnswerId: Int? = null,

    @Json(name = "answer_count")
    var answerCount: Int? = null,

    var score: Int? = null,

    @Json(name = "last_activity_date")
    var lastActivityDate: Int? = null,

    @Json(name = "creation_date")
    var creationDate: Int? = null,

    @Json(name = "last_edit_date")
    var lastEditDate: Int? = null,

    var link: String? = null
)
