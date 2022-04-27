package com.rubio.movstore.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rubio.movstore.data.datasource.local.moviedao.MovieStoreDao
import com.rubio.movstore.data.datasource.local.userdao.UserDao
import com.rubio.movstore.data.models.MovieParcelable
import com.rubio.movstore.data.models.UserParcelable

@Database(entities = [MovieParcelable::class, UserParcelable::class], version = 1)
abstract class LocalDataBaseInject : RoomDatabase(){
    abstract fun movieStoreDao(): MovieStoreDao

    abstract fun userLoginDao():UserDao
}