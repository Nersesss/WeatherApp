package com.nerses.weather.data.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nerses.weather.data.entity.WeatherEntity

@Dao
interface WeatherDao {

    @Query("SELECT * FROM weather WHERE id is :id")
    fun getWeather(id: Long): WeatherEntity?

    @Query("SELECT * FROM weather")
    fun getLocalWeather(): LiveData<WeatherEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(weatherEntity: WeatherEntity)
}