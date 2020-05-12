package com.example.covid_19_tracker.network


import com.example.covid_19_tracker.detailsCountry.FModel.CountryStatistic
import com.example.covid_19_tracker.model.CountryStatus
import com.example.covid_19_tracker.model.WorldStatus
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitClientInterface {
    @GET("/v2/countries")
    fun getCountriesStatus() : Single<List<CountryStatus>>

    @GET("/v2/all")
    fun getWorldStatus() : Single<WorldStatus>


//    https://disease.sh/v2/historical/Egypt?lastdays=30
    @GET("/v2/historical/{countryName}")
    fun getDetailsCountry(  @Path("countryName" ) countryName : String, @Query("lastdays") lastdays: Int?) : Call<CountryStatistic>

}