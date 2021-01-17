package com.nerses.weather.data.repository

import androidx.lifecycle.LiveData
import com.nerses.weather.ErrorType
import com.nerses.weather.api.ApiHelper
import com.nerses.weather.data.daos.WeatherDao
import com.nerses.weather.data.entity.WeatherEntity
import com.nerses.weather.model.mapper.WeatherMapper

class MainRepository constructor(
    private val apiHelper: ApiHelper,
    private val weatherDAO: WeatherDao
) {

    suspend fun getWeather(
        isOnline: Boolean,
        lat: String?,
        lon: String?,
        exclude: String?,
        units: String?,
        appID: String?
    ): ErrorType {

        if (isOnline) {
            val response = apiHelper.getWeather(lat, lon, exclude, units, appID)
            response.body()?.let {
                weatherDAO.insert(WeatherMapper().toEntity(it))
            }
            return if (response.isSuccessful){
                ErrorType.SUCCESS
            }else{
                ErrorType.NETWORK_ERROR
            }

        }
        return ErrorType.NETWORK_UNAVELABLE
    }

    fun getLocalWeather(): LiveData<WeatherEntity>? {
        return weatherDAO.getLocalWeather()
    }
}