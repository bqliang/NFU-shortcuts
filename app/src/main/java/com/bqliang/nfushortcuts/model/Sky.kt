package com.bqliang.nfushortcuts.model

import com.bqliang.nfushortcuts.R

class Sky (val infoStrResId: Int)

private val sky = mapOf(
    "CLEAR_DAY" to Sky(R.string.weather_clear),
    "CLEAR_NIGHT" to Sky(R.string.weather_clear),
    "PARTLY_CLOUDY_DAY" to Sky(R.string.weather_cloudy),
    "PARTLY_CLOUDY_NIGHT" to Sky(R.string.weather_partly_cloudy),
    "CLOUDY" to Sky(R.string.weather_cloudy),
    "WIND" to Sky(R.string.weather_wind),
    "LIGHT_RAIN" to Sky(R.string.weather_light_rain),
    "MODERATE_RAIN" to Sky(R.string.weather_moderate_rain),
    "HEAVY_RAIN" to Sky(R.string.weather_heavy_rain),
    "STORM_RAIN" to Sky(R.string.weather_storm_rain),
    "LIGHT_HAZE" to Sky(R.string.weather_light_haze),
    "MODERATE_HAZE" to Sky(R.string.weather_moderate_haze),
    "HEAVY_HAZE" to Sky(R.string.weather_heavy_haze),
    "FOG" to Sky(R.string.weather_fog),
    "LIGHT_SNOW" to Sky(R.string.weather_light_snow),
    "MODERATE_SNOW" to Sky(R.string.weather_moserate_snow),
    "HEAVY_SNOW" to Sky(R.string.weather_heavy_snow),
    "STORM_SNOW" to Sky(R.string.weather_storm_rain),
    "DUST" to Sky(R.string.weather_dust),
    "SAND" to Sky(R.string.weather_sand)
)

fun getSky(skycon: String) : Sky {
    return sky[skycon]?: sky["CLEAR_DAY"]!!
}