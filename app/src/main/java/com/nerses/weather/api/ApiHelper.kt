package com.nerses.weather.api

import com.nerses.weather.data.response.WeatherResponse
import retrofit2.Response

interface ApiHelper {

    suspend fun getWeather( lat: String?,
         lon: String?,
        exclude: String?,
        units: String?,
         appID: String?
    ): Response<WeatherResponse>
}