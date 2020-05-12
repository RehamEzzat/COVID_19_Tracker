package com.example.covid_19_tracker.network


import com.example.covid_19_tracker.detailsCountry.model.DetailsCountry
import okio.ByteString
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface FirstRetrofitClientInterface {

    @Headers("x-rapidapi-host:coronavirus-monitor.p.rapidapi.com","X-RapidAPI-Key:f6199fba62msh899398b08097b40p1bbfb8jsn386c09ded695")
    @GET("coronavirus/{countryName}")
    fun getDetailsCountry(@Path("countryName" ) countryName : String) : Call<DetailsCountry>
}