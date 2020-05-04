package com.example.covid_19_tracker

import android.content.Context
import android.util.Log
import androidx.work.*
import com.example.covid_19_tracker.model.CountryStatus
import com.example.covid_19_tracker.model.Repository
import com.example.covid_19_tracker.network.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

class RequestAPIWorker(var appContext : Context, workerParams : WorkerParameters) : Worker(appContext, workerParams) {
    private val TAG = "RequestAPIWorker"

    private val repository: Repository by lazy {
        Repository(appContext)
    }

    override fun doWork(): Result {
        Log.i("WORKER", "****new work****")

        repository
            .getRemoteCountriesStatus()
            .subscribeOn(Schedulers.io())
            .subscribe({
                Log.i(TAG, "updating data: " + Date().toString())
                it.forEach {newCountryStatus : CountryStatus ->
                    val oldCountryStatus: CountryStatus = repository.getLocalCountryStatusByCountryName(newCountryStatus.country)
                    newCountryStatus.flagUrl = newCountryStatus.countryInfo!!.flag
                    if(oldCountryStatus != null) {
                        newCountryStatus.isSubscriber = oldCountryStatus.isSubscriber
                        if (oldCountryStatus.isSubscriber && isModifiedCountryStatus(newCountryStatus, oldCountryStatus)) {
                            //send notification(ex: egypt has updates)
                            /*Aml*/
                            /*Aml*/
                            repository.updateLocalCountryStatus(newCountryStatus)
                        }
                    }else{
                        repository.insertLocalCountryStatus(newCountryStatus)
                    }
                }
            },{
                Log.e("ERROR", it.message)
            })

        repository
            .getRemoteWorldStatus()
            .subscribeOn(Schedulers.io())
            .subscribe({
                Log.i(TAG, "WorldStatus: "+it.affectedCountries.toString())
                repository.insertLocalWorldStatus(it)
            },{
                Log.e("ERROR", it.message)
            })

        /*val request = OneTimeWorkRequestBuilder<RequestAPIWorker>().build()
        WorkManager.getInstance(appContext).enqueueUniqueWork(
            "request",
            ExistingWorkPolicy.REPLACE,
            request
        )*/
        return Result.success()
    }

    private fun isModifiedCountryStatus(newCountryStatus: CountryStatus, oldCountryStatus: CountryStatus): Boolean{
        return when{
            newCountryStatus.todayCases != oldCountryStatus.todayCases -> true
            newCountryStatus.deaths != oldCountryStatus.deaths -> true
            newCountryStatus.recovered != oldCountryStatus.recovered -> true
            newCountryStatus.active != oldCountryStatus.active -> true
            else -> false
        }
    }
}
