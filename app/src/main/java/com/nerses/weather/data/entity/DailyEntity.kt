package com.nerses.weather.data.entity

data class DailyEntity(
    val dt: Long,
    val temp: TempEntity,
    val humidity: Int, )