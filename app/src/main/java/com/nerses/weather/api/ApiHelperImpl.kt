package com.nerses.weather.api

import com.nerses.weather.data.response.WeatherResponse
import com.nerses.weather.model.WeatherModel
import retrofit2.Response

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {

    override suspend fun getWeather(
        lat: String?,
        lon: String?,
        exclude: String?,
        units: String?,
        appID: String?
    ): Response<WeatherResponse> = apiService.getWeather(lat, lon, exclude, units, appID)

}