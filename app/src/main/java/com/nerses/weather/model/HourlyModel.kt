package com.nerses.weather.model

data class HourlyModel(
    val dt: Long,
    val humidity: Int,
    val temp: Double,
)