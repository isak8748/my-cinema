package com.example.mycinema.network

import com.example.mycinema.model.Review
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
class ReviewResponse {
    @Json(name = "id")
    var id = 0

    @Json(name = "page")
    var page = 0

    @Json(name = "results")
    var results: List<Review> = listOf()

    @Json(name = "total_pages")
    var total_pages: Int = 0

    @Json(name = "total_results")
    var total_results: Int = 0
}