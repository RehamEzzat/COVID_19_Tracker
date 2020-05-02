package com.example.covid_19_tracker.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName



@Entity(tableName = "country_cases")
data class CountryStatus (
    @SerializedName("country")
    @PrimaryKey
    val country: String,
    @ColumnInfo(name = "cases")
    val cases: Long,
    @ColumnInfo(name = "todayCases")
    val todayCases: Long,
    @ColumnInfo(name = "deaths")
    val deaths: Long,
    @ColumnInfo(name = "todayDeaths")
    val todayDeaths: Long,
    @ColumnInfo(name = "recovered")
    val recovered: Long,
    @ColumnInfo(name = "active")
    val active: Long,
    @ColumnInfo(name = "critical")
    val critical: Long,
    @ColumnInfo(name = "casesPerOneMillion")
    val casesPerOneMillion: Long,
    @ColumnInfo(name = "deathsPerOneMillion")
    val deathsPerOneMillion: Long,
    @ColumnInfo(name = "tests")
    val tests: Long,
    @ColumnInfo(name = "testsPerOneMillion")
    val testsPerOneMillion: Long,
    @ColumnInfo(name = "continent")
    val continent: String,
    @ColumnInfo(name = "isSubscriber")
    var isSubscriber: Boolean = false, // For Subscription
    var flagUrl: String,
    @Ignore
    val countryInfo: CountryInfo?
){
    constructor(country: String, cases: Long, todayCases: Long, deaths: Long, todayDeaths: Long,
                recovered: Long, active: Long, critical: Long, casesPerOneMillion: Long,
                deathsPerOneMillion: Long, tests: Long, testsPerOneMillion: Long,
                continent: String, isSubscriber: Boolean, flagUrl: String): this(country, cases, todayCases, deaths, todayDeaths, recovered, active, critical, casesPerOneMillion, deathsPerOneMillion, tests, testsPerOneMillion, continent, isSubscriber, flagUrl, null)
}


data class CountryInfo (
    @SerializedName("_id")
    val id: Long? = null,

    val iso2: String? = null,
    val iso3: String? = null,
    val lat: Double,
    val long: Double,
    val flag: String
)