package com.example.covid_19_tracker.model

import androidx.room.ColumnInfo
import androidx.room.Entity
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
    val isSubscriber: Boolean = false // For Subscription
)
