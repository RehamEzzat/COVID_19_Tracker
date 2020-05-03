package com.example.covid_19_tracker.viewmodel

import android.app.Application
import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.work.*
import com.example.covid_19_tracker.CURRENT_UPDATE_INTERVAL
import com.example.covid_19_tracker.RequestAPIRXWorker
import com.example.covid_19_tracker.RequestAPIWorker
import com.example.covid_19_tracker.model.CountryStatus
import com.example.covid_19_tracker.model.LocalDatabase
import com.example.covid_19_tracker.model.Repository
import com.example.covid_19_tracker.model.WorldStatus
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import java.util.*
import java.util.concurrent.TimeUnit


class CountriesListViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: Repository by lazy {
        Repository(application)
    }

    private val periodicWorkRequest : PeriodicWorkRequest by lazy {
        PeriodicWorkRequestBuilder<RequestAPIWorker>(
            repository.getUpdateInterval(), TimeUnit.HOURS).build()
    }

    fun getCountriesStatus(): LiveData<List<CountryStatus>>{
        return repository.getLocalCountriesStatus()
    }


    fun updateCountryStatus(countryStatus: CountryStatus){
        AsyncTask.execute {
            repository.updateLocalCountryStatus(countryStatus)
        }
    }

    // swipe to refresh and at changing update interval
    fun updateCountriesStatus(): LiveData<WorkInfo> {
        WorkManager
            .getInstance(getApplication())
            .enqueueUniquePeriodicWork("request", ExistingPeriodicWorkPolicy.REPLACE, periodicWorkRequest)
        return WorkManager.getInstance(getApplication()).getWorkInfoByIdLiveData(periodicWorkRequest.id)
    }
}