package com.nerses.weather.api

import com.nerses.weather.data.response.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/data/2.5/onecall")
    suspend fun getWeather(
        @Query("lat") lat: String?,
        @Query("lon") lon: String?,
        @Query("exclude") exclude: String?,
        @Query("units") units: String?,
        @Query("appid") appid: String?
    ): Response<WeatherResponse>
}