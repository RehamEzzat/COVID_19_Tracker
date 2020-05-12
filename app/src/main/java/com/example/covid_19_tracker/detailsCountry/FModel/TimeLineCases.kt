package com.example.covid_19_tracker.detailsCountry.FModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TimeLineCases (
    @SerializedName("cases")
    @Expose
    var cases : Map<String , Long> ,
    @SerializedName("deaths")
    @Expose
    var deaths : Map<String , Long> ,

    @SerializedName("recovered")
    @Expose
    var recovered : Map<String , Long>
){
}