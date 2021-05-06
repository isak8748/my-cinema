package com.example.mycinema.model

import com.squareup.moshi.Json

data class Review(
    @Json(name = "id")
    var id: String,

    @Json(name = "author")
    var author: String,

    @Json(name = "content")
    var content: String
)