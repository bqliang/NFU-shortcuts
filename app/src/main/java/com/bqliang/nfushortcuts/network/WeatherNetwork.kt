package com.bqliang.nfushortcuts.network

import retrofit2.await

object WeatherNetwork {

    private val weatherService = ServiceCreator.create<WeatherService>()

    suspend fun getRealtimeWeather(lng: String, lat: String) =
        weatherService.getRealtimeWeather(lng, lat).await()

}