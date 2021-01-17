package com.nerses.weather.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "temp")
data class TempEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val day: Double,
    val eve: Double,
    val max: Double,
    val min: Double,
    val morn: Double,
    val night: Double
)