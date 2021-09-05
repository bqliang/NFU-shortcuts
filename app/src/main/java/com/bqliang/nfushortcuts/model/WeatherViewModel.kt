package com.bqliang.nfushortcuts.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.bqliang.nfushortcuts.logic.Repository

class WeatherViewModel : ViewModel() {

    private val locationLiveData = MutableLiveData<Location>()

    val weatherLiveData = Transformations.switchMap(locationLiveData){ location ->
        Repository.refreshWeather(location.lng, location.lat)
    }

    fun refreshWeather(){
        locationLiveData.value = Location("113.6736", "23.6352")
    }
}