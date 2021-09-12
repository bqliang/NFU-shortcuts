package com.bqliang.nfushortcuts.network

import com.bqliang.nfushortcuts.BuildConfig
import com.bqliang.nfushortcuts.model.RealtimeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherService {

    @GET("v2.5/${BuildConfig.CAIYUN_TOKEN}/{lng},{lat}/realtime.json")
    fun getRealtimeWeather(
        @Path("lng") lng: String,
        @Path("lat") lat: String,
        @Query("lang") language: String = com.bqliang.nfushortcuts.logic.Language.get()
    ) : Call<RealtimeResponse>
}