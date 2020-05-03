package com.example.covid_19_tracker.view

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.work.*
import com.example.covid_19_tracker.R
import com.example.covid_19_tracker.RequestAPIWorker
import com.example.covid_19_tracker.network.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //WORK MANAGER
        /*val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()*/
        /*val re = PeriodicWorkRequestBuilder<RequestAPIWorker>(16, TimeUnit.MINUTES).build()
        WorkManager.getInstance(this).enqueueUniquePeriodicWork("request", ExistingPeriodicWorkPolicy.KEEP, re)*/



       /* Aml
        val db = LocalDatabase.getDataBase(this)
        var countryDao = db.countryDao()
        var countryModel = CountryModel("Egypt" , 500 ,100,200 ,20)
        var countryModel1 = CountryModel("Egypt1" , 500 ,100,200 ,20)
        var countryModel2 = CountryModel("Egypt2" , 500 ,100,200 ,20)
        var countryModel3 = CountryModel("Egypt3" , 500 ,100,200 ,20)

        GlobalScope.launch {
            countryDao.insert(countryModel)
            countryDao.insert(countryModel1)
            countryDao.insert(countryModel2)
            countryDao.insert(countryModel3)

            val data = countryDao.getAll()
            data.forEach {
                println(it)
                Log.e(
                    "query", it.countryName + it.newCases
                            + it.confirmedCases
                )
            }
        }
      */


    }





}
