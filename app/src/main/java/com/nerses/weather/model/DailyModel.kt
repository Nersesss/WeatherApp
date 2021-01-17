package com.nerses.weather.model



data class DailyModel(

    val dt: Long,
    val temp: TempModel,
    val humidity: Int,

)