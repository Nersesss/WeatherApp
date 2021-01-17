package com.nerses.weather.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nerses.weather.data.daos.LocationDao
import com.nerses.weather.data.daos.WeatherDao
import com.nerses.weather.data.entity.LocationEntity
import com.nerses.weather.data.entity.WeatherEntity

@Database(
    entities = [WeatherEntity::class, LocationEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun weatherDAO(): WeatherDao

    abstract fun locationDAO(): LocationDao
}