package com.example.covid_19_tracker.network

import com.example.covid_19_tracker.model.CountryStatus
import com.example.covid_19_tracker.model.WorldStatus
import io.reactivex.Single
import retrofit2.http.GET

interface RetrofitClientInterface {
    @GET("/v2/countries")
    fun getCountriesStatus() : Single<List<CountryStatus>>

    @GET("/v2/all")
    fun getWorldStatus() : Single<WorldStatus>
}