package com.example.mycinema.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.mycinema.model.Movie

/*
@Dao
interface MovieDatabaseDao {
    @Insert
    fun insert(movie: Movie)

    @Delete
    fun delete(movie: Movie)

    @Query("Select * from movies ORDER BY id ASC")
    fun getAllMovies(): List<Movie>

    @Query("SELECT EXISTS(SELECT * from movies WHERE id =:id)")
    fun isFavorite(id: Long): Boolean

}*/