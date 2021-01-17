package com.nerses.weather.model

data class WeatherModel(
    val current: CurrentModel,
    val lat: Double,
    val lon: Double,
    val daily: List<DailyModel>,
    val hourly: List<HourlyModel>,
    val timezone: String,
    val timezone_offset: Int
)