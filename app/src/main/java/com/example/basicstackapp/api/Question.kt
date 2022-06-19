package com.example.basicstackapp.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.security.acl.Owner


@JsonClass(generateAdapter = true)
class Question {
    @Json(name = "tags")
    var tags: List<String>? = null

    @Json(name = "body")
    var body: String? = null

    @Json(name = "owner")
    var owner: Owner? = null

    @Json(name = "is_answered")
    var isAnswered: Boolean? = null

    @Json(name = "view_count")
    var viewCount: Int? = null

    @Json(name = "accepted_answer_id")
    var acceptedAnswerId: Int? = null

    @Json(name = "answer_count")
    var answerCount: Int? = null

    @Json(name = "score")
    var score: Int? = null

    @Json(name = "last_activity_date")
    var lastActivityDate: Int? = null

    @Json(name = "creation_date")
    var creationDate: Int? = null

    @Json(name = "last_edit_date")
    var lastEditDate: Int? = null

    @Json(name = "question_id")
    var questionId: Int? = null

    @Json(name = "link")
    var link: String? = null

    @Json(name = "title")
    var title: String? = null
}