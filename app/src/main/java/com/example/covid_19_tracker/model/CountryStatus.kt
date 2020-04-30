package com.example.covid_19_tracker.model


data class CountryStatus (
    val country: String,
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
