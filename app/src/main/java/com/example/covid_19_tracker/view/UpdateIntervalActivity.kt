package com.example.covid_19_tracker.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import androidx.lifecycle.ViewModelProviders
import com.example.covid_19_tracker.R
import com.example.covid_19_tracker.UPDATE_INTERVAL
import com.example.covid_19_tracker.viewmodel.UpdateIntervalViewModel
import kotlinx.android.synthetic.main.activity_update_interval.*

class UpdateIntervalActivity : AppCompatActivity() {

    private lateinit var updateIntervalViewModel: UpdateIntervalViewModel
    private var currentUpdateInterval: Long? = null
    private var newUpdateInterval: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_interval)

        updateIntervalViewModel = ViewModelProviders.of(this).get(UpdateIntervalViewModel::class.java)

        currentUpdateInterval = updateIntervalViewModel.getUpdateInterval()
        newUpdateInterval = currentUpdateInterval

        when(currentUpdateInterval){
            UPDATE_INTERVAL.ONE_HOUR.value -> firstIntervalRB.isChecked = true
            UPDATE_INTERVAL.TWO_HOURS.value -> secondIntervalRB.isChecked = true
            UPDATE_INTERVAL.FIVE_HOURS.value -> thirdIntervalRB.isChecked = true
            UPDATE_INTERVAL.ONE_DAY.value -> forthIntervalRB.isChecked = true
        }

        updateIntervalBTN.setOnClickListener {
            if(newUpdateInterval != currentUpdateInterval){
                updateIntervalViewModel.modifyUpdateInterval(newUpdateInterval!!)
                updateIntervalViewModel.updateCountriesStatus()
            }
            finish()
        }
    }


    fun onRadioButtonClicked(view: View) {
        if(view is RadioButton){
            when(view.id){
                firstIntervalRB.id -> newUpdateInterval = UPDATE_INTERVAL.ONE_HOUR.value
                secondIntervalRB.id -> newUpdateInterval = UPDATE_INTERVAL.TWO_HOURS.value
                thirdIntervalRB.id -> newUpdateInterval = UPDATE_INTERVAL.FIVE_HOURS.value
                forthIntervalRB.id -> newUpdateInterval = UPDATE_INTERVAL.ONE_DAY.value
            }
        }
    }
}
