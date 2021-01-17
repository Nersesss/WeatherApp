package com.nerses.weather.data.entity


data class TempEntity(
    val id: Long,
    val day: Double,
    val eve: Double,
    val max: Double,
    val min: Double,
    val morn: Double,
    val night: Double
)