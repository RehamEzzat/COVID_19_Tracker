package com.example.covid_19_tracker.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(CountryStatus::class) , version = 1 , exportSchema = false)
abstract class LocalDatabase : RoomDatabase ( ) {
    abstract fun countryDao() : CountryDao

    companion object {
        @Volatile
        private var INSTANCE : LocalDatabase? = null

        fun getDataBase (context : Context) : LocalDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext ,
                    LocalDatabase::class.java ,
                    "country_database"
                )
                    //.allowMainThreadQueries()
                .build()
                INSTANCE = instance
                return  instance


            }
        }
    }
}