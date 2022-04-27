package com.rubio.movstore.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rubio.movstore.data.models.MovieParcelable


@Database(
    entities = [
        MovieParcelable::class
    ],
    version = 1,
    exportSchema = false
)
abstract class MovieStoreRoomDB : RoomDatabase() {
    /*abstract fun movieStoreDao(): MovieStoreDao

    companion object{
        @Volatile
        private var INSTANCE:MovieStoreRoomDB? = null
        private val LOCK = Any()
        private val DB_NAME = "movies_database"

        fun getDatabase(context:Context):MovieStoreRoomDB{
            return INSTANCE ?: synchronized(LOCK){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MovieStoreRoomDB::class.java,
                    DB_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }*/
}