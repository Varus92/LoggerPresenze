package com.example.presenze.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.presenze.model.*


@Database(
    entities = [EntityUser::class,EntityLezioni::class],  //classDB
    version = 1,
    exportSchema = false
)
abstract class DbPresenze : RoomDatabase() {

    //Dao
    abstract fun userDao(): DaoUser
    abstract fun lezioniDao(): DaoLezioni

    companion object {
        @Volatile
        private var INSTANCE: DbPresenze? = null

        fun getDatabase(context: Context): DbPresenze {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DbPresenze::class.java,
                    "database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }


}