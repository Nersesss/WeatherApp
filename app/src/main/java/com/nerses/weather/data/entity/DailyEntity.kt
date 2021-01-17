package com.nerses.weather.data.entity

import androidx.room.Entity

@Entity(tableName = "daily")
data class DailyEntity(
    val dt: Long,
    val temp: TempEntity,
    val humidity: Int,

    )