package com.nerses.weather.model.mapper

import com.nerses.weather.data.entity.*
import com.nerses.weather.data.response.DailyResponse
import com.nerses.weather.data.response.HourlyResponse
import com.nerses.weather.data.response.WeatherResponse
import com.nerses.weather.model.*

class WeatherMapper {
    fun toEntity(weatherResponse: WeatherResponse): WeatherEntity {

        return WeatherEntity(
            1,
            CurrentEntity(
                weatherResponse.current.dt,
                weatherResponse.current.temp,
                weatherResponse.current.humidity
            ),
            weatherResponse.lat,
            weatherResponse.lon,
            toHourlyEntity(weatherResponse.hourly),
            toEntity(weatherResponse.daily),
            weatherResponse.timezone,
            weatherResponse.timezone_offset
        )
    }


    fun toEntity(list: List<DailyResponse>): List<DailyEntity> {
        val result = ArrayList<DailyEntity>()
        list.map {
            result.add(
                DailyEntity(
                    it.dt,
                    TempEntity(
                        1, it.temp.day, it.temp.eve,
                        it.temp.max, it.temp.min, it.temp.morn, it.temp.night
                    ),
                    it.humidity
                )
            )
        }
        return result
    }

    fun toHourlyEntity(list: List<HourlyResponse>): List<HourlyEntity> {
        val result = ArrayList<HourlyEntity>()
        list.map {
            result.add(
                HourlyEntity(
                    it.dt,
                    it.humidity,
                    it.temp
                )
            )
        }
        return result
    }


    fun toModel(weatherEntity: WeatherEntity?): WeatherModel? {
        return weatherEntity?.let {
            WeatherModel(
                CurrentModel(
                    weatherEntity.current.dt,
                    weatherEntity.current.temp,
                    weatherEntity.current.humidity
                ),
                weatherEntity.lat,
                weatherEntity.lon,
                toModel(weatherEntity.daily),
                toHourlyModel(weatherEntity.hourly),
                weatherEntity.timezone,
                weatherEntity.timezone_offset
            )
        }
    }


    private fun toModel(list: List<DailyEntity>): List<DailyModel> {
        val result = ArrayList<DailyModel>()
        list.map {
            result.add(
                DailyModel(
                    it.dt, TempModel(
                        it.temp.day,
                        it.temp.eve,
                        it.temp.max,
                        it.temp.min,
                        it.temp.morn,
                        it.temp.night,
                    ), it.humidity
                )
            )
        }
        return result
    }

    private fun toHourlyModel(list: List<HourlyEntity>): List<HourlyModel> {
        val result = ArrayList<HourlyModel>()
        list.map {
            result.add(
                HourlyModel(
                    it.dt,
                    it.humidity,
                    it.temp
                )
            )
        }
        return result
    }
}