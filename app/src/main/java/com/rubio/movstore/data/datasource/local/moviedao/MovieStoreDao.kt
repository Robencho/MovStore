package com.rubio.movstore.data.datasource.local.moviedao

import androidx.room.*
import com.rubio.movstore.data.models.MovieParcelable

@Dao
interface MovieStoreDao {

    @Transaction
    suspend fun updateMoviesTable(movieStoreDao: List<MovieParcelable>?) {
        deleteAllMoviesInLocal()
        movieStoreDao?.let {
            insertMoviesInLocal(it)
        }
    }

    @Query("select * from movies_table order by original_title ASC")
    suspend fun getAllMoviesFromLocal(): List<MovieParcelable>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMoviesInLocal(movieStoreDao: List<MovieParcelable>)

    @Query("DELETE FROM movies_table")
    suspend fun deleteAllMoviesInLocal()
}