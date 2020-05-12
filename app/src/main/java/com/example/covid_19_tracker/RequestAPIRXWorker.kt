package com.example.covid_19_tracker

import android.content.Context
import android.util.Log
import androidx.work.RxWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.covid_19_tracker.model.CountryStatus
import com.example.covid_19_tracker.model.Repository
import com.example.covid_19_tracker.network.RetrofitClient
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

class RequestAPIRXWorker (var appContext : Context, workerParams : WorkerParameters) : RxWorker(appContext, workerParams) {
    private val TAG = "RequestAPIWorker"

    private val repository: Repository by lazy {
        Repository(appContext)
    }

    /*override fun doWork(): Result {
        Log.i("WORKER", "****new work****")

        repository
            .getRemoteCountriesStatus()
            .subscribeOn(Schedulers.io())
            .subscribe({
                Log.i(TAG, "updating data: " + Date().toString())
                it.forEach {newCountryStatus : CountryStatus ->
                    val oldCountryStatus: CountryStatus = repository.getLocalCountryStatusByCountryName(newCountryStatus.country)
                    if(oldCountryStatus != null) {
                        newCountryStatus.isSubscriber = oldCountryStatus.isSubscriber
                        if (oldCountryStatus.isSubscriber && isModifiedCountryStatus(newCountryStatus, oldCountryStatus)) {
                            //send notification(ex: egypt has updates)
                        }
                    }
                    newCountryStatus.flagUrl = newCountryStatus.countryInfo!!.flag
                    repository
                        .insertLocalCountryStatus(newCountryStatus)
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

        return Result.success()
    }*/

    override fun createWork(): Single<Result> {
        Log.i("WORKER", "****new work****")

        return repository
            .getRemoteCountriesStatus()
            .subscribeOn(Schedulers.io())
            .doOnSuccess {countriesStatus ->
                Log.i(TAG, "updating data: " + Date().toString())
                Log.i(TAG, "updating data: " + countriesStatus[0].country)
                countriesStatus.forEach {newCountryStatus : CountryStatus ->
                    val oldCountryStatus: CountryStatus = repository.getLocalCountryStatusByCountryName(newCountryStatus.country)
                    if(oldCountryStatus != null) {
                        newCountryStatus.isSubscriber = oldCountryStatus.isSubscriber
                        if (oldCountryStatus.isSubscriber && isModifiedCountryStatus(newCountryStatus, oldCountryStatus)) {
                            //send notification(ex: egypt has updates)
                        }
                    }
                    newCountryStatus.flagUrl = newCountryStatus.countryInfo!!.flag
                    repository
                        .insertLocalCountryStatus(newCountryStatus)
                }

                repository
                    .getRemoteWorldStatus()
                    .subscribeOn(Schedulers.io())
                    .doOnSuccess {worldStatus ->
                        Log.i(TAG, "WorldStatus: "+worldStatus.affectedCountries.toString())
                        repository.insertLocalWorldStatus(worldStatus)
                    }
                    .map {
                        Result.success()
                    }.onErrorReturn {
                        Result.failure()
                    }
            }
            .map {
                Result.success()
            }
            .onErrorReturn {
                Result.failure()
            }
    }

    private fun isModifiedCountryStatus(newCountryStatus: CountryStatus, oldCountryStatus: CountryStatus): Boolean{
        return when{
            newCountryStatus.todayCases != oldCountryStatus.todayCases -> false
            newCountryStatus.deaths != oldCountryStatus.deaths -> false
            newCountryStatus.recovered != oldCountryStatus.recovered -> false
            newCountryStatus.active != oldCountryStatus.active -> false
            else -> true
        }
    }


}
