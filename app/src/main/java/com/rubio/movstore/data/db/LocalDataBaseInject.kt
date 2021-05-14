package com.rubio.movstore.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rubio.movstore.data.db.moviedao.MovieStoreDao
import com.rubio.movstore.data.models.Movie

@Database(entities = [Movie::class], version = 1)
abstract class LocalDataBaseInject : RoomDatabase(){
    abstract fun movieStoreDao(): MovieStoreDao
}