package com.example.covid_19_tracker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.covid_19_tracker.RequestAPIWorker
import com.example.covid_19_tracker.model.Repository
import java.util.concurrent.TimeUnit

class UpdateIntervalViewModel (application: Application) : AndroidViewModel(application){
    private val repository: Repository by lazy {
        Repository(application)
    }

    fun getUpdateInterval(): Long{
        return repository.getUpdateInterval()
    }

    fun modifyUpdateInterval(numOfHours: Long){
        repository.modifyUpdateInterval(numOfHours)
    }

    private val periodicWorkRequest : PeriodicWorkRequest by lazy {
        PeriodicWorkRequestBuilder<RequestAPIWorker>(
            repository.getUpdateInterval(), TimeUnit.HOURS).build()
    }

    fun updateCountriesStatus(){
        WorkManager
            .getInstance(getApplication())
            .enqueueUniquePeriodicWork("request", ExistingPeriodicWorkPolicy.REPLACE, periodicWorkRequest)
    }
}