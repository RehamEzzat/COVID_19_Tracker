package com.example.covid_19_tracker.model

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.covid_19_tracker.network.RetrofitClient
import com.example.covid_19_tracker.network.RetrofitClientInterface
import io.reactivex.Single

class Repository(var context: Context) {
    private val retrofitClientInterface: RetrofitClientInterface by lazy {
        RetrofitClient.getInstance().getRetrofitClientInterface()
    }

    private val db: LocalDatabase by lazy {
        LocalDatabase.getDataBase(context)
    }

    fun getLocalCountriesStatus(): LiveData<List<CountryStatus>> {
        return db.countryDao().getAll()
    }

    fun getRemoteCountriesStatus(): Single<List<CountryStatus>> {
        return retrofitClientInterface.getCountriesStatus()
    }

    fun getLocalCountryStatusByCountryName(countryName: String): CountryStatus{
        return db.countryDao().findCoutry(countryName)
    }

    fun insertLocalCountryStatus(countryStatus: CountryStatus){
        db.countryDao().insert(countryStatus)
    }

    fun updateLocalCountryStatus(countryStatus: CountryStatus){
        db.countryDao().updateCountry(countryStatus)
    }

    fun getLocalWorldStatus(): LiveData<WorldStatus>{
        return db.worldStatusDao().getWorldStatus()
    }

    fun getRemoteWorldStatus(): Single<WorldStatus>{
        return retrofitClientInterface.getWorldStatus()
    }

    fun insertLocalWorldStatus(worldStatus: WorldStatus){
        db.worldStatusDao().insert(worldStatus)
    }
}