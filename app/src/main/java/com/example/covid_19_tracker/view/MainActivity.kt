package com.example.covid_19_tracker.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProviders
import androidx.work.*
import com.example.covid_19_tracker.R
import com.example.covid_19_tracker.RequestAPIWorker
import com.example.covid_19_tracker.network.RetrofitClient
import com.example.covid_19_tracker.viewmodel.MainViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    private var mainViewModel: MainViewModel? = null
    private val fragmentManager: FragmentManager by lazy {
        supportFragmentManager
    }

    private var fragmentTransaction: FragmentTransaction? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        mainViewModel!!.loadCountriesStatus()

        fragmentTransaction = fragmentManager.beginTransaction()
        if(fragmentManager.findFragmentByTag("WorldFragment") == null){
            fragmentTransaction!!.add(fragmentsFrameLayout.id, WorldFragment(), "WorldFragment")
        }
        fragmentTransaction!!.addToBackStack("WorldFragment")
        fragmentTransaction!!.commit()

        toggleButton.check(worldStatusButton.id)

        worldStatusButton.setOnClickListener{
            if(fragmentManager.findFragmentByTag("WorldFragment") == null){
                fragmentTransaction = fragmentManager.beginTransaction()

                fragmentTransaction!!.add(fragmentsFrameLayout.id, WorldFragment(), "WorldFragment")
                fragmentManager.popBackStack()
                fragmentTransaction!!.addToBackStack("WorldFragment")

                fragmentTransaction!!.commit()
            }
        }

        countriesStatusButton.setOnClickListener {
            if(fragmentManager.findFragmentByTag("CountriesListFragment") == null){
                fragmentTransaction = fragmentManager.beginTransaction()

                fragmentTransaction!!.add(fragmentsFrameLayout.id, CountriesListFragment(), "CountriesListFragment")
                fragmentManager.popBackStack()
                fragmentTransaction!!.addToBackStack("CountriesListFragment")

                fragmentTransaction!!.commit()
            }
        }

        //Log.i(TAG, "*********************"+mainViewModel!!.getUpdateInterval().toString())
        //mainViewModel!!.modifyUpdateInterval(1)
        //Log.i(TAG, "*********************"+mainViewModel!!.getUpdateInterval().toString())

        /*toggleButton.addOnButtonCheckedListener { toggleButton, checkedId, isChecked ->

            if(checkedId == worldStatusButton.id && isChecked){
                Log.i("MAIN ACTIVITY", "add world")
            }else{
                Log.i("MAIN ACTIVITY", "add countries")
            }
            *//*Log.i( "MAIN ACTIVITY 1", checkedId.toString())
            Log.i( "MAIN ACTIVITY 2", isChecked.toString())*//*
        }*/
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent = Intent(this, UpdateIntervalActivity::class.java)
        startActivity(intent)
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        fragmentManager.popBackStack()
        super.onBackPressed()
    }

}
