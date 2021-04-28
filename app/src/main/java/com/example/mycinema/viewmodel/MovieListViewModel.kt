package com.example.mycinema.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mycinema.database.MovieDatabaseDao
import com.example.mycinema.database.Movies
import com.example.mycinema.model.Movie
import kotlinx.coroutines.launch

class MovieListViewModel(private val movieDatabaseDao: MovieDatabaseDao, application: Application) : AndroidViewModel(application) {

    private val _movieList = MutableLiveData<List<Movie>>()
    val movieList: LiveData<List<Movie>>
        get() {
            return _movieList
        }

        init {
            _movieList.postValue(Movies().list)
        }

    fun getSavedMovies(){
        viewModelScope.launch{
            _movieList.value = movieDatabaseDao.getAllMovies()
        }

    }

    fun addMovie() {
        viewModelScope.launch {
            _movieList.value?.let { movieDatabaseDao.insert(it.get(0)) }
        }
    }
}