package com.bqliang.nfushortcuts.logic

import androidx.lifecycle.liveData
import com.bqliang.nfushortcuts.model.Weather
import com.bqliang.nfushortcuts.network.WeatherNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlin.coroutines.CoroutineContext

object Repository {

    fun refreshWeather(lng: String, lat: String) = fire(Dispatchers.IO) {
        coroutineScope {
            val deferredRealtime = async { WeatherNetwork.getRealtimeWeather(lng, lat) }
            val realtimeResponse = deferredRealtime.await()
            if (realtimeResponse.status == "ok"){
                val weather = Weather(realtimeResponse.result.realtime)
                Result.success(weather)
            }else {
                Result.failure(RuntimeException("realtime response status is ${realtimeResponse.status}"))
            }
        }
    }

    private fun <T> fire(context: CoroutineContext, block: suspend () -> Result<T>) =
        liveData(context) {
            val result = try {
                block()
            } catch (e: Exception){
                Result.failure(e)
            }
            emit(result)
        }

}