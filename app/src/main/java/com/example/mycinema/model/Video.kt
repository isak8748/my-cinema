package com.example.mycinema.model

import com.squareup.moshi.Json

data class Video(
    @Json(name = "id")
    var id: String,

    @Json(name = "key")
    var key: String,

    @Json(name = "site")
    var site: String

)