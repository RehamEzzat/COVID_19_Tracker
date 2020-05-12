package com.example.covid_19_tracker.network

import android.util.Log
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class FirstApiRetrofitClient private constructor(){
    companion object {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://coronavirus-monitor.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}