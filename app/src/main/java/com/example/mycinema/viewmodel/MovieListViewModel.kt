package com.example.mycinema.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mycinema.database.CachedDatabaseDao
import com.example.mycinema.database.MovieDatabaseDao
import com.example.mycinema.database.Movies
import com.example.mycinema.model.Movie
import com.example.mycinema.network.DataFetchStatus
import com.example.mycinema.network.MovieResponse
import com.example.mycinema.repository.MovieRepository
import kotlinx.coroutines.launch

class MovieListViewModel(private val movieDatabaseDao: MovieDatabaseDao,
                         private val cachedDatabaseDao: CachedDatabaseDao,
                         application: Application
) : AndroidViewModel(application) {


    private val movieRepository = MovieRepository(cachedDatabaseDao, movieDatabaseDao)

    private val _dataFetchStatus = MutableLiveData<DataFetchStatus>()
    val dataFetchStatus: LiveData<DataFetchStatus>
        get() {
            return _dataFetchStatus
        }

    private var popularSaved = false


    private val _favList = MutableLiveData<List<Movie>>()
    val favList: LiveData<List<Movie>>
        get(){
            return _favList
        }

    //var movieList = movieRepository.movies
    var movieList = movieRepository.movies



    private val _navigateToMovieDetail = MutableLiveData<Movie>()
    val navigateToMovieDetail: LiveData<Movie>
        get() {
            return _navigateToMovieDetail
        }

    init {
        _dataFetchStatus.value = DataFetchStatus.LOADING
        refreshTopRatedFromRepository()
    }

    private fun refreshTopRatedFromRepository(){
        viewModelScope.launch {
            try{
                movieRepository.refreshTopRated()
                _dataFetchStatus.value = DataFetchStatus.DONE
                popularSaved = false
            }
            catch(e: Exception){
                if(popularSaved){
                    _favList.value = listOf()
                    _dataFetchStatus.value = DataFetchStatus.ERROR
                }
                else{
                    _favList.value = movieList.value //Force update
                    _dataFetchStatus.value = DataFetchStatus.DONE
                }

            }
        }
    }

    private fun refreshPopularFromRepository(){
        viewModelScope.launch {
            try{
                movieRepository.refreshPopular()
                _dataFetchStatus.value = DataFetchStatus.DONE
                popularSaved = true
            }
            catch(e: Exception){
                if(popularSaved){
                    _favList.value = movieList.value
                    _dataFetchStatus.value = DataFetchStatus.DONE
                }
                else{
                    _favList.value = listOf()
                    _dataFetchStatus.value = DataFetchStatus.ERROR
                }
            }
        }
    }



    fun getPopularMovies(){
        refreshPopularFromRepository()
    }

    fun getTopRatedMovies(){
        refreshTopRatedFromRepository()
    }

    fun getSavedMovies(){
        viewModelScope.launch{
            _favList.value = movieDatabaseDao.getAllMovies()
        }
    }

    /*fun getPopularMovies() {
        viewModelScope.launch {
            try {
                val movieResponse: MovieResponse =
                    TMDBApi.movieListRetrofitService.getPopularMovies()
                _movieList.value = movieResponse.results
                _dataFetchStatus.value = DataFetchStatus.DONE
                cachedDatabaseDao.deleteAll()
                for (movie in movieResponse.results){
                    cachedDatabaseDao.insert(movie)
                }
            } catch(e: Exception) {
                //_dataFetchStatus.value = DataFetchStatus.ERROR
                //_movieList.value = arrayListOf()
                _movieList.value = cachedDatabaseDao.getAllMovies().value
                /*if(cachedDatabaseDao.getAllMovies().isEmpty()){
                    _dataFetchStatus.value = DataFetchStatus.ERROR
                }*/
            }
        }
    }*/

    /*fun getTopRatedMovies() {
        viewModelScope.launch {
            try {
                val movieResponse: MovieResponse =
                    TMDBApi.movieListRetrofitService.getTopRatedMovies()
                movieList.value = movieResponse.results
                _dataFetchStatus.value = DataFetchStatus.DONE
                cachedDatabaseDao.deleteAll()
                for (movie in movieResponse.results){
                    cachedDatabaseDao.insert(movie)
                }
            } catch(e: Exception) {
                //_dataFetchStatus.value = DataFetchStatus.ERROR
                //_movieList.value = arrayListOf()
                _movieList.value = cachedDatabaseDao.getAllMovies().value
                /*if(cachedDatabaseDao.getAllMovies().value.isEmpty()){
                    _dataFetchStatus.value = DataFetchStatus.ERROR
                }*/
            }
        }
    }*/

    /*fun getSavedMovies(){
        viewModelScope.launch{
            _movieList.value = movieDatabaseDao.getAllMovies()
        }

    }*/

    /*fun addMovie() {
        viewModelScope.launch {
            _movieList.value?.let { movieDatabaseDao.insert(it.get(0)) }
        }
    }*/

    fun onMovieListItemClicked(movie: Movie){
        _navigateToMovieDetail.value = movie
    }

    fun onMovieDetailNavigated() {
        _navigateToMovieDetail.value = null
    }
}