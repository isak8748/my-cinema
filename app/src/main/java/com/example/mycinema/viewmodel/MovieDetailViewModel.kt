package com.example.mycinema.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mycinema.database.MovieDatabaseDao
import com.example.mycinema.model.Movie
import kotlinx.coroutines.launch

class MovieDetailViewModel (private val movieDatabaseDao: MovieDatabaseDao,
                            application: Application,
                            movie: Movie
) : AndroidViewModel(application){

    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean>
        get() {
            return _isFavorite
        }

    init{
        setIsFavorite(movie)
    }

    private fun setIsFavorite(movie: Movie) {
        viewModelScope.launch{
            _isFavorite.value = movieDatabaseDao.isFavorite(movie.id)
        }
    }

    fun onSaveMovieButtonClicked(movie: Movie){
        viewModelScope.launch {
            movieDatabaseDao.insert(movie)
            setIsFavorite(movie)
        }
    }

    fun onRemoveMovieButtonClicked(movie: Movie){
        viewModelScope.launch {
            movieDatabaseDao.delete(movie)
            setIsFavorite(movie)
        }
    }

}