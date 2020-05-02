package com.example.covid_19_tracker.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.covid_19_tracker.model.CountryStatus
//import com.example.covid_19_tracker.model.RapidAPICountryStatus
import com.example.covid_19_tracker.network.RetrofitClient

class CountriesListViewModel : ViewModel() {
    var countriesStatus : MutableLiveData<List<CountryStatus>> = MutableLiveData()
        get() {

            return countriesStatus
        }
}