package com.example.covid_19_tracker.view

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.work.*
import com.example.covid_19_tracker.R
import com.example.covid_19_tracker.RequestAPIWorker
import com.example.covid_19_tracker.network.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {

    private val fragmentManager: FragmentManager by lazy {
        supportFragmentManager
    }
    private var fragmentTransaction: FragmentTransaction? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
            if(fragmentManager.findFragmentByTag("BlankFragment") == null){
                fragmentTransaction = fragmentManager.beginTransaction()

                fragmentTransaction!!.add(fragmentsFrameLayout.id, BlankFragment(), "BlankFragment")
                fragmentManager.popBackStack()
                fragmentTransaction!!.addToBackStack("BlankFragment")

                fragmentTransaction!!.commit()
            }
        }
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
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // GoToSettingsActivity
        return super.onOptionsItemSelected(item)
    }
}
