package com.nerses.weather.data.response


data class CurrentResponse(
    val dt: Long,
    val temp: Double,
    val humidity: Int,
)