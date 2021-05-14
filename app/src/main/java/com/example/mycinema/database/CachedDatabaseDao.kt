package com.example.mycinema.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.mycinema.model.Movie

@Dao
interface CachedDatabaseDao {
    @Insert
    suspend fun insert(movie: Movie)

    @Delete
    suspend fun delete(movie: Movie)

    @Query("Select * from movies ORDER BY id ASC")
    suspend fun getAllMovies(): List<Movie>
}