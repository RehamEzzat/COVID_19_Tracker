package com.example.covid_19_tracker.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "world_status")
data class WorldStatus(
    @PrimaryKey
    val id: Int = 1,
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
    val testsPerOneMillion: Double,
    val affectedCountries: Int
)