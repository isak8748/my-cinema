package com.example.mycinema.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mycinema.database.CachedDatabaseDao
import com.example.mycinema.database.MovieDatabaseDao

class MovieListViewModelFactory(private val movieDatabaseDao: MovieDatabaseDao,
                                private val cachedDatabaseDao: CachedDatabaseDao,
                                private val application: Application,
                                ): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MovieListViewModel::class.java)) {
            return MovieListViewModel(movieDatabaseDao, cachedDatabaseDao, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}