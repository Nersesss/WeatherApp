package com.nerses.weather.data.entity

data class HourlyEntity(
    val dt: Long,
    val humidity: Int,
    val temp: Double,
)