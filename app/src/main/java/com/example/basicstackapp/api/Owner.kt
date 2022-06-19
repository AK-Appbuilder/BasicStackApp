package com.example.basicstackapp.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Owner {
    var reputation: Int? = null

    @Json(name = "user_id")
    var userId: Int? = null

    @Json(name = "user_type")
    var userType: String? = null

    @Json(name = "accept_rate")
    var acceptRate: Int? = null

    @Json(name = "profile_image")
    var profileImage: String? = null

    @Json(name = "display_name")
    var displayName: String? = null

    var link: String? = null
}