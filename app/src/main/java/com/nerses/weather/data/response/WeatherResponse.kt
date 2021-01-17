package com.nerses.weather.data.response

data class WeatherResponse(
    val current: CurrentResponse,
    val lat: Double,
    val lon: Double,
    val daily: List<DailyResponse>,
    val hourly: List<HourlyResponse>,
    val timezone: String,
    val timezone_offset: Int
)