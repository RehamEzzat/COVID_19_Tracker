package com.example.covid_19_tracker.model

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import io.reactivex.Single

@Dao
interface CountryDao {
    @Query("SELECT * FROM  country_cases")
    fun getAll () :LiveData<List<CountryStatus>>

    @Query("SELECT * FROM  country_cases where country like :country_name ")
    fun findCoutry (country_name : String) : CountryStatus

    @Insert(onConflict = REPLACE)
    fun insert(countryModel : CountryStatus )

    @Query("DELETE FROM country_cases")
    fun deleteAll()

    @Query("DELETE FROM country_cases where country like :country_name ")
    fun deleteCountry(country_name : String)

    @Update
    fun updateCountry(countryStatus : CountryStatus)

}