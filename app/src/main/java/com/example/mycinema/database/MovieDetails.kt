package com.example.mycinema.database

import com.example.mycinema.model.MovieDetail

class MovieDetails {
    val list = mutableListOf<MovieDetail>()

    init{
        list.add(
            MovieDetail(
                1,
                "https://movies.disney.com/raya-and-the-last-dragon",
                 listOf("Animation", "Adventure", "Fantasy", "Family"),
                "tt5109280"
            )
        )
        list.add(
            MovieDetail(
                2,
                "https://www.netflix.com/title/81218770",
                listOf("Thriller", "Action", "Drama"),
                "tt11734264"
            )
        )
        list.add(
            MovieDetail(
                3,
                "https://www.hbomax.com/zacksnydersjusticeleague",
                listOf("Action", "Adventure", "Fantasy"),
                "tt12361974"
            )
        )
        list.add(
            MovieDetail(
                4,
                "https://www.tomandjerrymovie.com",
                listOf("Comedy", "Family", "Animation"),
                "tt1361336"
            )
        )
        list.add(
            MovieDetail(
                5,
                "https://netflix.com/title/81038588",
                listOf("Action", "Crime", "Thriller"),
                "tt9845564"
            )
        )

        list.add(
            MovieDetail(
                6,
                "https://movies.disney.com/raya-and-the-last-dragon",
                listOf("Animation", "Adventure", "Fantasy", "Family"),
                "tt5109280"
            )
        )
        list.add(
            MovieDetail(
                7,
                "https://www.netflix.com/title/81218770",
                listOf("Thriller", "Action", "Drama"),
                "tt11734264"
            )
        )
        list.add(
            MovieDetail(
                8,
                "https://www.hbomax.com/zacksnydersjusticeleague",
                listOf("Action", "Adventure", "Fantasy"),
                "tt12361974"
            )
        )
        list.add(
            MovieDetail(
                9,
                "https://www.tomandjerrymovie.com",
                listOf("Comedy", "Family", "Animation"),
                "tt1361336"
            )
        )
        list.add(
            MovieDetail(
                10,
                "https://netflix.com/title/81038588",
                listOf("Action", "Crime", "Thriller"),
                "tt9845564"
            )
        )
    }
}