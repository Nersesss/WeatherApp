package com.nerses.weather.data.response

data class DailyResponse(
    val dt: Long,
    val temp: TempResponse,
    val humidity: Int,

    )