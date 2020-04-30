package com.example.covid_19_tracker.network

import com.example.covid_19_tracker.model.CountryStatus
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header

interface RetrofitClientInterface {
    @GET("/v2/countries")
    fun getCountriesStatus() : Single<List<CountryStatus>>
}