package com.example.mycinema.database


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mycinema.model.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class CachedDatabase  : RoomDatabase(){
    abstract val movieDatabaseDao: CachedDatabaseDao

    companion object {

        @Volatile
        private var INSTANCE: CachedDatabase? = null

        fun getInstance(context: Context): CachedDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CachedDatabase::class.java,
                        "cached_movie_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }


}