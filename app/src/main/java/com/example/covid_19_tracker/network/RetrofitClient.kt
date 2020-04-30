package com.example.covid_19_tracker.network

import android.util.Log
import com.example.covid_19_tracker.BASE_URL
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitClient private constructor(){
    companion object{
        private var retrofitClientInterface : RetrofitClientInterface? = null
        private var INSTANCE : RetrofitClient? = null

        fun getInstance() : RetrofitClient{
            Log.i("RetrofitClient", "getting data")
            if(INSTANCE == null){
                /*val okHttpClient : OkHttpClient = OkHttpClient.Builder()
                    .addNetworkInterceptor{
                        var request : Request = it.request().newBuilder().addHeader("Cookie", X_RAPIDAPI_KEY).build()
                        it.proceed(request)
                    }
                    .build()*/

                INSTANCE = RetrofitClient()

                retrofitClientInterface = Retrofit.
                        Builder().
                        baseUrl(BASE_URL).
                        //client(okHttpClient).
                        addConverterFactory(GsonConverterFactory.create()).
                        addCallAdapterFactory(RxJava2CallAdapterFactory.create()).
                        build().
                        create(RetrofitClientInterface::class.java)
            }
            return INSTANCE!!
        }
    }

    fun getRetrofitClientInterface() : RetrofitClientInterface{
        return retrofitClientInterface!!
    }

}