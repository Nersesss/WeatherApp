package com.nerses.weather.data.response

data class HourlyResponse(
    val dt: Long,
    val humidity: Int,
    val temp: Double,
)