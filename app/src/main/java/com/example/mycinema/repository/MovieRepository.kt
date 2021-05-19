package com.example.mycinema.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.mycinema.database.CachedDatabaseDao
import com.example.mycinema.database.MovieDatabaseDao
import com.example.mycinema.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepository(private val cachedDatabaseDao: CachedDatabaseDao, private val movieDatabaseDao: MovieDatabaseDao) {

    var movies: LiveData<List<Movie>> = cachedDatabaseDao.getAllMovies()

    suspend fun refreshTopRated(){
        withContext(Dispatchers.IO){
            val movieList = TMDBApi.movieListRetrofitService.getTopRatedMovies()
            cachedDatabaseDao.deleteAll()
            for(movie in movieList.results){
                cachedDatabaseDao.insert(movie)
            }
        }
    }

    suspend fun refreshPopular(){
        withContext(Dispatchers.IO){
            val movieList = TMDBApi.movieListRetrofitService.getPopularMovies()
            cachedDatabaseDao.deleteAll()
            for(movie in movieList.results){
                cachedDatabaseDao.insert(movie)
            }
        }
    }

}