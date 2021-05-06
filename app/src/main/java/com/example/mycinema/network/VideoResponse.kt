package com.example.mycinema.network

import com.example.mycinema.model.Video
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
class VideoResponse {
    @Json(name = "id")
    var id = 0

    @Json(name = "results")
    var results: List<Video> = listOf()

}