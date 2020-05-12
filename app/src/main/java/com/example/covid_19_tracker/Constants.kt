package com.example.covid_19_tracker

val BASE_URL : String = "https://disease.sh"
var CURRENT_UPDATE_INTERVAL : UPDATE_INTERVAL  = UPDATE_INTERVAL.TWO_HOURS // get it from shared preferences
enum class UPDATE_INTERVAL(var value: Long){
    ONE_HOUR(1),
    TWO_HOURS(2),
    FIVE_HOURS(5),
    ONE_DAY(24)
}