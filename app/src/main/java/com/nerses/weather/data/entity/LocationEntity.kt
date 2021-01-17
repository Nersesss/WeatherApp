package com.nerses.weather.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "location")
class LocationEntity(
    @PrimaryKey
    val id: Long,
    @ColumnInfo(name = "latitude", defaultValue = "0.0")val latitude: Double,
    @ColumnInfo(name = "longitude", defaultValue = "0.0")val longitude: Double
)