package com.example.covid_19_tracker.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.covid_19_tracker.CURRENT_UPDATE_INTERVAL
import com.example.covid_19_tracker.RequestAPIWorker
import com.example.covid_19_tracker.model.CountryStatus
import java.util.concurrent.TimeUnit

class CountriesListViewModel(application: Application) : AndroidViewModel(application) {
    /*var countriesStatus : MutableLiveData<List<CountryStatus>> = MutableLiveData()
        get() {
            return countriesStatus
        }

    fun updateCountriesStatus(countriesStatusList: List<CountryStatus>){
        countriesStatus.postValue(countriesStatusList)
    }*/
    private val periodicWorkRequest : PeriodicWorkRequest by lazy {
        PeriodicWorkRequestBuilder<RequestAPIWorker>(
            CURRENT_UPDATE_INTERVAL.value, TimeUnit.MINUTES).build()
    }

    private val countriesStatus : MutableLiveData<List<CountryStatus>> by lazy {
        MutableLiveData<List<CountryStatus>>()
    }

    fun getCountriesStatus(): LiveData<List<CountryStatus>>{
        //loadCountriesStatus()
        //observe from live data of room
        // therefore can remove "countriesStatus" and return the observe directly ? //what if local is empty?? check before return
        return countriesStatus
    }

    // form remote for first time only
    fun loadCountriesStatus(){
        WorkManager
            .getInstance(getApplication())
            .enqueueUniquePeriodicWork("request", ExistingPeriodicWorkPolicy.KEEP, periodicWorkRequest)
    }

    // swipe to refresh
    fun updateCountriesStatus(){
        WorkManager
            .getInstance(getApplication())
            .enqueueUniquePeriodicWork("request", ExistingPeriodicWorkPolicy.REPLACE, periodicWorkRequest)
    }

    fun subscribeCountryStatus(countryStatus: CountryStatus){
        //update in room
    }

    // from remote to local
    /*fun updateCountriesStatus(countriesStatusList: List<CountryStatus>){
        countriesStatus.postValue(countriesStatusList)
    }*/
}