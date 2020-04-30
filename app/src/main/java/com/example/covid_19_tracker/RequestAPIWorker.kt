package com.example.covid_19_tracker

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.covid_19_tracker.network.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RequestAPIWorker(appContext : Context, workerParams : WorkerParameters) : Worker(appContext, workerParams) {
    override fun doWork(): Result {
        Log.i("WORKER", "****new work****")

        RetrofitClient
            .getInstance()
            .getRetrofitClientInterface()!!
            .getCountriesStatus()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                //Update local data source
                Log.i("MainViewModel", it[0].country)
            },{
                Log.e("ERROR", it.message)
            })

        return Result.success()
    }
}
