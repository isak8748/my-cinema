package com.example.mycinema.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mycinema.database.MovieDatabaseDao
import com.example.mycinema.model.Movie

class MovieDetailViewModelFactory(private val movieDatabaseDao: MovieDatabaseDao,
                                  private val application: Application,
                                  private val movie: Movie
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MovieDetailViewModel::class.java)) {
            return MovieDetailViewModel(movieDatabaseDao, application, movie) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}