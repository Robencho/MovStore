package com.rubio.movstore.data.datasource.local.moviedao

import androidx.room.*
import com.rubio.movstore.data.models.Movie

@Dao
interface MovieStoreDao {

    @Transaction
    suspend fun updateMoviesTable(movieStoreDao: List<Movie>?) {
        deleteAllMoviesInLocal()
        movieStoreDao?.let {
            insertMoviesInLocal(it)
        }
    }

    @Query("select * from movies_table order by original_title ASC")
    suspend fun getAllMoviesFromLocal(): List<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMoviesInLocal(movieStoreDao: List<Movie>)

    @Query("DELETE FROM movies_table")
    suspend fun deleteAllMoviesInLocal()
}