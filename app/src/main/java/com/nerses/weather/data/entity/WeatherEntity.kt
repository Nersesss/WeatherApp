package com.nerses.weather.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nerses.weather.model.HourlyModel

@Entity(tableName = "weather")
data class WeatherEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val current: CurrentEntity,
    val lat: Double,
    val lon: Double,
    val hourly: List<HourlyEntity>,
    val daily: List<DailyEntity>,
    val timezone: String,
    val timezone_offset: Int
)