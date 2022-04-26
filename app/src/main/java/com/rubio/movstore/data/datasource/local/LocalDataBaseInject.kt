package com.rubio.movstore.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rubio.movstore.data.datasource.local.moviedao.MovieStoreDao
import com.rubio.movstore.data.datasource.local.userdao.UserDao
import com.rubio.movstore.data.models.Movie
import com.rubio.movstore.data.models.User

@Database(entities = [Movie::class, User::class], version = 1)
abstract class LocalDataBaseInject : RoomDatabase(){
    abstract fun movieStoreDao(): MovieStoreDao

    abstract fun userLoginDao():UserDao
}