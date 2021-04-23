package com.example.mycinema.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieDetail(
    var id : Long = 0L,
    var website : String,
    var genres: List<String>,
    var imdb_id: String
) : Parcelable