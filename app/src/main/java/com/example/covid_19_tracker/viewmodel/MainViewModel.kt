package com.example.covid_19_tracker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.covid_19_tracker.RequestAPIWorker
import java.util.concurrent.TimeUnit

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val periodicWorkRequest : PeriodicWorkRequest by lazy {
        /*PeriodicWorkRequestBuilder<RequestAPIWorker>(
            CURRENT_UPDATE_INTERVAL.value, TimeUnit.MINUTES).build()*/
        PeriodicWorkRequestBuilder<RequestAPIWorker>(
            16, TimeUnit.MINUTES).build()
    }

    // form remote for first time only
    fun loadCountriesStatus(){
        WorkManager
            .getInstance(getApplication())
            .enqueueUniquePeriodicWork("request", ExistingPeriodicWorkPolicy.KEEP, periodicWorkRequest)
    }
}