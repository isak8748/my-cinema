package com.example.mycinema.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.mycinema.model.Movie

@Dao
interface CachedDatabaseDao {
    @Insert
    fun insert(movie: Movie)

    @Query("DELETE FROM movies")
    fun deleteAll()

    @Delete
    fun delete(movie: Movie)

    @Query("Select * from movies ORDER BY id ASC")
    fun getAllMovies(): LiveData<List<Movie>>

}