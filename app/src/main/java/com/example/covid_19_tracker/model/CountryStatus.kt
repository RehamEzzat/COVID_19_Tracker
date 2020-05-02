package com.example.covid_19_tracker.model

import com.google.gson.annotations.SerializedName


data class CountryStatus (
    val country: String,
    val countryInfo: CountryInfo,
    val cases: Long,
    val todayCases: Long,
    val deaths: Long,
    val todayDeaths: Long,
    val recovered: Long,
    val active: Long,
    val critical: Long,
    val casesPerOneMillion: Long,
    val deathsPerOneMillion: Long,
    val tests: Long,
    val testsPerOneMillion: Long,
    val continent: String,
    val isSubscriber: Boolean = false // For Subscription
)

data class CountryInfo (
    @SerializedName("_id")
    val id: Long? = null,

    val iso2: String? = null,
    val iso3: String? = null,
    val lat: Double,
    val long: Double,
    val flag: String
)