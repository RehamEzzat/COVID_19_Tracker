package com.example.covid_19_tracker.detailsCountry.FModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CountryStatistic (
    @SerializedName("country") @Expose
    var country: String ,
    @SerializedName("province") @Expose
    var province: List<String?>? ,
    @SerializedName("timeline") @Expose
     var timeline: TimeLineCases?

)