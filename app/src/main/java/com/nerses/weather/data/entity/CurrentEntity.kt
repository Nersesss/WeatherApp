package com.nerses.weather.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nerses.weather.data.response.TempResponse

@Entity(tableName = "current")
data class CurrentEntity(
    @PrimaryKey()
    val dt: Long,
    val temp: Double,
    val humidity: Int,
)